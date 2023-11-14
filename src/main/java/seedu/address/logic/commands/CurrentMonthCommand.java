package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class CurrentMonthCommand extends Command {

    public static final String COMMAND_WORD = "cm";

    public static final String MESSAGE_CURRENT_MONTH_ACKNOWLEDGEMENT = "Displaying current month calendar!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_CURRENT_MONTH_ACKNOWLEDGEMENT, false, false, false, false, false, true);
    }

}
