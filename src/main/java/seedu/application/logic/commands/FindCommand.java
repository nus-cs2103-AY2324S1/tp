package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.model.Model;
import seedu.application.model.job.FieldContainsKeywordsPredicate;

/**
 * Finds and lists all jobs in application book whose field contains any of the argument keywords.
 * Field is specified by the user.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all jobs whose field contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: FIELD_SPECIFIER KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " -r software database network";

    private final FieldContainsKeywordsPredicate predicate;

    public FindCommand(FieldContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredJobList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_JOBS_LISTED_OVERVIEW, model.getFilteredJobList().size()));
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
