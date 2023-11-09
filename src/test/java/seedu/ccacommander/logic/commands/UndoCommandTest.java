package seedu.ccacommander.logic.commands;

import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ccacommander.logic.commands.CommandTestUtil.deleteFirstEvent;
import static seedu.ccacommander.logic.commands.CommandTestUtil.deleteLastEvent;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;

public class UndoCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalCcaCommander(), new UserPrefs());

        deleteFirstEvent(model);
        deleteLastEvent(model);
        deleteFirstEvent(model);
        deleteFirstEvent(expectedModel);
        deleteLastEvent(expectedModel);
        deleteFirstEvent(expectedModel);
    }

    @Test
    public void execute_multiple_undos() {
        // multiple undoable commands in model
        String undoMessageFirst = expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model,
                String.format(UndoCommand.MESSAGE_SUCCESS_UNDO, undoMessageFirst), expectedModel);

        // two undoable commands in model
        String undoMessageSecond = expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model,
                String.format(UndoCommand.MESSAGE_SUCCESS_UNDO, undoMessageSecond), expectedModel);

        // one undoable command in model
        String undoMessageThird = expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model,
                String.format(UndoCommand.MESSAGE_SUCCESS_UNDO, undoMessageThird), expectedModel);

        // no more undoable command in model
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_NO_AVAILABLE_COMMAND);
    }
}
