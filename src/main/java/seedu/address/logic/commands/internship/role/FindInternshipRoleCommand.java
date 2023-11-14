package seedu.address.logic.commands.internship.role;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.internship.role.InternshipRoleNameContainsKeywordsPredicate;
import seedu.address.model.internship.task.InternshipTaskContainsInternshipRolesPredicate;


/**
 * Finds and lists all internship role AND tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindInternshipRoleCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "find-i-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internship roles AND their corresponding"
            + " tasks whose names "
            + "contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    private final InternshipRoleNameContainsKeywordsPredicate predicate;

    /**
     * Updates the model for BOTH role and task
     *
     * @param predicate predicate for roles
     */
    public FindInternshipRoleCommand(InternshipRoleNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredInternshipRoleList(predicate);
        InternshipTaskContainsInternshipRolesPredicate taskPredicate =
                new InternshipTaskContainsInternshipRolesPredicate(model.getFilteredInternshipRoleList());
        model.updateFilteredInternshipTaskList(taskPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipRoleList().size() + model.getFilteredInternshipTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindInternshipRoleCommand)) {
            return false;
        }

        FindInternshipRoleCommand otherFindCommand = (FindInternshipRoleCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
