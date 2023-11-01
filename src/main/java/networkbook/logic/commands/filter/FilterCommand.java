package networkbook.logic.commands.filter;

import networkbook.logic.commands.Command;
import networkbook.logic.commands.CommandResult;
import networkbook.logic.parser.CliSyntax;
import networkbook.model.Model;

/**
 * Filter contacts based on a specified set of criteria; valid fields to check are
 * course, specialisation, and grad year.
 *
 * TODO: Extend functionality to grad year and specialisation
 */
public abstract class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters all persons by a specified field (course, specialisation, or grad year)"
            + " and returns a list of contacts that contain the specified keywords.\n"
            + "Course and grad year can be additionally filtered to exclude contacts"
            + " who have finished the course or graduated.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_FILTER_FIELD + " FIELD "
            + "[" + CliSyntax.PREFIX_FILTER_FIN + " true/false (false by default)]\n"
            + "Example: " + COMMAND_WORD + " /with a b c /taken false";

    public static final String MESSAGE_SUCCESS = "Here is the list of contacts whose courses contain %1$s:";
    public static final String MESSAGE_EXCL_FIN = "\n(excluding contacts who have finished taking)";
    public static final String MESSAGE_PERSONS_FOUND_OVERVIEW = "\n(%1$s contacts found)";

    /**
     * Executes the FilterCommand object and returns a message to the user.
     *
     * @param model {@code Model} which the command should operate on.
     */
    public abstract CommandResult execute(Model model);

}
