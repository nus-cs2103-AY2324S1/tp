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
            + ": Lists the free times of a person or a group.\n"
            + "Parameters: " + PREFIX_NAME
            + "NAME (must be the full name of a person in the existing contactlist)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Nicholas Lee\n"
            + "OR Parameters: " + PREFIX_GROUPTAG
            + "GROUP (must be the full name of a group in the existing contactlist)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUPTAG + "CS2103T\n";

    public ListTimeCommand() {
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract String toString();
}
