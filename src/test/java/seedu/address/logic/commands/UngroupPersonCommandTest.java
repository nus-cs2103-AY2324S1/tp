package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
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
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;

public class UngroupPersonCommandTest {

    @Test
    public void constructor_nullPersonNullGroupThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UngroupPersonCommand(null, null));
    }

    @Test
    public void execute_ungroupPersonSuccess() throws CommandException {
        Group validGroup = new GroupBuilder().build();
        Person validPerson = new PersonBuilder().build();

        ModelStubWithGroupAndSingleMember modelStub = new ModelStubWithGroupAndSingleMember(validGroup, validPerson);
        assertTrue(validGroup.contains(validPerson));

        CommandResult commandResult = new UngroupPersonCommand(validGroup.getGroupName(),
                validPerson.getName().toString()).execute(modelStub);

        assertFalse(validGroup.contains(validPerson));
    }

    @Test
    public void execute_personNotInGroup_ungroupPersonFailure() {
        Group validGroup = new GroupBuilder().build();
        Person validPerson = new PersonBuilder().build();

        ModelStubWithEmptyGroup modelStub = new ModelStubWithEmptyGroup(validGroup, validPerson);
        assertFalse(validGroup.contains(validPerson));

        assertThrows(CommandException.class, () -> new UngroupPersonCommand(validGroup.getGroupName(),
                validPerson.getName().toString()).execute(modelStub));
    }

    /**
     * A default model stub that has all methods failing.
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
        public boolean hasPerson(Name personName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person deletePerson(String personName) throws CommandException {
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
        public Group deleteGroup(String groupName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Pair<Person, Group> groupPerson(String personName, String groupName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group addGroupRemark(String groupName, GroupRemark groupRemark) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String addTimeToPerson(Name toAddPerson, ArrayList<TimeInterval> toAddFreeTime) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TimeIntervalList getTimeFromPerson(Name personName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String deleteTimeFromPerson(Name personName,
                                           ArrayList<TimeInterval> listOfTimesToDelete) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group findGroup(String groupName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String addTimeToGroup(Group toAdd, ArrayList<TimeInterval> toAddTime) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String deleteTimeFromGroup(Group group,
                                          ArrayList<TimeInterval> toDeleteTime) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        public TimeIntervalList getTimeFromGroup(Group group) throws CommandException {
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

    /**
     * A Model stub that contains a single person in a group.
     */
    private class ModelStubWithGroupAndSingleMember extends ModelStub {
        private final Group group;
        private final Person person;
        ModelStubWithGroupAndSingleMember(Group group, Person person) throws CommandException {
            requireAllNonNull(group, person);
            this.group = group;
            this.person = person;
            group.addPerson(person);
            person.addGroup(group);
        }

        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.equals(group);
        }

        /**
         * Unassigns person to group.
         *
         * @param person Person to be grouped.
         * @param group  Group in consideration.
         * @throws CommandException if person has already been assigned to group.
         */
        public void unassignGroup(Person person, Group group) throws CommandException {
            group.removePerson(person);
            person.removeGroup(group);
        }

        /**
         * Removes person from group.
         *
         * @param personName String representing person name.
         * @param groupName  String representing group name.
         * @return Pair containing the Person and the Group.
         * @throws CommandException if the person cannot be removed from the group.
         */
        @Override
        public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws CommandException {
            Person person = this.person;
            Group group = this.group;
            this.unassignGroup(person, group);
            Pair<Person, Group> output = new Pair<>(person, group);
            return output;
        }
    }

    /**
     * A Model stub that contains a single person in a group.
     */
    private class ModelStubWithEmptyGroup extends ModelStub {
        private final Group group;
        private final Person person;

        ModelStubWithEmptyGroup(Group group, Person person) {
            requireAllNonNull(group, person);
            this.group = group;
            this.person = person;
        }

        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.equals(group);
        }

        /**
         * Unassigns person to group.
         *
         * @param person Person to be grouped.
         * @param group  Group in consideration.
         * @throws CommandException if person has already been assigned to group.
         */
        public void unassignGroup(Person person, Group group) throws CommandException {
            group.removePerson(person);
            person.removeGroup(group);
        }

        /**
         * Removes person from group.
         *
         * @param personName String representing person name.
         * @param groupName  String representing group name.
         * @return Pair containing the Person and the Group.
         * @throws CommandException if the person cannot be removed from the group.
         */
        @Override
        public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws CommandException {
            Person person = this.person;
            Group group = this.group;
            this.unassignGroup(person, group);
            Pair<Person, Group> output = new Pair<>(person, group);
            return output;
        }
    }
}
