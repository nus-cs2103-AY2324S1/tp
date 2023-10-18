package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.MetricContainsKeywordPredicate;

import java.util.ArrayList;


/**
 * Filter based on a specific metric and list the dates whose metric contains the keyword.
 * Keyword matching is case insensitive.
 *
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all dates whose specified metric contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: /METRIC KEYWORD (METRIC in the form of initial alphabet) \n"
            + "Example: " + COMMAND_WORD + PREFIX_NAME + " Bob (AKA find dates whose name that match Bob";
    private final ArrayList<MetricContainsKeywordPredicate> predicates;


    public FilterCommand(ArrayList<MetricContainsKeywordPredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        for (MetricContainsKeywordPredicate predicate : predicates) {
            model.updateFilteredPersonList(predicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicates.equals(otherFilterCommand.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicates", predicates)
                .toString();
    }
}
