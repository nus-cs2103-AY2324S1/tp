package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalPersons;

public class UndoCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_undoCommand_success() {
        UndoCommand undoCommand = new UndoCommand();

        CommandResult commandResult = undoCommand.execute(model);

        assertEquals(MESSAGE_SUCCESS, commandResult.getFeedbackToUser());

        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_undoCommandNullModel_success() {
        UndoCommand undoCommand = new UndoCommand();

        // Set the model to null
        model = null;

        CommandResult commandResult = undoCommand.execute(model);

        assertEquals(UndoCommand.MESSAGE_UNDO_DONE, commandResult.getFeedbackToUser());
    }

}
