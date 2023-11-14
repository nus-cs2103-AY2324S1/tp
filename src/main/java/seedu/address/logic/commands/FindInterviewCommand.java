package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.interview.JobContainsKeywordsPredicate;

//@@author jonyxzx
/**
 * Finds and lists all interviews in address book which the job-role contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindInterviewCommand extends Command {

    public static final String COMMAND_WORD = "find-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all interviews which the job-role contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD(S)\n"
            + "Example: " + COMMAND_WORD + " software-engineer data-analyst";

    private final JobContainsKeywordsPredicate predicateJob;

    public FindInterviewCommand(JobContainsKeywordsPredicate predicate) {
        this.predicateJob = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewList(predicateJob);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERVIEWS_LISTED_OVERVIEW, model.getFilteredInterviewList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindInterviewCommand)) {
            return false;
        }

        FindInterviewCommand otherFindCommand = (FindInterviewCommand) other;
        return predicateJob.equals(otherFindCommand.predicateJob);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicateJob)
                .toString();
    }
}
