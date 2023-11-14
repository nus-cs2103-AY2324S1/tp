package transact.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static transact.logic.commands.CommandTestUtil.assertCommandFailure;
import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.testutil.TypicalIndexes.ID_FIRST_PERSON;
import static transact.testutil.TypicalIndexes.ID_SECOND_PERSON;
import static transact.testutil.TypicalPersons.getTypicalAddressBook;
import static transact.testutil.TypicalTransactions.getTypicalTransactionBook;

import org.junit.jupiter.api.Test;

import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.ModelManager;
import transact.model.UserPrefs;
import transact.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStaffCommand}.
 */
public class DeleteStaffCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTransactionBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(ID_FIRST_PERSON);
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(ID_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), getTypicalTransactionBook(),
                new UserPrefs());
        expectedModel.deletePerson(personToDelete.getPersonId());

        assertCommandSuccess(deleteStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() throws CommandException {
        Integer outOfBoundId = model.getFilteredPersonList().size() + 1;
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(outOfBoundId);

        assertCommandFailure(deleteStaffCommand, model,
                String.format(Messages.MESSAGE_INVALID_PERSON_ID, outOfBoundId));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        /*
         * Not needed for now, since we do not identify a person based on their position
         * in the list
         * showPersonAtId(model, ID_FIRST_PERSON);
         *
         * Person personToDelete = model.getFilteredPersonList().get(ID_FIRST_PERSON);
         * DeleteStaffCommand deleteStaffCommand = new
         * DeleteStaffCommand(ID_FIRST_PERSON);
         *
         * String expectedMessage =
         * String.format(DeleteStaffCommand.MESSAGE_DELETE_PERSON_SUCCESS,
         * Messages.format(personToDelete));
         *
         * Model expectedModel = new ModelManager(model.getAddressBook(),
         * model.getTransactionBook(), new UserPrefs());
         * expectedModel.deletePerson(personToDelete.getPersonId());
         * showNoPerson(expectedModel);
         *
         * assertCommandSuccess(deleteStaffCommand, model, expectedMessage,
         * expectedModel);
         */
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        /*
         * Not needed for now, since we do not identify a person based on their position
         * in the list
         * showPersonAtId(model, ID_FIRST_PERSON);
         *
         * Integer outOfBoundId = ID_SECOND_PERSON;
         * // ensures that outOfBoundIndex is still in bounds of address book list
         * assertTrue(outOfBoundId < model.getAddressBook().getPersonList().size());
         *
         * DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(outOfBoundId);
         *
         * assertCommandFailure(deleteStaffCommand, model,
         * Messages.getInvalidPersonIndexMessageForId(outOfBoundId));
         */
    }

    @Test
    public void equals() {
        DeleteStaffCommand deleteFirstCommand = new DeleteStaffCommand(ID_FIRST_PERSON);
        DeleteStaffCommand deleteSecondCommand = new DeleteStaffCommand(ID_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStaffCommand deleteFirstCommandCopy = new DeleteStaffCommand(ID_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Integer targetId = 1;
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(targetId);
        String expected = DeleteStaffCommand.class.getCanonicalName() + "{personId=" + targetId + "}";
        assertEquals(expected, deleteStaffCommand.toString());
    }
}
