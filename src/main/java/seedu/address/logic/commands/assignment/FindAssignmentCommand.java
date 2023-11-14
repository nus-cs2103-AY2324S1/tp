package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.AssignmentNameContainsKeywordsPredicate;


/**
 * Finds and lists all assignments in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindAssignmentCommand extends AssignmentCommand {

    public static final String COMMAND_WORD = "find-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all assignment whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    private final AssignmentNameContainsKeywordsPredicate predicate;

    public FindAssignmentCommand(AssignmentNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredAssignmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindAssignmentCommand)) {
            return false;
        }

        FindAssignmentCommand otherFindCommand = (FindAssignmentCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
