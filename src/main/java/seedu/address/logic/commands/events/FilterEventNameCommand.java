package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.event.EventNameContainsKeywordsPredicate;

/**
 * Finds and lists all events in events book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterEventNameCommand extends Command {

    public static final String COMMAND_WORD = "filter-n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " meeting";

    private final EventNameContainsKeywordsPredicate namePredicate;

    public FilterEventNameCommand(EventNameContainsKeywordsPredicate namePredicate) {
        this.namePredicate = namePredicate;
    }



    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(namePredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterEventNameCommand)) {
            return false;
        }

        FilterEventNameCommand otherFilterEventCommand = (FilterEventNameCommand) other;
        return namePredicate.equals(otherFilterEventCommand.namePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .toString();
    }
}
