package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestData.getTypicalContacts;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Settings;



public class ClearCommandTest {
    @Test
    public void execute_emptyContacts_success() {
        Model actual = new ModelManager();
        Model expected = new ModelManager();
        assertCommandSuccess(new ClearCommand(), actual, Messages.COMMAND_CLEAR_SUCCESS, expected);
    }

    @Test
    public void execute_nonEmptyContacts_success() {
        Model actual = new ModelManager(getTypicalContacts(), new Settings());
        Model expected = new ModelManager();
        assertCommandSuccess(new ClearCommand(), actual, Messages.COMMAND_CLEAR_SUCCESS, expected);
    }
}
