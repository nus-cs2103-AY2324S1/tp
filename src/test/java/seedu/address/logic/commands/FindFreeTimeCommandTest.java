package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.FindFreeTimeCommand.MESSAGE_NOT_ALL_FREE;
import static seedu.address.logic.commands.FindFreeTimeCommand.MESSAGE_SUCCESS;
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
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Duration;
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
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalTimeIntervals;




public class FindFreeTimeCommandTest {

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindFreeTimeCommand(null, null));
    }

    // Group does not exist
    @Test
    public void execute_groupDoesNotExist_unSuccesful() {
        ModelStubGroup model = new ModelStubGroup("CS2100");

        Command command = new FindFreeTimeCommand("CS2103", new Duration(60));

        assertThrows(CommandException.class, Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND, () -> command.execute(model));
    }

    // One person in group not free
    @Test
    public void execute_contactNotFree_unSuccessful() {
        Person person = new PersonBuilder().withName("John")
                            .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A)
                            .build();

        Group groupOne = new GroupBuilder().withName("CS2102").build();
        try {
            groupOne.addPerson(person);
        } catch (CommandException e) {
            fail();
        }
        ModelStubGroup model = new ModelStubGroup("CS2102");
        Group groupTwo = new GroupBuilder().withName("CS2100").withListOfGroupMates("Kailash").build();
        model.addGroup(groupTwo);

        Command command = new FindFreeTimeCommand("CS2100", new Duration(60));

        String message = String.format(MESSAGE_NOT_ALL_FREE, "Kailash");

        assertThrows(CommandException.class, String.format(message), () -> command.execute(model));

    }

    // Group exist but empty
    @Test
    public void execute_groupIsEmpty_unSuccessful() {
        Group group = new GroupBuilder().withName("CS2100").build();

        ModelStubGroup model = new ModelStubGroup(group);
        String message = "Group is empty";

        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(60));

        assertThrows(CommandException.class, message, () -> command.execute(model));

    }

    // One person exactly fit duration
    @Test
    public void execute_personIsFree_successful() {
        Group group = new GroupBuilder().withName("CS2100").build();
        Person person = new PersonBuilder().withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A).build();
        try {
            group.addPerson(person);
        } catch (CommandException e) {
            fail();
        }

        ModelStubGroup model = new ModelStubGroup(group);
        String message = MESSAGE_SUCCESS + "1. MON 1000 - MON 1100 \n";

        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(60));
        try {
            CommandResult result = command.execute(model);
            assertEquals(message, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            fail();
        }

    }

    // One person more than duration
    @Test
    public void execute_personNotFree_unSuccessful() {
        Group group = new GroupBuilder().withName("CS2100").build();
        Person person = new PersonBuilder().withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A).build();
        try {
            group.addPerson(person);
        } catch (CommandException e) {
            fail();
        }

        ModelStubGroup model = new ModelStubGroup(group);
        String message = MESSAGE_SUCCESS;
        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(61));

        try {
            CommandResult result = command.execute(model);
            assertEquals(message, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            fail();
        }

    }


    // One person less than duration


    @Test
    public void execute_personFree_successful() {
        Group group = new GroupBuilder().withName("CS2100").build();
        Person person = new PersonBuilder().withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A).build();
        try {
            group.addPerson(person);
        } catch (CommandException e) {
            fail();
        }

        ModelStubGroup model = new ModelStubGroup(group);
        String message = MESSAGE_SUCCESS
                            + "1. MON 1000 - MON 1100 \n";
        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(59));
        try {
            CommandResult result = command.execute(model);
            assertEquals(message, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            fail();
        }

    }


    // time interval end and start are the same, 1030 - 1100 and 1100 - 1130
    // can't have a meeting from 1100 - 1100
    @Test
    public void execute_overlapStartEndSame_unSuccessful() {
        Person personOne = new PersonBuilder().withName("Zhen Dong")
                            .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A)
                            .build(); // 10 - 11
        Person personTwo = new PersonBuilder().withName("Kailash")
                            .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A)
                            .build(); // 11 - 12

        Group group = new GroupBuilder().withName("CS2100").build();

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
        } catch (CommandException e) {
            fail();
        }

        ModelStubGroup model = new ModelStubGroup(group);
        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(1));
        String message = MESSAGE_SUCCESS;
        try {
            CommandResult result = command.execute(model);
            assertEquals(message, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            fail();
        }

    }

    // time interval overlap for 2 people
    @Test
    public void execute_overlapTwo_successful() {
        Person personOne = new PersonBuilder().withName("Zhen Dong")
                           .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A)
                           .build(); // 1030 - 1130
        personOne
            .addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A); // 1200 - 1300

        Person personTwo = new PersonBuilder().withName("Kailash")
                           .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A)
                           .build(); // 11 - 12
        personTwo
            .addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_HALFOVERLAP_A); // 1230 - 1300

        Group group = new GroupBuilder().withName("CS2100").build();

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
        } catch (CommandException e) {
            fail();
        }

        ModelStubGroup model = new ModelStubGroup(group);
        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(30));
        String message = MESSAGE_SUCCESS
                            + "1. MON 1100 - MON 1130 \n"
                            + "2. MON 1230 - MON 1300 \n";

        try {
            CommandResult result = command.execute(model);
            assertEquals(message, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            fail();
        }

    }

    // Have group and people with time but duration too long
    // time interval overlap for 2 people
    @Test
    public void execute_overlapTwo_unSuccessful() {
        Person personOne = new PersonBuilder().withName("Zhen Dong")
                           .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A)
                           .build(); // 1030 - 1130
        personOne
        .addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A); // 1200 - 1300

        Person personTwo = new PersonBuilder().withName("Kailash")
                           .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A)
                           .build(); // 11 - 12
        personTwo
        .addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_HALFOVERLAP_A); // 1230 - 1300

        Group group = new GroupBuilder().withName("CS2100").build();

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
        } catch (CommandException e) {
            fail();
        }

        ModelStubGroup model = new ModelStubGroup(group);
        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(31));
        String message = MESSAGE_SUCCESS;

        try {
            CommandResult result = command.execute(model);
            assertEquals(message, result.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            fail();
        }

    }



    // One person missing time interval
    @Test
    public void execute_missingInterval_unSuccessful() {
        Person personOne = new PersonBuilder().withName("Zhen Dong")
                           .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A)
                           .build(); // 1030 - 1130
        personOne
        .addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A); // 1200 - 1300

        Person personTwo = new PersonBuilder().withName("Kailash")
                           .withTimeInterval(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A)
                           .build(); // 11 - 12
        personTwo
        .addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_HALFOVERLAP_A); // 1230 - 1300

        Person personThree = new PersonBuilder().withName("Nicholas").build();

        Group group = new GroupBuilder().withName("CS2100").build();

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
            group.addPerson(personThree);
        } catch (CommandException e) {
            fail();
        }

        ModelStubGroup model = new ModelStubGroup(group);
        Command command = new FindFreeTimeCommand(group.getGroupName(), new Duration(30));
        String message = String.format(MESSAGE_NOT_ALL_FREE, personThree.getName().toString());


        assertThrows(CommandException.class, message, () -> command.execute(model));

    }







    private abstract class ModelStub implements Model {

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

    private class ModelStubGroup extends ModelStub {

        private final Group validGroup;

        private final GroupList groupList = new GroupList();

        ModelStubGroup(String groupName) {
            validGroup = new GroupBuilder().withName(groupName).build();
            groupList.add(validGroup);
        }
        ModelStubGroup(Group group) {
            requireNonNull(group);
            validGroup = group;
            groupList.add(validGroup);
        }

        public void addPersonToGroup(Person person) throws CommandException {
            validGroup.addPerson(person);
        }

        public void addGroup(Group group) {
            requireNonNull(group);
            groupList.add(group);
        }


        public Group findGroup(String groupName) throws CommandException {
            for (Group group : groupList) {
                if (group.nameEquals(groupName)) {
                    return group;
                }
            }
            throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);

        }




    }

}
