package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.util.Pair;
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
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePersonCommandTest {

    @Test
    public void execute_existingPersonWithNoGroup_deleteSuccessful() throws Exception {
        Person validPerson = new PersonBuilder().withName(PersonBuilder.DEFAULT_NAME).build();
        DeletePersonCommandTest.ModelStubWithPersonToDelete modelStub =
                new DeletePersonCommandTest.ModelStubWithPersonToDelete(validPerson);

        CommandResult commandResult =
                new DeletePersonCommand(validPerson.getName().toString()).execute(modelStub);

        assertEquals(String.format(MESSAGE_DELETE_PERSON_SUCCESS, PersonBuilder.DEFAULT_NAME),
                commandResult.getFeedbackToUser());
        ArrayList<Person> emptyPersonList = new ArrayList<>();
        assertEquals(emptyPersonList, modelStub.persons); // checks person removed from contact list

    }

    @Test
    public void execute_existingPersonWithGroup_deleteSuccessful() throws Exception {
        String grpName = "CS2103";
        Person validPerson =
                new PersonBuilder().withName(PersonBuilder.DEFAULT_NAME).withGroupList(grpName).build();
        DeletePersonCommandTest.ModelStubWithPersonToDelete modelStub =
                new DeletePersonCommandTest.ModelStubWithPersonToDelete(validPerson);
        CommandResult commandResult =
                new DeletePersonCommand(validPerson.getName().toString()).execute(modelStub);

        assertEquals(String.format(String.format(MESSAGE_DELETE_PERSON_SUCCESS, PersonBuilder.DEFAULT_NAME)),
                commandResult.getFeedbackToUser());

        ArrayList<Person> emptyPersonList = new ArrayList<>();
        assertEquals(emptyPersonList, modelStub.persons); // checks person removed from contact list
        assertFalse(modelStub.groups.get(0).contains(validPerson)); // checks if person is deleted from grp
    }

    @Test
    public void execute_nonexistentPerson_throwsCommandException() {
        // person does not exist in address book
        Person nonexistentPerson = new PersonBuilder().build();
        DeletePersonCommand deletePersonCommand =
                new DeletePersonCommand(nonexistentPerson.getName().toString());
        DeletePersonCommandTest.ModelStub modelStub =
                new DeletePersonCommandTest.ModelStubDeletingPerson();

        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND, () -> deletePersonCommand.execute(modelStub));

        // empty input
        String emptyInput = "";
        assertThrows(CommandException.class, Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND, () ->
                new DeletePersonCommand(emptyInput).execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        DeletePersonCommand deleteAliceCommand = new DeletePersonCommand(alice.getName().fullName);
        DeletePersonCommand deleteBobCommand = new DeletePersonCommand(bob.getName().fullName);

        // same object -> returns true
        assertTrue(deleteAliceCommand.equals(deleteAliceCommand));

        // same values -> returns true
        DeletePersonCommand deleteAliceCommandCopy = new DeletePersonCommand(alice.getName().fullName);
        assertTrue(deleteAliceCommand.equals(deleteAliceCommandCopy));

        // different types -> returns false
        assertFalse(deleteAliceCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteAliceCommand.equals(deleteBobCommand));
    }

    @Test
    public void toStringMethod() {
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(ALICE.getName().fullName);
        String expected =
                DeletePersonCommand.class.getCanonicalName() + "{name=" + ALICE.getName().fullName + "}";
        assertEquals(expected, deletePersonCommand.toString());
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
     * A Model stub that deletes the person.
     */
    private class ModelStubDeletingPerson extends DeletePersonCommandTest.ModelStub {
        final ArrayList<Person> persons = new ArrayList<>();

        final ArrayList<Group> groups = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return persons.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public Person deletePerson(String personName) throws CommandException {
            requireNonNull(personName);
            for (Person person : persons) {
                if (person.getName().fullName.equals(personName)) {
                    persons.remove(person);
                    return person;
                }
            }
            throw new CommandException(Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND);
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
     * A Model stub that contains a single person to be deleted.
     */
    private class ModelStubWithPersonToDelete extends DeletePersonCommandTest.ModelStubDeletingPerson {

        ModelStubWithPersonToDelete(Person person) {
            requireNonNull(person);
            super.persons.add(person);
            person.getGroups().toStream().forEach(
                    g -> super.groups.add(g)
            );
        }
    }

}
