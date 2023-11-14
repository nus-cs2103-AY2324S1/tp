package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validEmailUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(0); // Assuming the first person in the list is used
        Email emailToDelete = personToDelete.getEmail();
        DeleteCommand deleteCommand = new DeleteCommand(emailToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, false);
    }

    @Test
    public void execute_invalidEmailUnfilteredList_throwsCommandException() {
        Email invalidEmail = new Email("invalid@u.nus.edu"); // Use an email that doesn't exist in the test data
        DeleteCommand deleteCommand = new DeleteCommand(invalidEmail);

        assertCommandFailure(deleteCommand, model, "Student with the provided email not found.");
    }

    @Test
    public void equals() {
        Person person = model.getFilteredPersonList().get(0); // Assuming the first person in the list is used
        Email email = person.getEmail();
        DeleteCommand deleteFirstCommand = new DeleteCommand(email);

        Email email2 = model.getFilteredPersonList().get(1)
                .getEmail(); // Assuming the second person in the list is used
        DeleteCommand deleteSecondCommand = new DeleteCommand(email2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(email);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different email -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Person person = model.getFilteredPersonList().get(0); // Assuming the first person in the list is used
        Email email = person.getEmail();
        DeleteCommand deleteCommand = new DeleteCommand(email);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetEmail=" + email + "}";
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
