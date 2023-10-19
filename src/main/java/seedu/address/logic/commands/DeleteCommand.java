package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person/group with the name provided.\n"
            + "Use 'delete n/NAME' to delete a person and 'delete g/GROUPNAME' to delete a group.\n"
            + "Parameters: " + PREFIX_NAME
            + "NAME (must be the full name of a person in the existing contactlist)\n"
            + "Parameters: " + PREFIX_GROUPTAG
            + "GROUPNAME (full name of an existing group)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Nicholas Lee \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUPTAG + "CS2103T";

    public static final String MESSAGE_TWO_PARAMETERS = "Multiple prefixes! "
            + COMMAND_WORD + " can only handle one person/group at a time.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Nicholas Lee \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUPTAG + "CS2103T";


    public DeleteCommand() {}

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract String toString();
}
