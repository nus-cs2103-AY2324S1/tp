package seedu.staffsnap.logic.commands;
import seedu.staffsnap.model.Model;

/**
 * Clears the applicant book.
 */
public class ConfirmationCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String CONFIRM = "Are you sure you want to clear?";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(CONFIRM);
    }
}
