package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model2;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command2 {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model2 model) throws CommandException {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }

}
