package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions "
            + "and available keywords.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window. Available keywords:\n"
            + AddCommand.COMMAND_WORD + ", "
            + ClearCommand.COMMAND_WORD + ", "
            + DeleteCommand.COMMAND_WORD + ", "
            + EditCommand.COMMAND_WORD + ", "
            + ExitCommand.COMMAND_WORD + ", "
            + FindCommand.COMMAND_WORD + ", "
            + GatherCommand.COMMAND_WORD + ", "
            + COMMAND_WORD + ", "
            + ListCommand.COMMAND_WORD + ", "
            + ScheduleCommand.COMMAND_WORD + ", "
            + SortCommand.COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
