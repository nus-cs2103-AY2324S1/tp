package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import seedu.address.model.group.GroupList;
import seedu.address.model.group.GroupRemark;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalGroups;
import seedu.address.testutil.TypicalPersons;

public class GroupPersonCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupPersonCommand(null, null));
        assertThrows(NullPointerException.class, () -> new GroupPersonCommand(null, ""));
        assertThrows(NullPointerException.class, () -> new GroupPersonCommand("", null));
    }

    @Test
    public void execute_personAndGroupAcceptedByModel_groupPersonSuccessful() throws Exception {
        ModelStubWithGroup modelStub = new ModelStubWithGroup();

        Person validPerson = TypicalPersons.ALICE;
        Group validGroup = TypicalGroups.CS2100;

        CommandResult commandResult = new GroupPersonCommand("Alice Pauline", "CS2100").execute(modelStub);

        assertEquals(String.format(GroupPersonCommand.MESSAGE_SUCCESS,
            validPerson.getName().toString(), Messages.format(validGroup)),
            commandResult.getFeedbackToUser());

        assertEquals(modelStub.groupsAdded.getGroup("CS2100"), validGroup);
        assertEquals(modelStub.personsAdded.stream()
                     .filter(g -> g.getName().toString().equals(validPerson.getName().toString())).findFirst()
                     .orElse(null), validPerson);
    }

    // no group
    @Test
    public void execute_personAndNoGroupAcceptedByModel_groupPersonUnSuccessful() {
        ModelStubWithGroup modelStub = new ModelStubWithGroup();
        Command groupPersonCommand = new GroupPersonCommand("Alice Pauline", "HELLO");

        assertThrows(CommandException.class,
            Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND, () -> groupPersonCommand.execute(modelStub));
    }

    @Test
    public void executenopersongroupsuccessful() {
        ModelStubWithGroup modelStub = new ModelStubWithGroup();

        Command groupPersonCommand = new GroupPersonCommand("Alice", "CS2100");

        assertThrows(CommandException.class,
            Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND, () -> groupPersonCommand.execute(modelStub));
    }

    // person already in group
    @Test
    public void executepersoningroupunsuccessful() throws Exception {
        ModelStubWithGroup modelStub = new ModelStubWithGroup();

        Person validPerson = TypicalPersons.ALICE;
        Group validGroup = TypicalGroups.CS2100;

        CommandResult commandResult = new GroupPersonCommand("Alice Pauline", "CS2100").execute(modelStub);

        assertEquals(String.format(GroupPersonCommand.MESSAGE_SUCCESS,
            validPerson.getName().toString(), Messages.format(validGroup)),
            commandResult.getFeedbackToUser());

        assertEquals(modelStub.groupsAdded.getGroup("CS2100"), validGroup);
        assertEquals(modelStub.personsAdded.stream()
                        .filter(g -> g.getName().toString().equals(validPerson.getName().toString()))
                        .findFirst().orElse(null), validPerson);

        Command groupPersonCommand = new GroupPersonCommand("Alice Pauline", "CS2100");

        String message = String.format("%s is already in this group: %s", "Alice Pauline", "CS2100");

        assertThrows(CommandException.class,
            message, () -> groupPersonCommand.execute(modelStub));
    }


    private class ModelStub implements Model {

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Name personName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person deletePerson(String personName) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group g) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group deleteGroup(String groupName) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Pair<Person, Group> groupPerson(String personName, String groupName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group addGroupRemark(String groupName, GroupRemark groupRemark) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String addTimeToPerson(Name personName, ArrayList<TimeInterval> freeTime) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TimeIntervalList getTimeFromPerson(Name personName) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String deleteTimeFromPerson(Name personName, ArrayList<TimeInterval> listOfTimesToDelete)
                throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group findGroup(String groupName) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String addTimeToGroup(Group toAdd, ArrayList<TimeInterval> toAddTime) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String deleteTimeFromGroup(Group group, ArrayList<TimeInterval> toDeleteTime) throws AssertionError {
            throw new AssertionError("This method should not be called.");

        }

        public TimeIntervalList getTimeFromGroup(Group group) throws AssertionError {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPhone(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmail(Person person) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithGroup extends ModelStub {

        private final ArrayList<Person> personsAdded;
        private final GroupList groupsAdded;

        ModelStubWithGroup() {
            this.personsAdded = TypicalPersons.getTypicalPersons();
            this.groupsAdded = TypicalGroups.getTypicalPGroup();
        }

        @Override
        public Pair<Person, Group> groupPerson(String personName, String groupName)
                throws CommandException {
            // both throw exception if not exists exact match
            Person person = personsAdded.stream()
                            .filter(g -> g.getName().toString().equals(personName))
                            .findFirst().orElse(null);

            Group group = groupsAdded.getGroup(groupName);

            if (person == null) {
                throw new CommandException(Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND);
            }
            if (group == null) {
                throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
            }
            group.addPerson(person);
            person.addGroup(group);

            Pair<Person, Group> output = new Pair<>(person, group);
            return output;

        }


    }


}
