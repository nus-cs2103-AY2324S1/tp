package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Internship's application deadline in Flagship.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String, String)}
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS = "The deadline should fulfill all the following conditions:\n"
            + "1. Contains only numbers and slashes\n"
            + "2. Follows the form DD/MM/YYYY\n"
            + "3. Earlier than the internship entry's start date\n";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}. Defensive coding is implemented here with a null check. This ensures that Deadline
     * is ALWAYS associated with ONE StartDate, even if the implementation of isValidDeadline changes next time and
     * does not take in a StartDate. The reference to a StartDate is important because it only makes sense that the
     * deadline of applying to the internship is before the start date of the internship.
     *
     * @param deadline A valid deadline.
     * @param startDate A valid startDate.
     */
    public Deadline(String deadline, String startDate) {
        requireNonNull(deadline);
        requireNonNull(startDate);
        checkArgument(isValidDeadline(deadline, startDate), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDate.parse(deadline, DATE_FORMATTER);
    }

    /**
     * Returns true if the given strings for deadline and start date are valid and the deadline is earlier than the
     * start date. The given strings are also stripped to defensively guard against instances where leading or trailing
     * spaces are inserted when user directly modifies the internship.json file. This is important so that the given
     * strings do not fail the regex check.
     *
     * @param deadlineTest The deadline string to be tested.
     * @param startDateTest The start date string to be tested.
     */
    public static boolean isValidDeadline(String deadlineTest, String startDateTest) {
        String strippedDeadlineTest = deadlineTest.strip();
        String strippedStartDateTest = startDateTest.strip();
        try {
            LocalDate deadline = LocalDate.parse(strippedDeadlineTest, DATE_FORMATTER);
            LocalDate startDate = LocalDate.parse(strippedStartDateTest, DATE_FORMATTER);
            return deadline.isBefore(startDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the given strings for test are valid date.
     *
     * @param test The date string to be tested.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.deadline.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Deadline otherDeadLine = (Deadline) other;
        return this.deadline.equals(otherDeadLine.deadline);
    }

    @Override
    public int hashCode() {
        return this.deadline.hashCode();
    }

    @Override
    public int compareTo(Deadline other) {
        return this.deadline.compareTo(other.deadline);
    }
}
