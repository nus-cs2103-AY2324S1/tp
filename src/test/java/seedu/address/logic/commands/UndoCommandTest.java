package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_undoSuccessful() {
        // Execute a delete operation (which can be considered a 'deletion' for the undo operation)
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        try {
            deleteCommand.execute(model);
        } catch (CommandException e) {
            // Handle CommandException according to the application's error handling strategy
            // For simplicity of the example, failing the test might be acceptable
            assertEquals(0, 1, "DeleteCommand execution failed.");
        }

        // Create a copy of the model before executing the deletion
        Model modelUndo = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Booking sample = modelUndo.getBookingsBook().getPersonList().get(0);
        modelUndo.deleteBooking(sample);
        modelUndo.addBooking(sample);

        // Verify that the undo command reverts the last deletion successfully
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;

        assertUndoCommandSuccess(undoCommand, model, expectedMessage, modelUndo);
    }

    @Test
    public void execute_noDeletionToUndo_throwsCommandException() {
        // No deletions have been made, so undo command should throw an exception
        UndoCommand undoCommand = new UndoCommand();
        try {
            undoCommand.execute(model);
        } catch (CommandException e) {
            assertEquals(UndoCommand.MESSAGE_NO_UNDO, e.getMessage());
        }
    }

    @Test
    public void equals() {
        UndoCommand undoFirstCommand = new UndoCommand();

        // same object -> returns true
        assertTrue(undoFirstCommand.equals(undoFirstCommand));

        // different types -> returns false
        assertFalse(undoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(undoFirstCommand.equals(null));
    }
}
