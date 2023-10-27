package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;

/**
 * Reports the performance metrics (overtime hours, overtime pay, number of leaves) of an employee in the address book.
 * Keyword matching is case insensitive.
 */
public class ReportCommand extends Command {

    public static final String COMMAND_WORD = "report";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reports the performance metrics (overtime hours, "
            + "overtime pay, number of leaves) of an employee in the address book.\n"
            + "Parameters: EMPLOYEE_ID\n"
            + "Example: " + COMMAND_WORD + " EID1234-5678";

    private final Id targetId;

    public ReportCommand(Id targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        for (Employee employee : lastShownList) {
            if (employee.getId().equals(targetId)) {
                return new CommandResult(String.format(Messages.MESSAGE_REPORT_STRING, employee.getName(), employee.getOvertimeHours(),
                        employee.getOvertimePay(), employee.getNumOfLeaves()));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReportCommand)) {
            return false;
        }

        ReportCommand otherReportCommand = (ReportCommand) other;
        return targetId.equals(otherReportCommand.targetId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", targetId)
                .toString();
    }
}
