//package seedu.address.logic.commands;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.util.Pair;
//import org.junit.jupiter.api.Test;
//import seedu.address.commons.core.GuiSettings;
//import seedu.address.logic.Messages;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.logic.parser.ParserUtil;
//import seedu.address.model.*;
//import seedu.address.model.group.Group;
//import seedu.address.model.group.GroupRemark;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.GroupBuilder;
//
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//
//import static java.util.Objects.requireNonNull;
//import static org.junit.jupiter.api.Assertions.*;
//import static seedu.address.logic.commands.AddGroupMeetingTimeCommand.MESSAGE_SUCCESS;
//import static seedu.address.logic.commands.CommandTestUtil.*;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.PersonBuilder.DEFAULT_NAME;
//
//public class UngroupPersonCommandTest {
//    Group validGroupWithMeeting = new GroupBuilder().withTimeIntervalList(VALID_TIME_MON).build();
//    Group validGroup = new GroupBuilder().build();
//    Group validGroupWithMembers = new GroupBuilder().withListOfGroupMates(DEFAULT_NAME).build();
//
//    @Test
//    public void constructor_nullPerson_nullGroup_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new UngroupPersonCommand(null, null));
//    }
//
//    @Test
//    public void execute_ungroupPersonSuccess() {
//        ModelStubWithGroupAndMembers modelStub = new ModelStubWithGroupAndMembers()
//    }
//
//    @Test
//    public void execute_groupWithNoMeeting_groupTimeIntervalAdditionSuccess() throws Exception {
//        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
//
//        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
//        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));
//
//        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
//
//        CommandResult commandResult = new AddGroupMeetingTimeCommand(validGroup, validTimeInterval).execute(modelStub);
//
//        // Time interval has been added
//        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
//    }
//
//    @Test
//    public void execute_groupWithMeeting_groupTimeIntervalAdditionSuccess() throws Exception {
//        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroupWithMeeting);
//
//        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
//        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));
//
//        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
//        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
//
//        CommandResult commandResult = new AddGroupMeetingTimeCommand(validGroupWithMeeting, validTimeInterval).execute(modelStub);
//
//        // Time interval has been added
//        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
//        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
//    }
//
//    @Test
//    public void execute_multipleGroupTimeIntervals_additionSuccess() throws Exception {
//        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroup);
//
//        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
//        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));
//        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));
//        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_WED));
//
//
//        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
//        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
//        assertFalse(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_WED)));
//
//        CommandResult commandResult = new AddGroupMeetingTimeCommand(validGroupWithMeeting, validTimeInterval).execute(modelStub);
//
//        // Time interval has been added
//        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_MON)));
//        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_TUE)));
//        assertTrue(modelStub.hasTime(ParserUtil.parseEachInterval(VALID_TIME_WED)));
//
//    }
//
//    @Test
//    public void execute_duplicateTimeInterval_groupTimeIntervalAdditionFail() throws Exception {
//        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroupWithMeeting);
//
//        ArrayList<TimeInterval> invalidTimeInterval = new ArrayList<>();
//        invalidTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));
//
//        CommandResult expectedOutput = new CommandResult(String.format(MESSAGE_SUCCESS
//                + "There is a clash in these input timings with your existing timings:\n"
//                + "MON 1300 - MON 1400 " + "\n", Messages.format(validGroupWithMeeting)));
//        AddGroupMeetingTimeCommand actualOutput = new AddGroupMeetingTimeCommand(validGroupWithMeeting, invalidTimeInterval);
//
//        assertEquals(expectedOutput, actualOutput.execute(modelStub));
//    }
//
//    @Test
//    public void execute_overlappingTimeInterval_groupTimeIntervalAdditionFail() throws Exception {
//        ModelStubWithGroup modelStub = new ModelStubWithGroup(validGroupWithMeeting);
//
//        ArrayList<TimeInterval> invalidTimeInterval = new ArrayList<>();
//        invalidTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON_2));
//
//        CommandResult expectedOutput = new CommandResult(String.format(MESSAGE_SUCCESS
//                + "There is a clash in these input timings with your existing timings:\n"
//                + "MON 1330 - MON 1430 " + "\n", Messages.format(validGroupWithMeeting)));
//        AddGroupMeetingTimeCommand actualOutput = new AddGroupMeetingTimeCommand(validGroupWithMeeting, invalidTimeInterval);
//
//        assertEquals(expectedOutput, actualOutput.execute(modelStub));
//    }
//
//
//    /**
//     * A default model stub that has all methods failing.
//     */
//    private class ModelStub implements Model {
//        @Override
//        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyUserPrefs getUserPrefs() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public GuiSettings getGuiSettings() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setGuiSettings(GuiSettings guiSettings) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getAddressBookFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBookFilePath(Path addressBookFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addPerson(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBook(ReadOnlyAddressBook newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyAddressBook getAddressBook() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasPerson(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasPerson(Name personName) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Person deletePerson(String personName) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Person> getFilteredPersonList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Group> getFilteredGroupList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredPersonList(Predicate<Person> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredGroupList(Predicate<Group> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addGroup(Group g) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Group deleteGroup(String groupName) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Pair<Person, Group> groupPerson(String personName, String groupName) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Group addGroupRemark(String groupName, GroupRemark groupRemark) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public String addTimeToPerson(Name toAddPerson, ArrayList<TimeInterval> toAddFreeTime) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasGroup(Group group) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public TimeIntervalList getTimeFromPerson(Name personName) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public String deleteTimeFromPerson(Name personName, ArrayList<TimeInterval> listOfTimesToDelete) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Group findGroup(String groupName) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public String addTimeToGroup(Group toAdd, ArrayList<TimeInterval> toAddTime) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public String deleteTimeFromGroup(Group group,
//                                          ArrayList<TimeInterval> toDeleteTime) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        public TimeIntervalList getTimeFromGroup(Group group) throws CommandException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasPhone(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasEmail(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    /**
//     * A Model stub that contains a single person and a time interval.
//     */
//    private class ModelStubWithGroupAndMembers extends ModelStub {
//        private final Group group;
//
//        ModelStubWithGroupAndMembers(Group group) {
//            requireNonNull(group);
//            this.group = group;
//        }
//
//        public boolean hasGroup(Group group) {
//            requireNonNull(group);
//            return this.group.equals(group);
//        }
//
//        /**
//         * Unassigns person to group.
//         *
//         * @param person Person to be grouped.
//         * @param group  Group in consideration.
//         * @throws CommandException if person has already been assigned to group.
//         */
//        private void unassignGroup(Person person, Group group) throws CommandException {
//            group.removePerson(person);
//            person.removeGroup(group);
//        }
//
//        /**
//         * Removes person from group.
//         *
//         * @param personName String representing person name.
//         * @param groupName  String representing group name.
//         * @return Pair containing the Person and the Group.
//         * @throws CommandException if the person cannot be removed from the group.
//         */
//        @Override
//        public Pair<Person, Group> ungroupPerson(String personName, String groupName) throws CommandException {
//            Person person = this.listOfGroupMates.get(0);
//            Group group = this.group;
//            this.unassignGroup(person, group);
//            Pair<Person, Group> output = new Pair<>(person, group);
//            return output;
//        }
//
//        /**
//         * Adds meeting time to a group.
//         *
//         * @param toAdd The group to be modified.
//         * @param toAddTime ArrayList of Time Intervals to be added.
//         * @return String showing added meeting times and clashes (if any).
//         * @throws CommandException if the times cannot be added.
//         */
//        @Override
//        public String addTimeToGroup(Group toAdd, ArrayList<TimeInterval> toAddTime) throws CommandException {
//            requireNonNull(toAdd);
//            Group groupInModel = this.group;
//            try {
//                return groupInModel.addTime(toAddTime);
//            } catch (CommandException e) {
//                throw new CommandException(e.getMessage());
//            }
//        }
//    }
//}
