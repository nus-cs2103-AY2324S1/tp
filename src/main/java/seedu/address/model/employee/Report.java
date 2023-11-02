package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.remark.RemarkList;


/**
 * Represents an Employee's report.
 * Guarantees: immutable; is valid as declared in {@link #isValidReport(String)}
 */
public class Report {
    public final Employee employee;
    public final double overtimeHours;
    public final double overtimePay;
    public final int numOfLeaves;
    public final RemarkList remarkList;

    /**
     * Constructs a {@code Report}.
     * @param employee A valid employee.
     * @param overtimeHours valid overtime hours.
     * @param overtimePay valid overtime pay.
     * @param numOfLeaves valid number of leaves.
     * @param remarkList valid remark list.
     */
    public Report(Employee employee, double overtimeHours, double overtimePay, int numOfLeaves,
                  RemarkList remarkList) {

        requireNonNull(employee);
        requireNonNull(overtimeHours);
        requireNonNull(overtimePay);
        requireNonNull(numOfLeaves);
        requireNonNull(remarkList);

        this.employee = employee;
        this.overtimeHours = overtimeHours;
        this.overtimePay = overtimePay;
        this.numOfLeaves = numOfLeaves;
        this.remarkList = remarkList;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EMPLOYEE REPORT\n")
                .append("\nDate: ")
                .append(LocalDate.now())
                .append("\n====================================\n")
                .append("Employee Name: ")
                .append(employee.getName().fullName)
                .append("\nOvertime Hours: ")
                .append(overtimeHours)
                .append("\nOvertime Pay: ")
                .append(overtimePay)
                .append("\nNumber of Leaves: ")
                .append(numOfLeaves)
                .append("\nRemarks:\n")
                .append(remarkList);
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // same object
                || (other instanceof Report // instanceof handles nulls
                && employee.equals(((Report) other).employee)
                && overtimeHours == ((Report) other).overtimeHours
                && overtimePay == ((Report) other).overtimePay
                && numOfLeaves == ((Report) other).numOfLeaves
                && remarkList.equals(((Report) other).remarkList)); // state check
    }
}
