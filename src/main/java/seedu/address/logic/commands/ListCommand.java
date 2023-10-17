package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNUMBER;

/**
 * Lists all persons in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists either students or attendance.\n"
            + "Parameters: "
            + "LIST_TYPE (must be either 'students' or 'attendance') "
            + PREFIX_COURSETUTORIAL + "TAG "
            + PREFIX_TUTORIALNUMBER + "TUTORIALNUMBER (must be a positive integer) "
            + "[applicable for list attendance only]\n"
            + "Example: " + COMMAND_WORD + " students";

    public ListCommand() {}
}
