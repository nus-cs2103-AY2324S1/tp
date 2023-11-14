package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.PasswordManager;

/**
 * Change the password used to enter InsuraHub
 */
public class ChangePasswordCommand extends Command {

    public static final String COMMAND_WORD = "changePassword";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " changes the current password used to enter InsuraHub to a new password \n"
            + "Parameters: "
            + PREFIX_OLD_PASSWORD + "OLD PASSWORD "
            + PREFIX_NEW_PASSWORD + "NEW PASSWORD \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OLD_PASSWORD + "Your OLD PASSWORD "
            + PREFIX_NEW_PASSWORD + "NEW PASSWORD TO SET TO";

    public static final String CHANGE_PASSWORD_SUCCESS = "Password has been change to: ";

    public static final String CHANGE_PASSWORD_FAILURE = "The old password entered is wrong: ";
    private static final PasswordManager passwordManager = new PasswordManager();
    private final String oldPassword;
    private final String newPassword;

    /**
     * Change the password used to enter InsuraHub
     *
     * @param oldPassword used to enter InsuraHub
     * @param newPassword used to enter InsuraHub
     */
    public ChangePasswordCommand(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (passwordManager.check(oldPassword)) {
            passwordManager.setPassword((newPassword));
            return new CommandResult(CHANGE_PASSWORD_SUCCESS + newPassword);
        } else {
            return new CommandResult(CHANGE_PASSWORD_FAILURE + oldPassword);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChangePasswordCommand)) {
            return false;
        }

        ChangePasswordCommand otherChangePasswordCommand = (ChangePasswordCommand) other;
        return oldPassword.equals(otherChangePasswordCommand.oldPassword)
                && newPassword.equals(otherChangePasswordCommand.newPassword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("oldPassword", oldPassword)
                .add("newPassword", newPassword)
                .toString();
    }
}
