/**
 * Command to change the user's password.
 */
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.Password;

/**
 * Changes the user's password to a new one.
 */
public class ChangePasswordCommand extends Command {
    public static final String COMMAND_WORD = "change-password";

    // Usage message for the command
    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Please change the password to the correct format\n"
            + Password.MESSAGE_CONSTRAINTS + "\n"
            + "Example: " + COMMAND_WORD + " pw/Password123! npw/NewPass987!";

    private String currentPw;
    private String newPw;

    /**
     * Constructs a {@code ChangePasswordCommand} with the current password and the new password.
     *
     * @param currentPw The current password.
     * @param newPw The new password to replace the current password.
     */
    public ChangePasswordCommand(String currentPw, String newPw) {
        this.currentPw = currentPw;
        this.newPw = newPw;
    }

    @Override
    public CommandResult execute(Model model) {
        assert model != null : "Model cannot be null";
        requireNonNull(model);
        String result = Password.changePassword(currentPw, newPw);
        return new CommandResult(result, TabIndex.Developer);
    }
}
