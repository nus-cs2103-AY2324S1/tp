package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a session remark, ensuring it adheres to certain constraints.
 * A valid session remark consists of one or more alphanumeric characters and spaces and should not be blank.
 */
public class SessionRemark {
    public static final String MESSAGE_CONSTRAINTS =
            "Session remarks must only contain alphanumeric characters and spaces";
    public static final String VALIDATION_REGEX = "^(?=.*[A-Za-z0-9_-])[-A-Za-z0-9\\s_-]*$";
    public final String sessionRemark;


    /**
     * Constructs a SessionRemark with the given session remark.
     *
     * @param sessionRemark The session remark to create.
     */
    public SessionRemark(String sessionRemark) {
        requireNonNull(sessionRemark);
        checkArgument(isValidSessionRemark(sessionRemark), MESSAGE_CONSTRAINTS);
        this.sessionRemark = sessionRemark;
    }


    /**
     * Checks whether the provided string is a valid session remark.
     *
     * @param test The string to test for validity.
     * @return True if the string is a valid session remark, false otherwise.
     */
    public static boolean isValidSessionRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the session remark as a string.
     *
     * @return The session remark as a string.
     */
    @Override
    public String toString() {
        return sessionRemark;
    }

    /**
     * Checks whether this SessionRemark is equal to another object.
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
        if (!(other instanceof SessionRemark)) {
            return false;
        }

        SessionRemark otherSessionRemark = (SessionRemark) other;
        return sessionRemark.equals(otherSessionRemark.sessionRemark);
    }

    /**
     * Returns the hash code of this SessionRemark.
     *
     * @return The hash code of the session remark.
     */
    @Override
    public int hashCode() {
        return sessionRemark.hashCode();
    }
}
