package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.model.Model;
import seedu.application.model.job.CombinedPredicate;

/**
 * Finds and lists all jobs in application book whose field contains any of the argument keywords.
 * Field is specified by the user.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all jobs that contain all of "
            + "the specified keywords (case-insensitive) in the corresponding fields and displays them as a list with "
            + "index numbers. At least one keyword must be provided.\n"
            + "Parameters: "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_INDUSTRY + "INDUSTRY] "
            + "[" + PREFIX_DEADLINE + "KEYWORDS] "
            + "[" + PREFIX_JOB_TYPE + "KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_COMPANY + "Google";

    public static final String MESSAGE_EMPTY_KEYWORDS = "Missing keywords";

    private final CombinedPredicate predicate;

    public FindCommand(CombinedPredicate predicate) {
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
