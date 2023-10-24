package swe.context.logic.commands;

import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.testutil.TestData.Valid.Contact.getTypicalContacts;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;

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
