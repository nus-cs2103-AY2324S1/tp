package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all the people either in a specific group or whose name contains any keywords.
 */
public abstract class FindCommand extends Command {

    public static final java.lang.String COMMAND_WORD = "find";

    public static final java.lang.String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose names contain any of the specified keywords (case insensitive)\n"
            + "or all persons in a specified group (case sensitive) and displays them as a list.\n"
            + "Parameters: " + PREFIX_NAME
            + "KEYWORDS_IN_NAME (case insensitive)\n"
            + "Parameters: " + PREFIX_GROUPTAG
            + "GROUPNAME (full name of an existing group, case sensitive)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice alex john \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUPTAG + "CS2103T";

    public static final String MESSAGE_TWO_PARAMETERS = "Multiple prefixes! "
            + COMMAND_WORD + " can only handle one person/group at a time.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "nicholas\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUPTAG + "CS2103T";

    public static final String MESSAGE_EMPTY_NAME = "Person does not exist";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract String toString();
}
