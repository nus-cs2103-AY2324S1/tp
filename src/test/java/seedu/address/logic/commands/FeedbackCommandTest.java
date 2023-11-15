package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FeedbackCommand.SHOWING_FEEDBACK_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class FeedbackCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_feedback_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_FEEDBACK_MESSAGE,
                false, false, true, false, false);
        assertCommandSuccess(new FeedbackCommand(), model, expectedCommandResult, expectedModel);
    }
}
