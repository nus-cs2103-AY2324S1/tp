package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

import java.time.LocalDate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;

/**
 * Lists all employees in address book who are on leave on the specified date.
 */
public class ListLeaveCommand extends Command {
    public static final String COMMAND_WORD = "listleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all employees that are on leave "
            + "on the specified date and displays them as a list with index numbers.\n"
            + "Parameters: DATE (yyyy-MM-dd)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ON + "2023-11-11";

    public static final String INVALID_DATE = "dates must be valid and in yyyy-MM-dd format.";
    public static final String MISSING_DATE = "No date given! Dates must be in yyyy-MM-dd format.";

    private final LocalDate date;

    /**
     * Creates a ListLeaveCommand to view employees on leave on the specified date
     */
    public ListLeaveCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(employee -> employee.isOnLeave(date));
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_ON_LEAVE_OVERVIEW,
                        model.getFilteredEmployeeList().size(), date));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListLeaveCommand)) {
            return false;
        }

        ListLeaveCommand otherListLeaveCommand = (ListLeaveCommand) other;
        return date.equals(otherListLeaveCommand.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("date", date)
                .toString();
    }
}
