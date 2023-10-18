package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIcs.FIRST_NRIC;
import static seedu.address.testutil.TypicalIcs.SECOND_NRIC;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPatient.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNricUnfilteredList_success() {
        Person personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Ic personToDeleteIc = personToDelete.getIc();
        DeleteCommand deleteCommand = new DeleteCommand(personToDeleteIc);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNricUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(FIRST_NRIC);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_validNricFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Ic personToDeleteIc = personToDelete.getIc();
        DeleteCommand deleteCommand = new DeleteCommand(personToDeleteIc);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNricFilteredList_throwsCommandException() {
        Person personToDelete = model.getFilteredPatientList().get(INDEX_SECOND_PERSON.getZeroBased());
        Ic personToDeleteIc = personToDelete.getIc();

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // delete person at index 2 of whole address book but filtered list is only up to index 1
        DeleteCommand deleteCommand = new DeleteCommand(personToDeleteIc);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(FIRST_NRIC);
        DeleteCommand deleteSecondCommand = new DeleteCommand(SECOND_NRIC);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(FIRST_NRIC);
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
        DeleteCommand deleteCommand = new DeleteCommand(FIRST_NRIC);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIc=" + FIRST_NRIC + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
