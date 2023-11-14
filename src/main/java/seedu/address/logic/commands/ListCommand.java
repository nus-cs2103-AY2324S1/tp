package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists either students or attendance.\n"
            + "Parameters: "
            + "LIST_TYPE (must be either 'students' or 'attendance')\n"
            + "(applicable for list attendance only): "
            + PREFIX_WEEK + "WEEK_NUMBER "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP_ID]\n"
            + "Example: "
            + COMMAND_WORD + " students, "
            + COMMAND_WORD + " attendance w/1 tg/G01";

    @Override
    public abstract CommandResult execute(Model model);
}
