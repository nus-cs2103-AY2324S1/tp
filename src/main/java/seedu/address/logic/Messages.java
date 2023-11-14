package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The employee index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_NAME = "The employee name provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d employees listed!";

    public static final String MESSAGE_INVALID_FIELD_TO_READ = "The field to read provided is invalid";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_DELETE =
            "%1$d employees found! Refer to their indexes for deletion";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_MARK =
            "%1$d employees found! Refer to their indexes for marking attendance";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_ATTENDANCE_REPORT =
            "%1$d employees found! Refer to their indexes for retrieving their attendance report.";
    public static final String MESSAGE_INVALID_DATE = "Invalid command format! Date should be in format: 'dd/MM/yyyy'"
            + " and date should be valid!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_PAYROLL =
            "%1$d employees found! Refer to their indexes for payroll calculation";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_DEDUCT =
            "%1$d employees found! Refer to their indexes for deduction";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_BENEFIT =
            "%1$d employees found! Refer to their indexes for benefit";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW_PAYSLIP =
            "%1$d employees found! Refer to their indexes for payslip generation";

    public static final String MESSAGE_NO_EMPLOYEE_ON_LEAVE =
            "There are no employees on leave on %1$s";

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
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Bank Account: ")
                .append(person.getBankAccount())
                .append("; Join Date: ")
                .append(person.getJoinDate())
                .append("; Salary: ")
                .append(person.getSalary())
                .append("; Annual Leave: ")
                .append(person.getAnnualLeave().value)
                .append("; Attendance: ")
                .append(person.getWorkingStatusToday());
        return builder.toString();
    }

}
