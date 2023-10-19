package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_TWO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddScheduleCommand.
 */
class AddScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        //multiple null params
        assertThrows(NullPointerException.class, () -> new AddScheduleCommand(null, null, null));

        // null index
        assertThrows(NullPointerException.class, () -> new AddScheduleCommand(null, SCHEDULE_ALICE_FIRST_JAN
                .getStartTime(), SCHEDULE_ALICE_FIRST_JAN.getEndTime()));

        // null start time
        assertThrows(NullPointerException.class, () -> new AddScheduleCommand(INDEX_FIRST_PERSON,
                SCHEDULE_ALICE_FIRST_JAN.getStartTime(), null));

        // null end time
        assertThrows(NullPointerException.class, () -> new AddScheduleCommand(INDEX_FIRST_PERSON, null,
                SCHEDULE_ALICE_FIRST_JAN.getEndTime()));
    }

    @Test
    public void execute_validParametersUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Schedule schedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withTutor(firstPerson).build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(INDEX_FIRST_PERSON,
                SCHEDULE_ALICE_FIRST_JAN.getStartTime(),
                SCHEDULE_ALICE_FIRST_JAN.getEndTime());

        String expectedMessage = String.format(AddScheduleCommand.MESSAGE_SUCCESS,
                Messages.format(schedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addSchedule(schedule);

        assertCommandSuccess(addScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validParametersFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Schedule schedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withTutor(firstPerson).build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(INDEX_FIRST_PERSON,
                SCHEDULE_ALICE_FIRST_JAN.getStartTime(),
                SCHEDULE_ALICE_FIRST_JAN.getEndTime());

        String expectedMessage = String.format(AddScheduleCommand.MESSAGE_SUCCESS,
                Messages.format(schedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.addSchedule(schedule);

        assertCommandSuccess(addScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateSchedule_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Schedule schedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withTutor(firstPerson).build();
        model.addSchedule(schedule);

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(INDEX_FIRST_PERSON,
                SCHEDULE_ALICE_FIRST_JAN.getStartTime(),
                SCHEDULE_ALICE_FIRST_JAN.getEndTime());

        assertCommandFailure(addScheduleCommand, model, AddScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_badSchedule_failure() {
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(INDEX_FIRST_PERSON,
                new StartTime(SCHEDULE_ALICE_FIRST_JAN.getEndTime().getTime()),
                new EndTime(SCHEDULE_ALICE_FIRST_JAN.getStartTime().getTime()));

        assertCommandFailure(addScheduleCommand, model, Schedule.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_clashingSchedule_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Schedule baseSchedule = new ScheduleBuilder().withTutor(firstPerson).build();
        LocalDateTime baseScheduleStartTime = baseSchedule.getStartTime().getTime();
        LocalDateTime baseScheduleEndTime = baseSchedule.getEndTime().getTime();
        model.addSchedule(baseSchedule);

        // ends 1 second after the base schedule starts
        AddScheduleCommand addSchedule1 = new AddScheduleCommand(INDEX_FIRST_PERSON,
                new StartTime(baseScheduleStartTime.minusHours(1)),
                new EndTime(baseScheduleStartTime.plusSeconds(1)));

        // starts 1 second before the base schedule starts
        AddScheduleCommand addSchedule2 = new AddScheduleCommand(INDEX_FIRST_PERSON,
                new StartTime(baseScheduleEndTime.minusSeconds(1)),
                new EndTime(baseScheduleEndTime.plusHours(1)));

        // starts and ends in the middle of the base schedule
        AddScheduleCommand addSchedule3 = new AddScheduleCommand(INDEX_FIRST_PERSON,
                new StartTime(baseScheduleStartTime.plusSeconds(1)),
                new EndTime(baseScheduleEndTime.minusSeconds(1)));

        assertCommandFailure(addSchedule1, model, AddScheduleCommand.MESSAGE_CLASHING_SCHEDULE);
        assertCommandFailure(addSchedule2, model, AddScheduleCommand.MESSAGE_CLASHING_SCHEDULE);
        assertCommandFailure(addSchedule3, model, AddScheduleCommand.MESSAGE_CLASHING_SCHEDULE);
    }

    @Test
    public void execute_nonClashingSchedule_success() {
        Person firstTutor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Schedule baseSchedule = new ScheduleBuilder().withTutor(firstTutor).build();
        LocalDateTime baseScheduleStartTime = baseSchedule.getStartTime().getTime();
        LocalDateTime baseScheduleEndTime = baseSchedule.getEndTime().getTime();
        model.addSchedule(baseSchedule);

        // ends immediately as base schedule starts
        AddScheduleCommand addSchedule = new AddScheduleCommand(INDEX_FIRST_PERSON,
                new StartTime(baseScheduleStartTime.minusHours(1)),
                new EndTime(baseScheduleStartTime));

        Schedule schedule =
                new ScheduleBuilder().withTutor(firstTutor).withStartTime(baseScheduleStartTime.minusHours(1))
                        .withEndTime(baseScheduleStartTime).build();

        String expectedMessage = String.format(AddScheduleCommand.MESSAGE_SUCCESS,
                Messages.format(schedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addSchedule(schedule);

        assertCommandSuccess(addSchedule, model, expectedMessage, expectedModel);

        // Next day schedule
        AddScheduleCommand addSchedule2 = new AddScheduleCommand(INDEX_FIRST_PERSON,
                new StartTime(baseScheduleStartTime.plusDays(1)),
                new EndTime(baseScheduleEndTime.plusDays(1)));

        Schedule schedule2 =
                new ScheduleBuilder().withTutor(firstTutor).withStartTime(baseScheduleStartTime.plusDays(1))
                        .withEndTime(baseScheduleEndTime.plusDays(1)).build();

        String expectedMessage2 = String.format(AddScheduleCommand.MESSAGE_SUCCESS,
                Messages.format(schedule2));

        expectedModel.addSchedule(schedule2);

        assertCommandSuccess(addSchedule2, model, expectedMessage2, expectedModel);

        // Other tutor same time
        Person secondTutor = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        AddScheduleCommand addSchedule3 = new AddScheduleCommand(INDEX_SECOND_PERSON,
                new StartTime(baseScheduleStartTime),
                new EndTime(baseScheduleEndTime));

        Schedule schedule3 =
                new ScheduleBuilder().withTutor(secondTutor).withStartTime(baseScheduleStartTime)
                        .withEndTime(baseScheduleEndTime).build();

        String expectedMessage3 = String.format(AddScheduleCommand.MESSAGE_SUCCESS,
                Messages.format(schedule3));

        expectedModel.addSchedule(schedule3);

        assertCommandSuccess(addSchedule3, model, expectedMessage3, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(outOfBoundIndex,
                SCHEDULE_ALICE_FIRST_JAN.getStartTime(),
                SCHEDULE_ALICE_FIRST_JAN.getEndTime());

        assertCommandFailure(addScheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final StartTime standardStartTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_ONE));
        final EndTime standardEndTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_ONE));
        final AddScheduleCommand standardCommand = new AddScheduleCommand(INDEX_FIRST_PERSON, standardStartTime,
                standardEndTime);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> returns true
        StartTime copyStartTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_ONE));
        EndTime copyEndTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_ONE));
        AddScheduleCommand addScheduleCommandCopy =
                new AddScheduleCommand(INDEX_FIRST_PERSON, copyStartTime, copyEndTime);
        assertTrue(standardCommand.equals(addScheduleCommandCopy));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different start time -> returns false
        StartTime differentStartTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_TWO));
        assertFalse(standardCommand.equals(
                new AddScheduleCommand(INDEX_FIRST_PERSON, differentStartTime, standardEndTime)));

        // different end time -> returns false
        EndTime differentEndTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_TWO));
        assertFalse(standardCommand.equals(
                new AddScheduleCommand(INDEX_FIRST_PERSON, standardStartTime, differentEndTime)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        StartTime startTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_ONE));
        EndTime endTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_ONE));
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(index, startTime, endTime);

        String expected = AddScheduleCommand.class.getCanonicalName() + "{index=" + index + ", startTime="
                + startTime + ", endTime="
                + endTime + "}";
        assertEquals(expected, addScheduleCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSchedule(Schedule target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSchedules(ObservableList<Schedule> targets) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(Schedule target, Schedule editedSchedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getSchedulesFromTutor(Person tutor) throws PersonNotFoundException {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithSchedule extends AddScheduleCommandTest.ModelStub {
        private final Schedule schedule;

        ModelStubWithSchedule(Schedule schedule) {
            requireNonNull(schedule);
            this.schedule = schedule;
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            requireNonNull(schedule);
            return this.schedule.equals(schedule);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingScheduleAdded extends AddScheduleCommandTest.ModelStub {
        final ArrayList<Schedule> schedulesAdded = new ArrayList<>();

        @Override
        public boolean hasSchedule(Schedule schedule) {
            requireNonNull(schedule);
            return schedulesAdded.stream().anyMatch(schedule::equals);
        }

        @Override
        public void addSchedule(Schedule schedule) {
            requireNonNull(schedule);
            schedulesAdded.add(schedule);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
