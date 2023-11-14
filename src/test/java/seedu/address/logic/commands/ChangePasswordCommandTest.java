package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.PasswordManager;

public class ChangePasswordCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void different_passwordManager() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        String wrongPassword = password + "wrong";
        ChangePasswordCommand wrongChangePasswordCommand = new ChangePasswordCommand(wrongPassword, password);
        ChangePasswordCommand rightChangePasswordCommand = new ChangePasswordCommand(password, password);
        assert(!wrongChangePasswordCommand.equals(rightChangePasswordCommand));
    }

    @Test
    public void same_passwordManager() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        ChangePasswordCommand firstChangePasswordCommand = new ChangePasswordCommand(password, password);
        ChangePasswordCommand secondChangePasswordCommand = new ChangePasswordCommand(password, password);
        assert(firstChangePasswordCommand.equals(secondChangePasswordCommand));
    }


    @Test
    public void null_passwordManager() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        ChangePasswordCommand changePasswordCommand = new ChangePasswordCommand(password, password);
        assert(!changePasswordCommand.equals(null));
    }

    @Test
    public void same_passwordManager_toString() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        ChangePasswordCommand firstChangePasswordCommand = new ChangePasswordCommand(password, password);
        ChangePasswordCommand secondChangePasswordCommand = new ChangePasswordCommand(password, password);
        assert(firstChangePasswordCommand.toString().equals(secondChangePasswordCommand.toString()));
    }

    @Test
    public void passwordManager_execute_success() {
        PasswordManager passwordManager = new PasswordManager();
        String password = passwordManager.getPassword();
        ChangePasswordCommand changePasswordCommand = new ChangePasswordCommand(password, password);
        String expectedMessage = ChangePasswordCommand.CHANGE_PASSWORD_SUCCESS + password;
        assertCommandSuccess(changePasswordCommand, model, expectedMessage, model);
    }

}
