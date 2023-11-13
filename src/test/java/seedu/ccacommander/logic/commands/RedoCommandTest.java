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

public class RedoCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalCcaCommander(), new UserPrefs());

        deleteFirstEvent(model);
        deleteLastEvent(model);
        deleteFirstEvent(model);
        for (int i = 0; i < 3; i++) {
            model.undo();
        }

        deleteFirstEvent(expectedModel);
        deleteLastEvent(expectedModel);
        deleteFirstEvent(expectedModel);
        for (int i = 0; i < 3; i++) {
            expectedModel.undo();
        }
    }

    @Test
    public void execute_multiple_redos() {
        // multiple redoable commands in model
        String redoMessageFirst = expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model,
                String.format(RedoCommand.MESSAGE_SUCCESS_REDO, redoMessageFirst), expectedModel);

        // two redoable commands in model
        String redoMessageSecond = expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model,
                String.format(RedoCommand.MESSAGE_SUCCESS_REDO, redoMessageSecond), expectedModel);

        // one redoable command in model
        String redoMessageThird = expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model,
                String.format(RedoCommand.MESSAGE_SUCCESS_REDO, redoMessageThird), expectedModel);

        // no more redoable command in model
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_NO_AVAILABLE_COMMAND);
    }
}
