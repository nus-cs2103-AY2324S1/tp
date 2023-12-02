package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.predicates.ApplicationStatusPredicate;

/**
 * Filters the company list by application status.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE =
            "Format: " + COMMAND_WORD + " " + PREFIX_STATUS + "STATUS. Valid statuses are: PA, PI, PO, A, R\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STATUS + "PA";

    public static final String MESSAGE_SUCCESS = "Filtered company list by application status: %1$s";

    private static final Logger logger = LogsCenter.getLogger(FilterCommand.class);
    private final ApplicationStatus status;
    private final ApplicationStatusPredicate predicate;

    /**
     * Creates a FilterCommand to filter the company list by the specified {@code ApplicationStatus}.
     *
     * @param status application status to filter the company list by.
     * @param predicate predicate to filter the company list by.
     */
    public FilterCommand(ApplicationStatus status, ApplicationStatusPredicate predicate) {
        this.status = status;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.info("Executing filter command with status: " + status);
        model.filterCompaniesByStatus(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, status));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return status.equals(otherFilterCommand.status)
                && predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("status", status)
                .add("predicate", predicate)
                .toString();
    }
}
