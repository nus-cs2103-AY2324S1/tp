package swe.context.logic.commands;

import swe.context.logic.Messages;
import swe.context.model.Model;



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
