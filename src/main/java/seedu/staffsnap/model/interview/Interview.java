package seedu.staffsnap.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.AppUtil.checkArgument;

/**
 * Represents an Interview in the applicant book.
 * Guarantees: immutable; type is valid as declared in {@link #isValidType(String)}
 */
public class Interview implements Comparable<Interview> {

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

    public String getType() {
        return type;
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

    /**
     * @param o the object to be compared.
     * @return the value 0 if the argument Interview is equal to this Interview; a value less than 0 if this
     *      Interview type is lexicographically less than the Interview argument's type; and a value greater than
     *      0 if this Interview type is lexicographically greater than the Interview argument's type.
     */
    @Override
    public int compareTo(Interview o) {
        return this.type.compareTo(o.type);
    }

}
