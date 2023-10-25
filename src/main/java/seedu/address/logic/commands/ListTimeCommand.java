package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public abstract class ListTimeCommand extends Command {

    public static final String COMMAND_WORD = "listtime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the times of the person/group with the name provided.\n"
            + "Use 'listtime n/NAME' to list time of a person and 'listtime g/GROUPNAME' to list time of a group.\n"
            + "Parameters: " + PREFIX_NAME
            + "NAME (full name of an existing person)\n"
            + "Parameters: " + PREFIX_GROUPTAG
            + "GROUPNAME (full name of an existing group)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Nicholas Lee \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUPTAG + "CS2103T";

    public ListTimeCommand() {
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract String toString();
}
