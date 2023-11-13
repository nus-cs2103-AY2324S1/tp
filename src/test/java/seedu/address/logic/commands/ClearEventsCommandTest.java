package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventPeriodBuilder;

class ClearEventsCommandTest {
    private static final EventPeriod defaultPeriod = new EventPeriodBuilder().build();
    private static final ClearEventsCommand defaultTrueCommand =
            new ClearEventsCommand(defaultPeriod, true);
    private static final ClearEventsCommand defaultFalseCommand =
            new ClearEventsCommand(defaultPeriod, false);

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearEventsCommand(null, true));
        assertThrows(NullPointerException.class, () -> new ClearEventsCommand(null, false));
    }

    @Test
    public void execute_noEvent_throwsCommandException() {
        ClearEventsCommandTest.ModelStub modelStub = new ClearEventsCommandTest.ModelStubWithNoEvent();

        assertThrows(CommandException.class, ClearEventsCommand.MESSAGE_NO_EVENTS, () -> defaultFalseCommand
                .execute(modelStub));
        assertThrows(CommandException.class, ClearEventsCommand.MESSAGE_NO_EVENTS, () -> defaultTrueCommand
                .execute(modelStub));
    }

    @Test
    public void constructor_oneEventClearedFromModel_deleteSuccessful() throws Exception {
        Event validEvent = new EventBuilder().build();
        ClearEventsCommandTest.ModelStubWithOneEvent modelStub =
                new ClearEventsCommandTest.ModelStubWithOneEvent(validEvent);

        EventPeriodBuilder builder = new EventPeriodBuilder();
        builder.changeStartAndEnd(EventBuilder.DEFAULT_START_TIME_STRING, EventBuilder.DEFAULT_END_TIME_STRING);
        EventPeriod eventPeriod = builder.build();

        CommandResult commandResult = new ClearEventsCommand(eventPeriod, true).execute(modelStub);
        CommandResult commandResult2 = new ClearEventsCommand(eventPeriod, false).execute(modelStub);
        assertEquals(ClearEventsCommand.MESSAGE_SUCCESS + "1. " + Messages.format(validEvent) + "\n",
                commandResult.getFeedbackToUser());
        assertEquals(ClearEventsCommand.MESSAGE_WITHIN_RANGE
                + "1. " + Messages.format(validEvent) + "\n"
                + ClearEventsCommand.MESSAGE_ADD_CONFIRMATION
                + String.format(ClearEventsCommand.COMMAND_RESEND_FORMAT, EventBuilder.DEFAULT_START_TIME_STRING,
                        EventBuilder.DEFAULT_END_TIME_STRING),
                commandResult2.getFeedbackToUser());
    }

    @Test
    public void equals() {
        EventPeriodBuilder nonDefaultBuilder = new EventPeriodBuilder();
        nonDefaultBuilder.changeStart("2023-01-02 08:00");
        EventPeriod nonDefaultPeriod = nonDefaultBuilder.build();
        ClearEventsCommand nonDefaultTrueCommand = new ClearEventsCommand(nonDefaultPeriod, true);

        // same object -> returns true
        assertTrue(defaultTrueCommand.equals(defaultTrueCommand));

        // same values -> returns true
        ClearEventsCommand clearEventsTrueDefaultCopy = new ClearEventsCommand(defaultPeriod, true);
        assertTrue(defaultTrueCommand.equals(clearEventsTrueDefaultCopy));

        // different types -> returns false
        assertFalse(defaultTrueCommand.equals(1));

        // null -> returns false
        assertFalse(defaultTrueCommand.equals(null));

        // different timing -> returns false
        assertFalse(defaultTrueCommand.equals(nonDefaultTrueCommand));

        // different confirmation -> returns false
        assertFalse(defaultTrueCommand.equals(defaultFalseCommand));
    }

    @Test
    void toStringMethod() {
        String expected = ClearEventsCommand.class.getCanonicalName() + "{toClearWithin=" + defaultPeriod
                + ", confirmed=true}";
        assertEquals(expected, defaultTrueCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
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
        public Path getCalendarFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCalendarFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskManagerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskManagerFilePath(Path addressBookFilePath) {
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
        public void setCalendar(ReadOnlyCalendar newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getCurrentWeekEventList() {
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
        public boolean canAddEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEventAt(LocalDateTime dateTime) throws EventNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override

        public Event findEventAt(LocalDateTime dateTime) throws EventNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Event> eventsInRange(EventPeriod range) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEventsInRange(EventPeriod range) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCalendar getComparisonCalendar() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setComparisonCalendar(ReadOnlyCalendar eventList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task deleteTask(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TaskManager getTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTasksBy(String comparatorType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void sortPersonList(Comparator<Person> personComparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithNoEvent extends ClearEventsCommandTest.ModelStub {

        @Override
        public List<Event> eventsInRange(EventPeriod eventPeriod) {
            return List.of();
        }
    }

    private class ModelStubWithOneEvent extends ClearEventsCommandTest.ModelStub {
        private final Event event;

        ModelStubWithOneEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public List<Event> eventsInRange(EventPeriod eventPeriod) {
            return List.of(event);
        }

        @Override
        public void deleteEventsInRange(EventPeriod eventPeriod) {
            return;
        }
    }
}
