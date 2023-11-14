package seedu.address.logic.commands.internship.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.internship.task.InternshipTaskNameContainsKeywordsPredicate;


/**
 * Finds and lists all internship tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindInternshipTaskCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "find-i-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internship tasks and their corresponding"
            + " roles whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    private final InternshipTaskNameContainsKeywordsPredicate predicate;

    public FindInternshipTaskCommand(InternshipTaskNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredInternshipTaskList(predicate);

        model.updateFilteredInternshipRoleList(role -> {
            for (InternshipTask internshipTask : model.getFilteredInternshipTaskList()) {
                if (internshipTask.getInternshipRole().equals(role)) {
                    return true;
                }
            }
            return false;
        });

        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindInternshipTaskCommand)) {
            return false;
        }

        FindInternshipTaskCommand otherFindCommand = (FindInternshipTaskCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
