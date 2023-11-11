package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_TUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_WED;
import static seedu.address.logic.commands.ListTimePersonCommand.MESSAGE_LISTTIME_PERSON_SUCCESS;
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
import seedu.address.testutil.PersonBuilder;

public class ListTimePersonCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListTimePersonCommand(null));
    }

    @Test
    public void execute_personWithNoTimeIntervalListSuccess() throws Exception {
        Person validPerson = new PersonBuilder().build();
        ModelStubWithPerson modelStub = new ModelStubWithPerson(validPerson);

        // Person has time interval to be listed
        CommandResult commandResult =
                new ListTimePersonCommand(validPerson.getName()).execute(modelStub);
        CommandResult expectedResult =
                new CommandResult(String.format(MESSAGE_LISTTIME_PERSON_SUCCESS,
                        validPerson.getName()));

        // Time interval has been deleted
        assertEquals(expectedResult, commandResult);
    }

    @Test
    public void execute_personWithSingleTimeIntervalListSuccess() throws Exception {
        Person validPerson = new PersonBuilder().build();
        ModelStubPersonWithSingleTiming modelStub = new ModelStubPersonWithSingleTiming(validPerson);

        // Person has time interval to be listed
        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));
        CommandResult commandResult =
                new ListTimePersonCommand(validPerson.getName()).execute(modelStub);
        CommandResult expectedResult =
                new CommandResult(String.format(MESSAGE_LISTTIME_PERSON_SUCCESS,
                        validPerson.getName()) + "\nMON 1300 - MON 1400 ");

        // Time interval has been deleted
        assertEquals(expectedResult, commandResult);
    }

    @Test
    public void execute_personWithMultipleTimeIntervalsListSuccess() throws Exception {
        Person validPerson = new PersonBuilder().build();
        ModelStubPersonWithMultipleTimings modelStub = new ModelStubPersonWithMultipleTimings(validPerson);

        // Person has time interval to be listed
        ArrayList<TimeInterval> validTimeInterval = new ArrayList<>();
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_MON));
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_TUE));
        validTimeInterval.add(ParserUtil.parseEachInterval(VALID_TIME_WED));

        CommandResult commandResult =
                new ListTimePersonCommand(validPerson.getName()).execute(modelStub);
        CommandResult expectedResult =
                new CommandResult(String.format(MESSAGE_LISTTIME_PERSON_SUCCESS,
                        validPerson.getName()) + "\nMON 1300 - MON 1400 \nTUE 1300 - TUE 1400 \nWED 1300 - WED 1400 ");

        assertEquals(expectedResult, commandResult);
    }

    @Test
    public void execute_nonExistentPersonListFail() throws Exception {
        Person validPerson = new PersonBuilder().build();
        ModelStubPersonWithMultipleTimings modelStub = new ModelStubPersonWithMultipleTimings(validPerson);
        Name invalidName = new Name("John");
        assertThrows(CommandException.class, () -> new ListTimePersonCommand(invalidName).execute(modelStub));
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
     * A Model stub that contains a single person with no time interval.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) throws ParseException {
            requireNonNull(person);
            this.person = person;
        }

        public boolean hasPerson(Name person) {
            requireNonNull(person);
            return this.person.getName().equals(person);
        }

        public boolean hasTime(TimeInterval timeInterval) {
            requireNonNull(timeInterval);
            return this.person.hasFreeTime(timeInterval);
        }

        @Override
        public TimeIntervalList getTimeFromPerson(Name personName) throws CommandException {
            requireNonNull(personName);
            Person person = this.person;
            return person.getTime();
        }
    }

    /**
     * A Model stub that contains a single person and a time interval.
     */
    private class ModelStubPersonWithSingleTiming extends ModelStub {
        private final Person person;

        ModelStubPersonWithSingleTiming(Person person) throws ParseException {
            requireNonNull(person);
            this.person = person;
            this.person.addFreeTime(ParserUtil.parseEachInterval(VALID_TIME_MON));
        }

        public boolean hasPerson(Name person) {
            requireNonNull(person);
            return this.person.getName().equals(person);
        }

        public boolean hasTime(TimeInterval timeInterval) {
            requireNonNull(timeInterval);
            return this.person.hasFreeTime(timeInterval);
        }

        @Override
        public TimeIntervalList getTimeFromPerson(Name personName) throws CommandException {
            requireNonNull(personName);
            Person person = this.person;
            return person.getTime();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubPersonWithMultipleTimings extends ModelStub {
        private final Person person;

        ModelStubPersonWithMultipleTimings(Person person) throws ParseException {
            requireNonNull(person);
            this.person = person;
            this.person.addFreeTime(ParserUtil.parseEachInterval(VALID_TIME_MON));
            this.person.addFreeTime(ParserUtil.parseEachInterval(VALID_TIME_TUE));
            this.person.addFreeTime(ParserUtil.parseEachInterval(VALID_TIME_WED));
        }

        public boolean hasPerson(Name person) {
            requireNonNull(person);
            return this.person.getName().equals(person);
        }

        public boolean hasTime(TimeInterval timeInterval) {
            requireNonNull(timeInterval);
            return this.person.hasFreeTime(timeInterval);
        }

        @Override
        public TimeIntervalList getTimeFromPerson(Name personName) throws CommandException {
            requireNonNull(personName);
            Person person = this.person;
            return person.getTime();
        }
    }

}
