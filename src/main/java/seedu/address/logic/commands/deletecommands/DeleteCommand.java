package seedu.address.logic.commands.deletecommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a person or a company identified using its displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person, company or internship identified by the index number used in the displayed list.\n"
            + "Parameters for company or person: \n"
            + "c - Delete a company's contact\n"
            + "p - Delete a person's contact\n"
            + "INDEX - the index of the person or company contact to be deleted\n"
            + "Example: " + COMMAND_WORD + " p 1"
            + "\n"
            + "Parameters for internship: \n"
            + "i - Delete an internship of a company\n"
            + "c/INDEX - the index of the target company\n"
            + "i/INDEX - the index of the target internship\n"
            + "Example: " + COMMAND_WORD + " i c/1 i/1";

    public static final String MESSAGE_SUCCESS = "Success message for the delete command!";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
