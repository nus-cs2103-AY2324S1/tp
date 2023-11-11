package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TimeInterval;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TimeIntervalBuilder;
import seedu.address.testutil.TypicalTimeIntervals;

public class AddTimeCommandTest {

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTimeCommand(null, null));
    }

    @Test
    public void execute_personDoesNotExit_throwException() {
        Person validPerson = new PersonBuilder().build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson.getName());
        Person toAdd = new PersonBuilder().withName("doesnotexistperson").build();
        TimeInterval timeInterval = new TimeIntervalBuilder().build();
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(timeInterval);

        try {
            CommandResult commandResult = new AddTimeCommand(toAdd.getName(), timeIntervalArrayList).execute(modelStub);
            assertThrows(CommandException.class, () -> new AddTimeCommand(toAdd.getName(),
                timeIntervalArrayList).execute(modelStub));
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "Person does not exists");
        }
    }

    @Test
    public void execute_addSingleTimeToPerson_addSuccessful() {
        Person validPerson = new PersonBuilder().build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson);
        TimeInterval timeInterval = new TimeIntervalBuilder().build();
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(timeInterval);
        String status = "These times have been added:\n" + timeInterval.toString() + "\n";

        try {
            CommandResult commandResult = new AddTimeCommand(validPerson.getName(),
                timeIntervalArrayList).execute(modelStub);
            assertEquals(String.format(AddTimeCommand.MESSAGE_SUCCESS + status, Messages.format(validPerson.getName())),
                commandResult.getFeedbackToUser());
            assertEquals(timeInterval, modelStub.personList.get(0).getTime().iterator().next());
        } catch (CommandException e) {
            fail();
        }

    }

    @Test
    public void execute_addMultipleTimeWithNoOverlapToPerson_addSuccessfulAddMsg() {
        Person validPerson = new PersonBuilder().build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson);
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP);

        String status = "These times have been added:\n" + TypicalTimeIntervals.TIME_INTERVAL_ONE_NO_OVERLAP.toString()
            + "\n" + TypicalTimeIntervals.TIME_INTERVAL_TWO_NO_OVERLAP.toString()
            + "\n" + TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP.toString()
            + "\n";

        try {
            CommandResult commandResult = new AddTimeCommand(validPerson.getName(),
                timeIntervalArrayList).execute(modelStub);
            assertEquals(String.format(AddTimeCommand.MESSAGE_SUCCESS + status, Messages.format(validPerson.getName())),
                commandResult.getFeedbackToUser());

            assertEquals("\n" + TypicalTimeIntervals.TIME_INTERVAL_ONE_NO_OVERLAP.toString()
                + "\n" + TypicalTimeIntervals.TIME_INTERVAL_TWO_NO_OVERLAP.toString()
                + "\n" + TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP.toString(),
                modelStub.personList.get(0).getTime().toString());
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_addMultipleTimeToPersonWithOneExistingOverlappingTime_addUnSuccessfulClashMsg() {
        Person validPerson = new PersonBuilder().withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A)
            .build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson);
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A);

        String status = "There is a clash in these input timings with your existing timings:\n"
            + TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A.toString()
            + "\n" + TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A.toString()
            + "\n";

        try {
            CommandResult commandResult = new AddTimeCommand(validPerson.getName(),
                timeIntervalArrayList).execute(modelStub);
            assertEquals(String.format(AddTimeCommand.MESSAGE_SUCCESS + status, Messages.format(validPerson.getName())),
                commandResult.getFeedbackToUser());

            assertEquals("\n" + TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A.toString(),
                modelStub.personList.get(0).getTime().toString());
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_addMultipleTimeToPersonWithOneExistingOverlappingTime_addHalfSuccessfulClashAndAddMsg() {
        Person validPerson = new PersonBuilder().withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A)
            .build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson);
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP);

        String status = "There is a clash in these input timings with your existing timings:\n"
            + TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A.toString()
            + "\n" + "These times have been added:"
            + "\n" + TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP.toString()
            + "\n";

        try {
            CommandResult commandResult = new AddTimeCommand(validPerson.getName(),
                timeIntervalArrayList).execute(modelStub);
            assertEquals(String.format(AddTimeCommand.MESSAGE_SUCCESS + status, Messages.format(validPerson.getName())),
                commandResult.getFeedbackToUser());

            assertEquals("\n" + TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A.toString()
                + "\n" + TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP.toString(),
                modelStub.personList.get(0).getTime().toString());
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_addMultipleTimeToPersonWithTwoExistingOverlappingTime_addUnSuccessfulClashMsg() {
        Person validPerson = new PersonBuilder().withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A)
            .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_FOUR_OVERLAP_A).build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson);
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A);

        String status = "There is a clash in these input timings with your existing timings:\n"
            + TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A.toString()
            + "\n" + TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A.toString()
            + "\n";

        try {
            CommandResult commandResult = new AddTimeCommand(validPerson.getName(),
                timeIntervalArrayList).execute(modelStub);
            assertEquals(String.format(AddTimeCommand.MESSAGE_SUCCESS + status, Messages.format(validPerson.getName())),
                commandResult.getFeedbackToUser());

            assertEquals("\n" + TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A.toString()
                + "\n" + TypicalTimeIntervals.TIME_INTERVAL_FOUR_OVERLAP_A.toString(),
                modelStub.personList.get(0).getTime().toString());
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_addMultipleTimeToPersonWithTwoExistingOverlappingTime_addHalfSuccessfulClashAndAddMsg() {
        Person validPerson = new PersonBuilder().withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A)
            .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_FOUR_OVERLAP_A).build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson);
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP);

        String status = "There is a clash in these input timings with your existing timings:\n"
            + TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A.toString()
            + "\n" + "These times have been added:"
            + "\n" + TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP.toString()
            + "\n";

        try {
            CommandResult commandResult = new AddTimeCommand(validPerson.getName(),
                timeIntervalArrayList).execute(modelStub);
            assertEquals(String.format(AddTimeCommand.MESSAGE_SUCCESS + status, Messages.format(validPerson.getName())),
                commandResult.getFeedbackToUser());

            assertEquals("\n" + TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A.toString()
                    + "\n" + TypicalTimeIntervals.TIME_INTERVAL_FOUR_OVERLAP_A.toString()
                    + "\n" + TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP.toString(),
                modelStub.personList.get(0).getTime().toString());
        } catch (CommandException e) {
            fail();
        }
    }


    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Person deletePerson(String personName) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasPerson(Name personName) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Pair<Person, Group> groupPerson(String personName, String groupName) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public String addTimeToPerson(Name toAddPerson, ArrayList<TimeInterval> toAddTime) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public TimeIntervalList getTimeFromPerson(Name personName) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public String deleteTimeFromPerson(Name personName, ArrayList<TimeInterval> toDeleteTime) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addGroup(Group g) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Group deleteGroup(String groupName) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Group findGroup(String groupName) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Group addGroupRemark(String groupName, GroupRemark groupRemark) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public String addTimeToGroup(Group toAdd, ArrayList<TimeInterval> toAddTime) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public String deleteTimeFromGroup(Group group, ArrayList<TimeInterval> toDeleteTime) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public TimeIntervalList getTimeFromGroup(Group group) throws CommandException {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasEmail(Person toAdd) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasPhone(Person toAdd) {
            throw new AssertionError("This method should not be called");
        }
    }


    private class ModelStubWithPerson extends ModelStub {
        private final Name personName;

        private final ArrayList<Person> personList = new ArrayList<>();

        ModelStubWithPerson(Name personName) {
            requireNonNull(personName);
            this.personName = personName;
        }

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            personList.add(person);
            personName = person.getName();
        }

        @Override
        public boolean hasPerson(Name personName) {
            requireNonNull(personName);
            return this.personName.equals(personName);
        }

        @Override
        public String addTimeToPerson(Name personName, ArrayList<TimeInterval> toAddTime) {
            Person p = personList.get(0);
            String status = p.addFreeTime(toAddTime);
            return status;
        }

    }



}
