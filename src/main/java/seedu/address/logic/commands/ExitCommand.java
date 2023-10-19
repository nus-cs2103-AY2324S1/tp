package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.model.Model;



/**
 * Exits the app.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(Messages.COMMAND_EXIT_SUCCESS, false, true);
    }
}
