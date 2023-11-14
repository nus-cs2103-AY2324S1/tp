package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.finance.TimeDueBetweenPredicate;


/**
 * Finds and lists all finances in Finance Tab whose timeDue is between the specified start and end times.
 */
public class FilterTimeDueCommand extends Command {

    public static final String COMMAND_WORD = "filter-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Finances whose creation or due time"
            + " is between "
            + "the specified times given as input and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_TIME_START + "START_TIME "
            + PREFIX_TIME_END + "END_TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TIME_START + "23-11-2023 "
            + PREFIX_TIME_END + "29-11-2023";

    private final TimeDueBetweenPredicate timePredicate;

    public FilterTimeDueCommand(TimeDueBetweenPredicate timePredicate) {
        this.timePredicate = timePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFinanceList(timePredicate);
        int listSize = model.getFilteredFinanceList().size();
        return new CommandResult(
                String.format(Messages.MESSAGE_FINANCE_LISTED_OVERVIEW, listSize));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterTimeDueCommand)) {
            return false;
        }

        FilterTimeDueCommand otherFilterTimeDueCommand = (FilterTimeDueCommand) other;
        return timePredicate.equals(otherFilterTimeDueCommand.timePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", timePredicate)
                .toString();
    }

}
