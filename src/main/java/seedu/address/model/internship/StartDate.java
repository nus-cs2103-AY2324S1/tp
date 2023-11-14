package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Internship's start date in Flagship.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartDate(String)}
 */
public class StartDate implements Comparable<StartDate> {

    public static final String MESSAGE_CONSTRAINTS = "The start date should fulfill all the following conditions:\n"
            + "1. Contains only numbers and slashes\n"
            + "2. Follows the form DD/MM/YYYY\n"
            + "3. Later than the internship entry's deadline\n";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate startDate;

    /**
     * Constructs a {@code StartDate}.
     *
     * @param startDate A valid start date.
     */
    public StartDate(String startDate) {
        requireNonNull(startDate);
        checkArgument(isValidStartDate(startDate), MESSAGE_CONSTRAINTS);
        this.startDate = LocalDate.parse(startDate, DATE_FORMATTER);
    }

    /**
     * Verifies whether the given string constitutes a valid start date. The given string is also stripped to
     * defensively guard against instances where leading or trailing spaces are inserted when user directly modifies the
     * internship.json file. This is important so that the given string does not fail the regex check.
     *
     * @param test The given string to be tested.
     * @return A boolean representing whether the string input is valid.
     */
    public static boolean isValidStartDate(String test) {
        String strippedTest = test.strip();
        try {
            LocalDate.parse(strippedTest, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.startDate.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        StartDate otherStartDate = (StartDate) other;
        return this.startDate.equals(otherStartDate.startDate);
    }

    @Override
    public int hashCode() {
        return this.startDate.hashCode();
    }

    @Override
    public int compareTo(StartDate other) {
        return this.startDate.compareTo(other.startDate);
    }
}
