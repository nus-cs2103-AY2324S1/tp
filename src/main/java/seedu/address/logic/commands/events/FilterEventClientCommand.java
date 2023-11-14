package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.event.EventClientContainsKeywordsPredicate;

/**
 * Finds and lists all events in events book whose clients' name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterEventClientCommand extends Command {

    public static final String COMMAND_WORD = "filter-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events who are tagged with "
            + "the specified client (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Alex Yeoh";

    private final EventClientContainsKeywordsPredicate clientPredicate;

    public FilterEventClientCommand(EventClientContainsKeywordsPredicate clientPredicate) {
        this.clientPredicate = clientPredicate;
    }



    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(clientPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterEventClientCommand)) {
            return false;
        }

        FilterEventClientCommand otherFilterEventCommand = (FilterEventClientCommand) other;
        return clientPredicate.equals(otherFilterEventCommand.clientPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("clientPredicate", clientPredicate)
                .toString();
    }
}
