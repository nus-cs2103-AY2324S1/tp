package seedu.address.logic.newcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.NewCommand;
import seedu.address.model.Model;
import seedu.address.model.NewModel;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends NewCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(NewModel model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
