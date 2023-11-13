package profplan.logic.commands;

import profplan.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = "Exits the application";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Task List as requested ...";
    public static final String MESSAGE_FULL_HELP = MESSAGE_USAGE;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
