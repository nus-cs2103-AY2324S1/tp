package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class UndoCommandTest {

    @Test
    public void execute_singleUndoableCommand_success() throws CommandException {
        Model model = new ModelManager();
        UndoableCommandStub stubUndoableCommand = new UndoableCommandStub();

        model.addToHistory(stubUndoableCommand);

        UndoCommand undoCommand = new UndoCommand(1);
        CommandResult result = undoCommand.execute(model);

        assertEquals("Undoing 1 command(s):\n"
                + "1. Stub Undoable Command undone", result.getFeedbackToUser());
    }

    @Test
    public void execute_multipleUndoableCommand_success() throws CommandException {
        Model model = new ModelManager();
        UndoableCommandStub stubUndoableCommand1 = new UndoableCommandStub();
        UndoableCommandStub stubUndoableCommand2 = new UndoableCommandStub();
        UndoableCommandStub stubUndoableCommand3 = new UndoableCommandStub();

        model.addToHistory(stubUndoableCommand1);
        model.addToHistory(stubUndoableCommand2);
        model.addToHistory(stubUndoableCommand3);

        UndoCommand undoCommand = new UndoCommand(3);
        CommandResult result = undoCommand.execute(model);

        assertEquals("Undoing 3 command(s):\n"
                + "1. Stub Undoable Command undone\n"
                + "2. Stub Undoable Command undone\n"
                + "3. Stub Undoable Command undone", result.getFeedbackToUser());
    }
    @Test
    public void execute_undoMoreCommandsThanHistory_failure() {
        Model model = new ModelManager();
        UndoableCommandStub stubUndoableCommand = new UndoableCommandStub();

        model.addToHistory(stubUndoableCommand);

        // Attempt to undo 2 commands when only 1 is in history
        UndoCommand undoCommand = new UndoCommand(2);
        assertThrows(CommandException.class, () -> undoCommand.execute(model));
    }

    // Define a stub UndoableCommand class for testing purposes
    static class UndoableCommandStub extends UndoableCommand {
        @Override
        public CommandResult execute(Model model) {
            return new CommandResult("Stub Undoable Command executed");
        }

        @Override
        public CommandResult undo(Model model) {
            return new CommandResult("Stub Undoable Command undone");
        }
    }
}
