package seedu.address.logic.commands.listcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons or companies in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Success message for the list command!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all persons or companies. \n"
            + "Parameters: \n"
            + "c - Lists all companies\n"
            + "p - Lists all persons \n"
            + "Example: " + COMMAND_WORD + " p";

    @Override
    public abstract CommandResult execute(Model model);
}
