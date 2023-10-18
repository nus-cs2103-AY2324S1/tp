package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestData.Valid.Contact.getTypicalContactsManager;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.ContactsManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyContactsManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, Messages.MESSAGE_CLEAR_COMMAND_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyContactsManager_success() {
        Model model = new ModelManager(getTypicalContactsManager(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalContactsManager(), new UserPrefs());
        expectedModel.setContactsManager(new ContactsManager());

        assertCommandSuccess(new ClearCommand(), model, Messages.MESSAGE_CLEAR_COMMAND_SUCCESS, expectedModel);
    }

}
