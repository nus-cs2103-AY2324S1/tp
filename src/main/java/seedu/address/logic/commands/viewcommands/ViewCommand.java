package seedu.address.logic.commands.viewcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Views the contact with the specified index from a contact list.
 */
public abstract class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a person or company. \n"
            + "Parameters: \n"
            + "c - View a company's contact\n"
            + "p - View a person's contact\n"
            + "index - Index number shown in the corresponding contact list\n"
            + "Example: " + COMMAND_WORD + " p 3";

    public static final String MESSAGE_SUCCESS = "Success message for the view command!";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
