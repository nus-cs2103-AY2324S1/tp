package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a Date in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidDate(String)}
 */
public class Birthdate {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be in the following format: dd-mm-yyyy and should be a valid day in the past!";

    public static final String VALIDATION_REGEX = "\\d{2}-\\d{2}-\\d{4}";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public final String value;

    /**
     * Constructs a {@code Birthdate}.
     *
     * @param date A valid date
     */
    public Birthdate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid date
     */
    public static boolean isValidDate(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            // Ensure that birthdate is valid and not in the future
            LocalDate date = LocalDate.parse(test, DATE_TIME_FORMATTER);
            return date.format(DATE_TIME_FORMATTER).equals(test) && date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Birthdate)) {
            return false;
        }

        Birthdate otherBirthdate = (Birthdate) other;
        return value.equals(otherBirthdate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
