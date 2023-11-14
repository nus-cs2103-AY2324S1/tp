package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;

import seedu.lovebook.commons.util.PredicatesUtil;
import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.date.MetricContainsKeywordPredicate;


/**
 * Filter based on a specific metric and list the Dates whose metric contains the keyword.
 * Keyword matching is case-insensitive.
 * @author lynnlow175
 *
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Dates whose specified metric contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: METRIC/ KEYWORD \n"
            + "Parameter constraints: Metric limited to gender, name, age, height only\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " Bob (i.e. find Dates whose name equals Bob)";
    private final ArrayList<MetricContainsKeywordPredicate> predicateList;


    public FilterCommand(ArrayList<MetricContainsKeywordPredicate> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new PredicatesUtil(predicateList));
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
        return predicateList.equals(otherFilterCommand.predicateList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicateList", predicateList)
                .toString();
    }
}
