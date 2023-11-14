package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

/**
 * Command that filters out the people based on their role
 */
public class FindRoleCommand extends Command {
    /**
     * Keyword to trigger the searchrole command.
     */
    public static final String COMMAND_WORD = "searchrole";

    /**
     * Usage instructions for the 'searchrole' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose roles contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " TA";

    private final RoleContainsKeywordsPredicate predicate;

    /**
     * Constructs a FindRoleCommand with a specific keyword predicate.
     *
     * @param predicate The predicate used to filter persons by role.
     */
    public FindRoleCommand(RoleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the FindRoleCommand to filter and list persons whose roles contain the specified keywords.
     *
     * @param model The model that this command should operate on.
     * @return The CommandResult containing a summary of the filtered persons.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Checks equality of the FindRoleCommand with another object.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, otherwise returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindRoleCommand)) {
            return false;
        }

        FindRoleCommand otherFindCommand = (FindRoleCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    /**
     * Returns a string representation of the FindRoleCommand.
     *
     * @return A string representing the FindRoleCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
