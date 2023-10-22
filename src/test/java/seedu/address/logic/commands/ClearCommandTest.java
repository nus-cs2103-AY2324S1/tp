package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalManageHr;

import org.junit.jupiter.api.Test;

import seedu.address.model.ManageHr;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyManageHr_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyManageHr_success() {
        Model model = new ModelManager(getTypicalManageHr(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalManageHr(), new UserPrefs());
        expectedModel.setManageHr(new ManageHr());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
