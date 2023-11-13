package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the patient record system.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting A&E as requested ...";

    @Override
    public CommandResult execute(Model model, String command) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
