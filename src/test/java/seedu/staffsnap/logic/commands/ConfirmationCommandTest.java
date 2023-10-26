package seedu.staffsnap.logic.commands;

import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;


public class ConfirmationCommandTest {
    @Test
    public void execute_emptyApplicantBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ConfirmationCommand(), model, ConfirmationCommand.CONFIRM, expectedModel);
    }

}
