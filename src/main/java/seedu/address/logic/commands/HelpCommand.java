package seedu.address.logic.commands;

import seedu.address.model.SeplendidModel;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    @Override
    public CommandResult execute(SeplendidModel model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
