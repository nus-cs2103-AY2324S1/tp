package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_EXAMPLE = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "\n"
            + "Example: \n" + COMMAND_WORD + "\n"
            + "Shows specific help for each command.\n"
            + "\n"
            + "Example: \n" + COMMAND_WORD + " add\n"
            + "Displays help for the add command";;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public final String commandUsage;
    public final String commandExample;

    /**
     * Create a new HelpCommand, to display in the help window.
     * @param cmdUsage Usage of command, to show in textbox.
     * @param cmdExample Example of use of command, to copy-paste into clipboard.
     */
    public HelpCommand(String cmdUsage, String cmdExample) {
        this.commandUsage = cmdUsage;
        this.commandExample = cmdExample;
    }
    public HelpCommand() {
        this("", "");
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true,
                false, commandUsage, commandExample);
    }
}
