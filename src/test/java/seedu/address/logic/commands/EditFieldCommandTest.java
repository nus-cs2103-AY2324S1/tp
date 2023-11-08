package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditFieldCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class EditFieldCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    @Test
    public void execute_editFieldCommand_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, CommandType.EDIT_FIELD);
        assertCommandSuccess(new EditFieldCommand(), model, expectedCommandResult, expectedModel);

    }
}
