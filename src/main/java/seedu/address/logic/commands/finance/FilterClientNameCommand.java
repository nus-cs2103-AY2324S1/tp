package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.finance.ClientNameContainsKeywordsPredicate;
/**
 * Finds and lists all persons in Finance Tab whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterClientNameCommand extends Command {

    public static final String COMMAND_WORD = "filter-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " John Doe";

    private final ClientNameContainsKeywordsPredicate namePredicate;

    public FilterClientNameCommand(ClientNameContainsKeywordsPredicate namePredicate) {
        this.namePredicate = namePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFinanceList(namePredicate);
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
        if (!(other instanceof FilterClientNameCommand)) {
            return false;
        }

        FilterClientNameCommand otherFilterClientNameCommand = (FilterClientNameCommand) other;
        return namePredicate.equals(otherFilterClientNameCommand.namePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", namePredicate)
                .toString();
    }

}
