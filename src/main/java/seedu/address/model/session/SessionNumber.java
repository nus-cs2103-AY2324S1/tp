package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a session number, ensuring it adheres to certain constraints.
 * A valid session number consists of one or more digits and should not be blank.
 */
public class SessionNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Session numbers must only contain numbers, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[0-9]\\d*$";
    public final String sessionNumber;

    /**
     * Constructs a SessionNumber with the given session number.
     *
     * @param sessionNumber The session number to create.
     */
    public SessionNumber(String sessionNumber) {
        requireNonNull(sessionNumber);
        checkArgument(isValidSessionNumber(sessionNumber), MESSAGE_CONSTRAINTS);
        this.sessionNumber = sessionNumber;
    }

    /**
     * Checks whether the provided string is a valid session number.
     *
     * @param test The string to test for validity.
     * @return True if the string is a valid session number, false otherwise.
     */
    public static boolean isValidSessionNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the session number as a string.
     *
     * @return The session number as a string.
     */
    @Override
    public String toString() {
        return sessionNumber;
    }

    /**
     * Checks whether this SessionNumber is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SessionNumber)) {
            return false;
        }

        SessionNumber otherSessionNumber = (SessionNumber) other;
        return sessionNumber.equals(otherSessionNumber.sessionNumber);
    }

    /**
     * Returns the hash code of this SessionNumber.
     *
     * @return The hash code of the session number.
     */
    @Override
    public int hashCode() {
        return sessionNumber.hashCode();
    }
}
