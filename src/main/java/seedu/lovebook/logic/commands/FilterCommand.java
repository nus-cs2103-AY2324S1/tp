package seedu.lovebook.logic.commands;

import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.logic.parser.Prefix;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.MetricContainsKeywordsPredicate;
import seedu.lovebook.model.person.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all dates whose specified metric contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: /METRIC KEYWORD (METRIC in the form of initial alphabet) \n"
            + "Example: " + COMMAND_WORD + PREFIX_NAME + " Bob (AKA find dates whose name that match Bob";
    private final MetricContainsKeywordsPredicate predicate;


    public FilterCommand(MetricContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
