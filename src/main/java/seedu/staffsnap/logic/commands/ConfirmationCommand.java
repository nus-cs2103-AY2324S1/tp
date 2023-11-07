package seedu.staffsnap.logic.commands;
import seedu.staffsnap.model.Model;

/**
 * Clears the applicant book.
 */
public class ConfirmationCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String CONFIRM = "Enter yes to confirm clear. Otherwise, enter any other key to cancel.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(CONFIRM);
    }
}
