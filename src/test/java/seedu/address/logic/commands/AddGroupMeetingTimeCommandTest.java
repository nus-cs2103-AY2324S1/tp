package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddGroupMeetingTimeCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MON_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_TUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_WED;
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
import seedu.address.logic.parser.ParserUtil;
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

public class AddGroupMeetingTimeCommandTest {

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupMeetingTimeCommand(null, null));
    }

    @Test
    public void execute_groupWithNoMeeting_groupTimeIntervalAdditionSuccess() throws Exception {
        Group validGroup = new GroupBuilder().build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);

        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));

        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));

        CommandResult commandResult = new AddGroupMeetingTimeCommand(validGroup, validTimeInterval).execute(modelStub);

        // Time interval has been added
        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
    }

    @Test
    public void execute_groupWithMeeting_groupTimeIntervalAdditionSuccess() throws Exception {
        Group validGroupWithMeeting = new GroupBuilder().withTimeIntervalList(VALID_TIME_MON).build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroupWithMeeting);

        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));

        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));

        CommandResult commandResult =
                new AddGroupMeetingTimeCommand(validGroupWithMeeting, validTimeInterval).execute(modelStub);

        // Time interval has been added
        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
    }

    @Test
    public void execute_multipleGroupTimeIntervals_additionSuccess() throws Exception {
        Group validGroupWithMeeting = new GroupBuilder().withTimeIntervalList(VALID_TIME_MON).build();
        Group validGroup = new GroupBuilder().build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);

        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_WED));


        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_WED)));

        CommandResult commandResult =
                new AddGroupMeetingTimeCommand(validGroupWithMeeting, validTimeInterval).execute(modelStub);

        // Time interval has been added
        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_WED)));

    }

    @Test
    public void execute_duplicateTimeInterval_groupTimeIntervalAdditionFail() throws Exception {
        Group validGroupWithMeeting = new GroupBuilder().withTimeIntervalList(VALID_TIME_MON).build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroupWithMeeting);

        ArrayList<TimeInterval> invalidTimeInterval = new ArrayList<>();
        invalidTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));

        CommandResult expectedOutput = new CommandResult(String.format(MESSAGE_SUCCESS
                + "There is a clash in these input timings with your existing timings:\n"
                + "MON 1300 - MON 1400 " + "\n", Messages.format(validGroupWithMeeting)));
        AddGroupMeetingTimeCommand actualOutput =
                new AddGroupMeetingTimeCommand(validGroupWithMeeting, invalidTimeInterval);

        assertEquals(expectedOutput, actualOutput.execute(modelStub));
    }

    @Test
    public void execute_overlappingTimeInterval_groupTimeIntervalAdditionFail() throws Exception {
        Group validGroupWithMeeting = new GroupBuilder().withTimeIntervalList(VALID_TIME_MON).build();

        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroupWithMeeting);

        ArrayList<TimeInterval> invalidTimeInterval = new ArrayList<>();
        invalidTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON_2));

        CommandResult expectedOutput = new CommandResult(String.format(MESSAGE_SUCCESS
                + "There is a clash in these input timings with your existing timings:\n"
                + "MON 1330 - MON 1430 " + "\n", Messages.format(validGroupWithMeeting)));
        AddGroupMeetingTimeCommand actualOutput =
                new AddGroupMeetingTimeCommand(validGroupWithMeeting, invalidTimeInterval);

        assertEquals(expectedOutput, actualOutput.execute(modelStub));
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
         * Adds meeting time to a group.
         *
         * @param toAdd The group to be modified.
         * @param toAddTime ArrayList of Time Intervals to be added.
         * @return String showing added meeting times and clashes (if any).
         * @throws CommandException if the times cannot be added.
         */
        @Override
        public String addTimeToGroup(Group toAdd, ArrayList<TimeInterval> toAddTime) throws CommandException {
            requireNonNull(toAdd);
            Group groupInModel = this.group;
            try {
                return groupInModel.addTime(toAddTime);
            } catch (CommandException e) {
                throw new CommandException(e.getMessage());
            }
        }
    }
}
