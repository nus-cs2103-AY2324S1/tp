package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be in the format of YYYY-MM-DD and should be a valid date.";
    public static final String VALIDATION_REGEX =
            "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
    public final String date;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        date = birthday;
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static Boolean isValidBirthday(String test) {
        if (test == "") {
            return true;
        }
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }

        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return date;
    }

    public LocalDate getDate() {
        if (date == "") {
            return LocalDate.parse("1900-01-01");
        }
        return LocalDate.parse(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return date.equals(otherBirthday.date);

    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
