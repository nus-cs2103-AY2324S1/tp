package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.CS2103;
import static seedu.address.testutil.TypicalPersons.ALICE;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteGroupCommandTest {

    @Test
    public void execute_existingGroupWithNoMembers_deleteSuccessful() throws Exception {
        Group validGroup = new GroupBuilder().withName(GroupBuilder.DEFAULT_GROUP_NAME).build();
        DeleteGroupCommandTest.ModelStubWithGroupToDelete modelStub =
                new DeleteGroupCommandTest.ModelStubWithGroupToDelete(validGroup);

        CommandResult commandResult =
                new DeleteGroupCommand(validGroup.getGroupName()).execute(modelStub);

        assertEquals(String.format(MESSAGE_DELETE_GROUP_SUCCESS, GroupBuilder.DEFAULT_GROUP_NAME),
                commandResult.getFeedbackToUser());
        ArrayList<Group> emptyGroupList = new ArrayList<>();
        assertEquals(emptyGroupList, modelStub.groups); // checks group removed from contact list

    }

    @Test
    public void execute_existingGroupWithMembers_deleteSuccessful() throws Exception {
        Person member = new PersonBuilder().withName(PersonBuilder.DEFAULT_NAME).build();
        Group validGroup =
                new GroupBuilder().withName(GroupBuilder.DEFAULT_GROUP_NAME).build();

        validGroup.addPerson(member);
        member.addGroup(validGroup);

        DeleteGroupCommandTest.ModelStubWithGroupToDelete modelStub =
                new DeleteGroupCommandTest.ModelStubWithGroupToDelete(validGroup);
        CommandResult commandResult =
                new DeleteGroupCommand(validGroup.getGroupName()).execute(modelStub);

        assertEquals(String.format(String.format(MESSAGE_DELETE_GROUP_SUCCESS, GroupBuilder.DEFAULT_GROUP_NAME)),
                commandResult.getFeedbackToUser());

        ArrayList<Group> emptyGroupList = new ArrayList<>();
        assertEquals(emptyGroupList, modelStub.groups); // checks group removed from contact list
        assertFalse(modelStub.persons.get(0).containsGroup(validGroup)); // checks if group is deleted from members
    }

    @Test
    public void execute_nonexistentGroup_throwsCommandException() {
        // group does not exist in address book
        Group nonexistentGroup = new GroupBuilder().build();
        DeleteGroupCommand deleteGroupCommand =
                new DeleteGroupCommand(nonexistentGroup.getGroupName());
        DeleteGroupCommandTest.ModelStub modelStub
                = new DeleteGroupCommandTest.ModelStubDeletingGroup();

        assertThrows(CommandException.class, Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND,
                () -> deleteGroupCommand.execute(modelStub));

        // empty input
        String emptyInput = "";
        assertThrows(CommandException.class, Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND,
                () -> new DeleteGroupCommand(emptyInput).execute(modelStub));
    }

    @Test
    public void equals() {
        Group CS2103 = new GroupBuilder().withName("CS2103").build();
        Group CS2103T = new GroupBuilder().withName("CS2103T").build();
        DeleteGroupCommand deleteCS2103Command = new DeleteGroupCommand(CS2103.getGroupName());
        DeleteGroupCommand deleteCS2103TCommand = new DeleteGroupCommand(CS2103T.getGroupName());

        // same object -> returns true
        assertTrue(deleteCS2103Command.equals(deleteCS2103Command));

        // same values -> returns true
        DeleteGroupCommand deleteCS2103CommandCopy = new DeleteGroupCommand(CS2103.getGroupName());
        assertTrue(deleteCS2103Command.equals(deleteCS2103CommandCopy));

        // different types -> returns false
        assertFalse(deleteCS2103Command.equals(1));

        // null -> returns false
        assertFalse(deleteCS2103Command.equals(null));

        // different person -> returns false
        assertFalse(deleteCS2103Command.equals(deleteCS2103TCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(CS2103.getGroupName());
        String expected =
                DeleteGroupCommand.class.getCanonicalName() + "{group name=" + CS2103.getGroupName() + "}";
        assertEquals(expected, deleteGroupCommand.toString());
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
     * A Model stub that deletes the group.
     */
    private class ModelStubDeletingGroup extends DeleteGroupCommandTest.ModelStub {
        final ArrayList<Person> persons = new ArrayList<>();

        final ArrayList<Group> groups = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return persons.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public Group deleteGroup(String groupName) throws CommandException {
            requireNonNull(groupName);
            for (Group group : groups) {
                if (group.getGroupName().equals(groupName)) {
                    groups.remove(group);
                    return group;
                }
            }
            throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groups.contains(group);
        }

        @Override
        public AddressBook getAddressBook() {
            return new AddressBook();
        }

    }

    /**
     * A Model stub that contains a single group to be deleted.
     */
    private class ModelStubWithGroupToDelete extends DeleteGroupCommandTest.ModelStubDeletingGroup {

        ModelStubWithGroupToDelete(Group group) {
            requireNonNull(group);
            super.groups.add(group);
            group.getListOfGroupMates().forEach(
                    p -> super.persons.add(p)
            );
        }
    }

}
