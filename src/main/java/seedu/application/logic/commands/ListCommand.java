package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.model.Model;
import seedu.application.model.job.FieldComparator;

/**
 * Lists all jobs in the application book to the user.
 * List order is based on the specifier provided.
 * If no specifier is provided, jobs are listed in the order they were added.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed jobs";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current list of jobs based on "
            + "the specified field and displays them as a list with index numbers.\n"
            + "Parameters: FIELD_SPECIFIER\n\n"
            + "Example: " + COMMAND_WORD + " -c";

    public static final String MESSAGE_INVALID_SPECIFIER = "Specifiers should only be one of the following:\n"
            + "-c (Company), -r (Role), -d (Deadline), -s (Status)";

    private final FieldComparator comparator;

    public ListCommand(FieldComparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_JOBS);
        if (comparator.hasSpecifier()) {
            model.sortJobs(comparator);
        } else {
            model.unsortJobs();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        ListCommand otherListCommand = (ListCommand) other;
        return comparator.equals(otherListCommand.comparator);
    }
}
