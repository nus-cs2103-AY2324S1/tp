package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    /**
     * Executes the help command to display program usage instructions.
     *
     * @param model The current model.
     * @return A CommandResult indicating the result of the help operation and whether to open the help window.
     */
    @Override
    public CommandResult execute(Model model) {
        assert model != null;
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, true);
    }
}
