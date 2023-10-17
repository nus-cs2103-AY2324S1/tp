package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Name name = new Name(PersonBuilder.DEFAULT_NAME);

    private Nric nric = new Nric(PersonBuilder.DEFAULT_NRIC);

    private DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

    @Test
    public void execute_ValidNameUnfilteredList_success() {
        Person personToDelete = new PersonBuilder().withName("Amy Bee").build();
        model.addPerson(personToDelete);
        Name name = personToDelete.getName();
        DeleteCommand deleteCommand = new DeleteCommand(null, name, descriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_ValidNricUnfilteredList_success() {
        Person personToDelete = new PersonBuilder().withNric("S1234567E").build();
        model.addPerson(personToDelete);
        Nric nric = personToDelete.getNric();
        DeleteCommand deleteCommand = new DeleteCommand(nric, null, descriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    /*
     * @Test
     * public void execute_deletePartial_success() {
     * Person personToDelete = new
     * PersonBuilder().withAddress("Kent Ridge").withNric("S1234567F").build();
     * model.addPerson(personToDelete);
     * DeletePersonDescriptor discriptorAddress = new DeletePersonDescriptor();
     * discriptorAddress.setAddress();
     * 
     * 
     * CommandResult result = new DeleteCommand(nric, null, descriptor)
     * .execute(model);
     * 
     * Person expectedPerson = new
     * PersonBuilder().withAddress("").withNric("S1234567F").build();
     * 
     * assertEquals(expectedPerson, model.getFilteredPersonList().get(0));
     * }
     */

    /*
     * @Test
     * public void execute_validIndexUnfilteredList_success() {
     * Person personToDelete =
     * model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
     * DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
     * 
     * String expectedMessage =
     * String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
     * Messages.format(personToDelete));
     * 
     * ModelManager expectedModel = new ModelManager(model.getAddressBook(), new
     * UserPrefs());
     * expectedModel.deletePerson(personToDelete);
     * 
     * assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
     * }
     * 
     * @Test
     * public void execute_invalidIndexUnfilteredList_throwsCommandException() {
     * Index outOfBoundIndex =
     * Index.fromOneBased(model.getFilteredPersonList().size() + 1);
     * DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
     * 
     * assertCommandFailure(deleteCommand, model,
     * Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
     * }
     * 
     * @Test
     * public void execute_validIndexFilteredList_success() {
     * showPersonAtIndex(model, INDEX_FIRST_PERSON);
     * 
     * Person personToDelete =
     * model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
     * DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
     * 
     * String expectedMessage =
     * String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
     * Messages.format(personToDelete));
     * 
     * Model expectedModel = new ModelManager(model.getAddressBook(), new
     * UserPrefs());
     * expectedModel.deletePerson(personToDelete);
     * showNoPerson(expectedModel);
     * 
     * assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
     * }
     * 
     * @Test
     * public void execute_invalidIndexFilteredList_throwsCommandException() {
     * showPersonAtIndex(model, INDEX_FIRST_PERSON);
     * 
     * Index outOfBoundIndex = INDEX_SECOND_PERSON;
     * // ensures that outOfBoundIndex is still in bounds of address book list
     * assertTrue(outOfBoundIndex.getZeroBased() <
     * model.getAddressBook().getPersonList().size());
     * 
     * DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
     * 
     * assertCommandFailure(deleteCommand, model,
     * Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
     * }
     */

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(nric, null, descriptor);
        DeleteCommand deleteSecondCommand = new DeleteCommand(null, name, descriptor);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(nric, null, descriptor);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));


        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        model.addPerson(new PersonBuilder().build());
        DeleteCommand deleteCommand = new DeleteCommand(nric, name, descriptor);
        System.out.println(deleteCommand.toString());
        String expected = new PersonBuilder().build().toString();
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
