package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.application.model.Model;
import seedu.application.model.job.FieldComparator;

/**
 * Lists all jobs in the application book to the user.
 * Sort order is based on the specifier provided.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Sorted jobs";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of jobs based on "
            + "the specified field and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ROLE;

    private final FieldComparator comparator;

    /**
     * @param comparator The comparator used to sort jobs in the list.
     */
    public SortCommand(FieldComparator comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortJobs(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherListCommand = (SortCommand) other;
        return comparator.equals(otherListCommand.comparator);
    }
}
