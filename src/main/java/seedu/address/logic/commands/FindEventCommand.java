package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.EventNameOrGroupContainsKeywordsPredicate;

/**
 * Finds and lists all events in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "find_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose names, groups or assigned "
            + "persons contain any of the specified keywords (case-insensitive) and displays them "
            + "as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final EventNameOrGroupContainsKeywordsPredicate predicate;

    public FindEventCommand(EventNameOrGroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        predicate.setPersonList(model.getFullPersonList());
        model.updateFilteredEventList(predicate);
        int listSize = model.getFilteredEventList().size();
        return new CommandResult(
                String.format(listSize == 1 ? Messages.MESSAGE_EVENT_LISTED_OVERVIEW
                        : Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, listSize));
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
