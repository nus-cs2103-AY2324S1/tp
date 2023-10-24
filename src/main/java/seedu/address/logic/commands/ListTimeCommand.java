package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
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

    public static final String MESSAGE_NO_PERSON_WITH_NAME_FOUND = "No person with such name found.\n"
            + "Please provide the person's full name as in the existing contactlist.";

    public static final String MESSAGE_NO_GROUP_WITH_NAME_FOUND = "No group with such name found.\n"
            + "Please provide the group's full name as in the existing contactlist.";


    public ListTimeCommand() {
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract String toString();
}
