package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Subject in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)} (String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS = "Subject can take any values, and it should not be blank";

    /*
     * The first character of the subject must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        value = subject;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * @return a defensive copy of subject
     */

    public Subject copy() {
        return new Subject(this.value);
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
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherSubject = (Subject) other;
        return value.equals(otherSubject.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
