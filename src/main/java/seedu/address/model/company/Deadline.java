package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Company's deadline in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline implements Comparable<Deadline> {

    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Deadline should not be blank! Please try again with a valid deadline of format DD-MM-YYYY.";
    public static final String MESSAGE_CONSTRAINTS_INVALID_DEADLINE =
            "Oops! You have entered an invalid deadline! Please try again with the deadline format DD-MM-YYYY.";

    public static final String MESSAGE_CONSTRAINTS_WRONG_FORMAT =
            "Oops! You have entered an incorrect format for the deadline! Please use the format DD-MM-YYYY.";

    public static final String FORMAT_REGEX = "^\\d{2}-\\d{2}-\\d{4}$";
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
    public final LocalDate value;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(!deadline.isBlank(), MESSAGE_CONSTRAINTS_NON_EMPTY);
        checkArgument(isValidFormat(deadline), MESSAGE_CONSTRAINTS_WRONG_FORMAT);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS_INVALID_DEADLINE);
        this.value = LocalDate.parse(deadline, FORMATTER);
    }

    /**
     * Returns true if the given string follows the correct format.
     */
    public static boolean isValidFormat(String test) {
        return test.matches(FORMAT_REGEX);
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        if (!isValidFormat(test)) {
            return false;
        }

        try {
            LocalDate.parse(test, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return value.equals(otherDeadline.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    //@@author papataco14-reused
    // The compareTo method was adapted from GitHub co-pilot suggestion
    @Override
    public int compareTo(Deadline other) {
        if (other == null) {
            return 1; // or -1 depending on how you want to handle nulls
        }
        return this.value.compareTo(other.value);
    }
}
