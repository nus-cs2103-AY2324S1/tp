package swe.context.logic.commands;

import swe.context.logic.Messages;
import swe.context.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(Messages.HELP_COMMAND_SHOW_HELP, true, false);
    }
}
