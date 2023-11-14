package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.event.EventTimeBeforePredicate;

/**
 * Finds and lists all events in address book whose start time is before the specified time.
 */
public class FilterEventTimeCommand extends Command {

    public static final String COMMAND_WORD = "filter-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose start time is before "
            + "the specified time given as input and displays them as a list with index numbers.\n"
            + "Parameters: Time \n"
            + "Example: " + COMMAND_WORD + " 23-11-2023";

    private final EventTimeBeforePredicate timePredicate;

    public FilterEventTimeCommand(EventTimeBeforePredicate timePredicate) {
        this.timePredicate = timePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(timePredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterEventTimeCommand)) {
            return false;
        }

        FilterEventTimeCommand otherFilterEventCommand = (FilterEventTimeCommand) other;
        return timePredicate.equals(otherFilterEventCommand.timePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", timePredicate)
                .toString();
    }
}
