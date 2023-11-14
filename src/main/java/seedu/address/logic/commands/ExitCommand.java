package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Keyword to trigger the exit command.
     */
    public static final String COMMAND_WORD = "exit";

    /**
     * Message displayed upon exiting the NUSearch application.
     */
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting NUSearch!";

    /**
     * Executes the exit command which terminated the NUSearch application.
     *
     * @param model The current model, unused in this command.
     * @return The CommandResult indicating the acknowledgement message and the termination of the application.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
