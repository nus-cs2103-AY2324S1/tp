package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.NameAndTagContainKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords in their names or tags.
 * Keyword matching is case-insensitive.
 */
public class FindPersonCommand extends FindCommand {
    public static final String MESSAGE_SUCCESS = "Success message for the find command!";

    private final NameAndTagContainKeywordsPredicate predicate;

    /**
     * Creates a FindPersonCommand to find persons with the specified {@code NameAndTagContainKeywordsPredicate}
     */
    public FindPersonCommand(NameAndTagContainKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new RegularCommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindPersonCommand)) {
            return false;
        }

        FindPersonCommand otherFindPersonCommand = (FindPersonCommand) other;
        return predicate.equals(otherFindPersonCommand.predicate);
    }
}
