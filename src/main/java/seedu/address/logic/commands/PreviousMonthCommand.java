package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class PreviousMonthCommand extends Command {

    public static final String COMMAND_WORD = "pm";

    public static final String MESSAGE_PREVIOUS_MONTH_ACKNOWLEDGEMENT = "Displaying previous month calendar!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_PREVIOUS_MONTH_ACKNOWLEDGEMENT, false, false, false, false, true, false);
    }

}
