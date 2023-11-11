package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_TUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_WED;
import static seedu.address.logic.commands.ListTimeGroupCommand.MESSAGE_LISTTIME_GROUP_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
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

public class ListTimeGroupCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListTimeGroupCommand(null));
    }

    @Test
    public void execute_groupWithNoTimeIntervalListSuccess() throws Exception {
        Group validGroup = new GroupBuilder().build();
        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);

        // Person has time interval to be listed
        CommandResult commandResult =
                new ListTimeGroupCommand(validGroup).execute(modelStub);
        CommandResult expectedResult =
                new CommandResult(String.format(MESSAGE_LISTTIME_GROUP_SUCCESS,
                        validGroup.getGroupName()));

        // Time interval has been deleted
        assertEquals(expectedResult, commandResult);
    }

    @Test
    public void execute_personWithSingleTimeIntervalListSuccess() throws Exception {
        Group validGroup = new GroupBuilder().build();
        ModelStubGroupWithSingleTiming modelStub = new ModelStubGroupWithSingleTiming(validGroup);

        // Person has time interval to be listed
        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));
        CommandResult commandResult =
                new ListTimeGroupCommand(validGroup).execute(modelStub);
        CommandResult expectedResult =
                new CommandResult(String.format(MESSAGE_LISTTIME_GROUP_SUCCESS,
                        validGroup.getGroupName()) + "\nMON 1300 - MON 1400 ");

        // Time interval has been deleted
        assertEquals(expectedResult, commandResult);
    }

    @Test
    public void execute_personWithMultipleTimeIntervalsListSuccess() throws Exception {
        Group validGroup = new GroupBuilder().build();
        ModelStubGroupWithMultipleTimings modelStub = new ModelStubGroupWithMultipleTimings(validGroup);

        // Person has time interval to be listed
        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_WED));

        CommandResult commandResult =
                new ListTimeGroupCommand(validGroup).execute(modelStub);
        CommandResult expectedResult =
                new CommandResult(
                        String.format(MESSAGE_LISTTIME_GROUP_SUCCESS, validGroup.getGroupName())
                                + "\nMON 1300 - MON 1400 \nTUE 1300 - TUE 1400 \nWED 1300 - WED 1400 ");

        assertEquals(expectedResult, commandResult);
    }

    @Test
    public void execute_nonExistentPerson_listFail() throws Exception {
        Group validGroup = new GroupBuilder().build();
        ModelStubGroupWithMultipleTimings modelStub = new ModelStubGroupWithMultipleTimings(validGroup);
        Group invalidGroup = new Group("CS2100");
        assertThrows(CommandException.class, () -> new ListTimeGroupCommand(invalidGroup).execute(modelStub));
    }

    /**
     * A default model stub that have all of the methods failing.
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
        public String deleteTimeFromPerson(Name personName, ArrayList<TimeInterval> listOfTimesToDelete)
                throws CommandException {
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
     * A Model stub that contains a single group with no time interval.
     */
    private class ModelStubWithGroup extends ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) throws ParseException {
            requireNonNull(group);
            this.group = group;
        }

        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.equals(group);
        }

        @Override
        public TimeIntervalList getTimeFromGroup(Group group) throws CommandException {
            requireNonNull(group);
            return group.getTime();
        }
    }

    /**
     * A Model stub that contains a single group and a time interval.
     */
    private class ModelStubGroupWithSingleTiming extends ModelStub {
        private final Group group;

        ModelStubGroupWithSingleTiming(Group group) throws ParseException, CommandException {
            requireNonNull(group);
            this.group = group;
            this.group.addTime(ParserUtil.parseEachInterval(VALID_TIME_MON));
        }

        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.equals(group);
        }

        @Override
        public TimeIntervalList getTimeFromGroup(Group group) {
            requireNonNull(group);
            return group.getTime();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubGroupWithMultipleTimings extends ModelStub {
        private final Group group;

        ModelStubGroupWithMultipleTimings(Group group) throws ParseException, CommandException {
            requireNonNull(group);
            this.group = group;
            this.group.addTime(ParserUtil.parseEachInterval(VALID_TIME_MON));
            this.group.addTime(ParserUtil.parseEachInterval(VALID_TIME_TUE));
            this.group.addTime(ParserUtil.parseEachInterval(VALID_TIME_WED));
        }

        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.equals(group);
        }

        @Override
        public TimeIntervalList getTimeFromGroup(Group group) {
            requireNonNull(group);
            return group.getTime();
        }
    }

}
