package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.employee.Employee;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX = "The employee index provided is invalid";
    public static final String MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID = "The ID provided does not exist in the list";
    public static final String MESSAGE_EMPLOYEES_LISTED_OVERVIEW = "%1$d employees listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_REPORT_STRING =
                "Name: %1$s\nOvertime hours: %2$s\nOvertime pay: $%3$s\nNumber of leaves used: %4$s\nRemarks:\n%5$s";
    public static final String MESSAGE_INVALID_DATE = "Invalid date! Dates should be valid and in yyyy-MM-dd format.";
    public static final String MESSAGE_MISSING_DATE = "No date given! Dates should be valid and in yyyy-MM-dd format.";
    public static final String MESSAGE_EMPLOYEES_ON_LEAVE_OVERVIEW = "%1$d employees are on leave on %2$s!";
    public static final String MESSAGE_REPORT_SAVE_ERROR = "Error saving report to file";
    public static final String MESSAGE_MISSING_REMARK = "No remark given!";


    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code employee} for display to the user.
     */
    public static String format(Employee employee) {
        final StringBuilder builder = new StringBuilder();
        builder.append(employee.getName())
                .append("; Position: ")
                .append(employee.getPosition())
                .append("; Id: ")
                .append(employee.getId())
                .append("; Phone: ")
                .append(employee.getPhone())
                .append("; Email: ")
                .append(employee.getEmail())
                .append("; Salary: ")
                .append(employee.getSalary())
                .append("; Departments: ");
        employee.getDepartments().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the OvertimeHours of {@code employee} for display to the user.
     */
    public static String formatOvertimeHours(Employee employee) {
        final StringBuilder builder = new StringBuilder();
        builder.append(employee.getName())
                .append("; Position: ")
                .append(employee.getPosition())
                .append("; Id: ")
                .append(employee.getId());
        return builder.toString();
    }

    /**
     * Formats the Leaves of {@code employee} for display to the user.
     */
    public static String formatLeaves(Employee employee) {
        final StringBuilder builder = new StringBuilder();
        builder.append(employee.getName())
                .append("; Position: ")
                .append(employee.getPosition())
                .append("; Id: ")
                .append(employee.getId())
                .append("; \nLeaves taken: \n")
                .append(employee.getLeaveList().toString());
        return builder.toString();
    }

    /**
     * Formats the Remarks of {@code employee} for display to the user.
     */
    public static String formatRemarks(Employee employee) {
        final StringBuilder builder = new StringBuilder();
        builder.append(employee.getName())
                .append("; Position: ")
                .append(employee.getPosition())
                .append("; Id: ")
                .append(employee.getId())
                .append("; \nRemarks: \n")
                .append(employee.getRemarkList().toString());
        return builder.toString();
    }

}
