package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK_OTHERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK_SPECIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK_UNICODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MON;
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

public class GroupRemarkCommandTest {
    @Test
    public void constructor_nullGroupNullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupRemarkCommand(null, null));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        GroupRemark groupRemark = new GroupRemark(VALID_GROUP_REMARK);
        assertThrows(NullPointerException.class, () -> new GroupRemarkCommand(null, groupRemark));
    }

    @Test
    public void constructor_nullRemarkThrowsNullPointerException() {
        Group validGroup = new GroupBuilder().build();
        GroupRemark groupRemark = new GroupRemark(VALID_GROUP_REMARK);
        assertThrows(NullPointerException.class, () -> new GroupRemarkCommand(validGroup.getGroupName(), null));
    }

    @Test
    public void execute_addGroupRemarkSuccess() throws Exception {
        Group validGroup = new GroupBuilder().build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
        assertEquals(" ", validGroup.getGroupRemark().toString());
        GroupRemark groupRemark = new GroupRemark(VALID_GROUP_REMARK);
        CommandResult commandResult = new GroupRemarkCommand(validGroup.getGroupName(), groupRemark).execute(modelStub);

        // Group remark has been added
        assertEquals(VALID_GROUP_REMARK, validGroup.getGroupRemark().toString());
    }

    @Test
    public void execute_groupWithMeeting_addGroupRemarkSuccess() throws Exception {
        Group validGroupWithMeeting = new GroupBuilder().withTimeIntervalList(VALID_TIME_MON).build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroupWithMeeting);
        assertEquals(" ", validGroupWithMeeting.getGroupRemark().toString());
        GroupRemark groupRemark = new GroupRemark(VALID_GROUP_REMARK);
        CommandResult commandResult =
                new GroupRemarkCommand(validGroupWithMeeting.getGroupName(), groupRemark).execute(modelStub);

        // Group remark has been added
        assertEquals(VALID_GROUP_REMARK, validGroupWithMeeting.getGroupRemark().toString());
    }

    @Test
    public void execute_alphanumericSpecialCharacters_addGroupRemarkSuccess() throws Exception {
        Group validGroup = new GroupBuilder().build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
        assertEquals(" ", validGroup.getGroupRemark().toString());
        GroupRemark groupRemark = new GroupRemark(VALID_GROUP_REMARK_SPECIAL);
        CommandResult commandResult = new GroupRemarkCommand(validGroup.getGroupName(), groupRemark).execute(modelStub);

        // Group remark has been added
        assertEquals(VALID_GROUP_REMARK_SPECIAL, validGroup.getGroupRemark().toString());
    }

    @Test
    public void execute_unicodeCharacters_addGroupRemarkSuccess() throws Exception {
        Group validGroup = new GroupBuilder().build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
        assertEquals(" ", validGroup.getGroupRemark().toString());
        GroupRemark groupRemark = new GroupRemark(VALID_GROUP_REMARK_UNICODE);
        CommandResult commandResult = new GroupRemarkCommand(validGroup.getGroupName(), groupRemark).execute(modelStub);

        // Group remark has been added
        assertEquals(VALID_GROUP_REMARK_UNICODE, validGroup.getGroupRemark().toString());
    }

    @Test
    public void execute_validCharacters_addGroupRemarkSuccess() throws CommandException {
        Group validGroup = new GroupBuilder().build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
        GroupRemark groupRemark = new GroupRemark(VALID_GROUP_REMARK_OTHERS);
        CommandResult commandResult = new GroupRemarkCommand(validGroup.getGroupName(), groupRemark).execute(modelStub);
        assertEquals(VALID_GROUP_REMARK_OTHERS, validGroup.getGroupRemark().toString());
    }

    /**
     * A default model stub that has all methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void assignGroup(Person person, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unassignGroup(Person person, Group group) {
            throw new AssertionError("This method should not be called.");
        }
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
     * A Model stub that contains a single person and a time interval.
     */
    private class ModelStubWithGroup extends ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.equals(group);
        }

        public boolean hasTime(TimeInterval timeInterval) {
            requireNonNull(timeInterval);
            return this.group.hasTime(timeInterval);
        }

        /**
         * Adds remarks to a group.
         *
         * @param groupName Group to be modified.
         * @param groupRemark Remark to be added.
         * @return The modified group.
         * @throws CommandException if the remark cannot be added.
         */
        public Group addGroupRemark(String groupName, GroupRemark groupRemark) throws CommandException {
            Group group = this.group;
            group.setGroupRemark(groupRemark);
            return group;
        }
    }
}
