package seedu.classmanager.logic.commands;

import seedu.classmanager.logic.CommandHistory;
import seedu.classmanager.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Class Manager as requested ...";

    /**
     * Executes the command which exits the program.
     * @param model {@code Model} which the command should operate on.
     * @param commandHistory The command history to record this command.
     * @return A {@code CommandResult} with the feedback message of the operation result.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false);
    }

}
