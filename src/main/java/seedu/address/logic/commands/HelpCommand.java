package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD + "\n"
            + "Shows specific help for each command.\n"
            + "Example: " + COMMAND_WORD + " add\n"
            + "Displays help for the add command";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public final String commandUsage;
    public HelpCommand(String cmd) {
        this.commandUsage = cmd;
    }
    public HelpCommand() {
        this("");
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true,
                false, commandUsage);
    }
}
