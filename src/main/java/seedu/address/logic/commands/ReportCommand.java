package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Report;
import seedu.address.storage.ReportStorage;

/**
 * Reports the performance metrics (overtime hours, overtime pay, number of leaves) of an employee in the address book.
 * Downloads the report as a text file.
 */
public class ReportCommand extends Command {

    public static final String COMMAND_WORD = "report";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reports the performance metrics (overtime hours, "
            + "overtime pay, number of leaves used, remarks (if any)) of an employee in the employee book.\n"
            + "Parameters: EMPLOYEE_ID\n"
            + "Example: " + COMMAND_WORD + " EID1234-5678";

    private final Id targetId;

    public ReportCommand(Id targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        for (Employee employee : lastShownList) {
            if (employee.getId().equals(targetId)) {
                Report report = generateReport(employee);
                try {
                    ReportStorage.saveReport(report);
                } catch (CommandException e) {
                    throw new CommandException(Messages.MESSAGE_REPORT_SAVE_ERROR);
                }
                return new CommandResult(String.format(Messages.MESSAGE_REPORT_STRING,
                        employee.getName().fullName, employee.getOvertimeHours(), report.overtimePay,
                        report.numOfLeaves, report.remarkList));
            }
        }


        throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    /**
     * Generates a report for the given employee.
     *
     * @param employee Employee to generate report for.
     * @return Report for the given employee.
     */
    public Report generateReport(Employee employee) {
        requireNonNull(employee);
        return new Report(employee, employee.getOvertimeHours().value, employee.getOvertimePay(),
                employee.getNumOfLeaves(), employee.getRemarkList());
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
                .add("targetId", targetId)
                .toString();
    }
}
