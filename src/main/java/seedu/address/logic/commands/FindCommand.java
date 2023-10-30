package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bookings whose names or room contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    /**
     * Constructs a FindCommand with the specified keyword predicate.
     *
     * @param predicate The keyword predicate to use for filtering bookings.
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the find command to filter and list bookings that match the keyword predicate.
     *
     * @param model The current model.
     * @return A CommandResult indicating the result of the find operation.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW, model.getFilteredBookingList().size()),
                false, false, false);
    }

    /**
     * Checks if this FindCommand is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    /**
     * Returns a string representation of this FindCommand.
     *
     * @return A string containing the details of the FindCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
