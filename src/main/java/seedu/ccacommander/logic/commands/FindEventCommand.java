package seedu.ccacommander.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ccacommander.commons.util.ToStringBuilder;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.event.EventNameContainsKeywordsPredicate;

/**
 * Finds and lists all events in CcaCommander whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "findEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " party marathon study";

    private final EventNameContainsKeywordsPredicate predicate;

    public FindEventCommand(EventNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindEventCommand)) {
            return false;
        }

        FindEventCommand otherFindEventCommand = (FindEventCommand) other;
        return predicate.equals(otherFindEventCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
