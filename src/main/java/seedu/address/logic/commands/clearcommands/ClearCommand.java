package seedu.address.logic.commands.clearcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
/**
 * Clear the whole address book or clear internship list of a specific company.
 */
public abstract class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Success message for the clear command";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the whole address book or "
            + "internship list of a specific company"
            + "\n"
            + "Parameters: \n"
            + "i INDEX - Clears all internships of the company with index [INDEX]\n"
            + "Example: " + COMMAND_WORD + " i 1";
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
