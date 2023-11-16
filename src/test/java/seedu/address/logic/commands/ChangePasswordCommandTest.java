package seedu.address.logic.commands;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Password.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
public class ChangePasswordCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        expectedModel = new ModelManager();
    }

    @Test
    public void execute_changePassword_success() {
        //Works only for default password
        assertCommandSuccess(new ChangePasswordCommand("Password123!", "Password1243!"), model,
                "Password changed successfully.", expectedModel);
        //Change back to default password
        assertCommandSuccess(new ChangePasswordCommand("Password1243!", "Password123!"), model,
                "Password changed successfully.", expectedModel);
    }

    @Test
    public void execute_changePassword_same() {
        assertCommandSuccess(new ChangePasswordCommand("Password123!", "Password123!"), model,
                "New password cannot be the same as the current password.",
                expectedModel);
    }
    @Test
    public void execute_changePassword_wrong() {
        assertCommandSuccess(new ChangePasswordCommand("Password13!", "Password123!"), model,
                "Current password is incorrect.\n" + MESSAGE_CONSTRAINTS,
                expectedModel);
    }
    @Test
    public void execute_changePassword_invalid() {
        assertCommandSuccess(new ChangePasswordCommand("Password123!", "Password123"), model,
                "New password doesn't meet the constraints.\n" + MESSAGE_CONSTRAINTS,
                expectedModel);
    }
}
