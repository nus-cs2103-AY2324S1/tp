package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
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
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.TypicalGroups;
import seedu.address.testutil.TypicalPersons;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

public class FindFreeTimeCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindFreeTimeCommand(null, null));
    }

    @Test
    public void execute_FindFreeTime_Successful() throws Exception {
        ModelStubWithTime modelStub = new ModelStubWithTime();

        Person memberOne = TypicalPersons.KAILASH;
        Person memberTwo = TypicalPersons.NIC;
        Person memberThree = TypicalPersons.ZHENDONG;

        Group group = TypicalGroups.CS2103T;
        Duration duration = new Duration(10);

        Command commandSuccess = new FindFreeTimeCommand("CS2103T", duration);

//        CommandResult commandResult = new GroupPersonCommand("Alice Pauline", "CS2100").execute(modelStub);
//
//        assertEquals(String.format(GroupPersonCommand.MESSAGE_SUCCESS,
//        validPerson.getName().toString(), Messages.format(validGroup)),
//        commandResult.getFeedbackToUser());
//
//        assertEquals(modelStub.groupsAdded.getGroup("CS2100"), validGroup);
//        assertEquals(modelStub.personsAdded.stream()
//                     .filter(g -> g.getName().toString().equals(validPerson.getName().toString())).findFirst()
//                     .orElse(null), validPerson);
    }



    private abstract class ModelStub implements Model {

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
        public abstract Group findGroup(String groupName) throws CommandException;

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

    private class ModelStubWithTime extends ModelStub {
       // group 2103
       // Alice Pauline mon 1400 - mon 1600
       // Bob  mon
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
       private ArrayList<Person> personsAdded;
       private GroupList groupsAdded;

        ModelStubWithTime() {
            this.personsAdded = new ArrayList<>();
            this.groupsAdded = new GroupList();
        }

        public Group findGroup(String groupName) throws CommandException{
            for (Group group : groupsAdded) {
                if (group.nameEquals(groupName)) {
                    return group;
                }
            }
            throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
        }

        public void addGroup(Group group) {
            groupsAdded.add(group);
        }
        public void addPerson(Person person) {
            personsAdded.add(person);
        }


        public void groupPerson(Person person, Group group) throws CommandException {

            if (person == null) {
                throw new CommandException(Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND);
            }
            if (group == null) {
                throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
            }
            group.addPerson(person);
            person.addGroup(group);
        }

        public void addTime(Group group, TimeInterval time) throws CommandException {
            if (group == null) {
                throw new CommandException(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
            }

            group.addTime(time);
        }
        public void addTime(Person person, TimeInterval time) throws CommandException {
            if (person == null) {
                throw new CommandException(Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND);
            }

            person.addFreeTime(time);
        }

    }


}
