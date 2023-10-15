package seedu.staffsnap.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.AppUtil.checkArgument;

/**
 * Represents an Interview in the applicant book.
 * Guarantees: immutable; type is valid as declared in {@link #isValidType(String)}
 */
public class Interview {

    public static final String MESSAGE_CONSTRAINTS = "Interview types should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String type;

    /**
     * Constructs a {@code Interview}.
     *
     * @param type A valid interview type.
     */
    public Interview(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        this.type = type;
    }

    /**
     * Returns true if a given string is a valid interview type.
     */
    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherInterview = (Interview) other;
        return type.equals(otherInterview.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + type + ']';
    }

}
