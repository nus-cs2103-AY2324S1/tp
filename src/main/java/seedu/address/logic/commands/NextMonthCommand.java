package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class NextMonthCommand extends Command {

    public static final String COMMAND_WORD = "nm";

    public static final String MESSAGE_NEXT_MONTH_ACKNOWLEDGEMENT = "Displaying next month calendar!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_NEXT_MONTH_ACKNOWLEDGEMENT, false, false, false, true, false, false);
    }

}
