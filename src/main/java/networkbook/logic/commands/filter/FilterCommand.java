package networkbook.logic.commands.filter;

import networkbook.logic.commands.Command;
import networkbook.logic.commands.CommandResult;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.logic.parser.CliSyntax;
import networkbook.model.Model;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters all persons by a specified field (course, specialisation, or grad year)"
            + " and returns a list of contacts that contain the specified keywords.\n"
            + "Course and grad year can be additionally filtered to exclude contacts"
            + " who have finished the course or graduated.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_FILTER_FIELD + " FIELD "
            + "[" + CliSyntax.PREFIX_FILTER_FIN + " true/false (false by default)]\n"
            + "Example: " + COMMAND_WORD;

    public CommandResult execute(Model model) {
        return new CommandResult("To be implemented");
    }
}
