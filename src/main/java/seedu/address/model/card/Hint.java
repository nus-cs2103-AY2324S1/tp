package seedu.address.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a possible hint to a question of a Card.
 */
public class Hint {

    public static final String MESSAGE_CONSTRAINTS =
            "Hints should only contain alphanumeric characters and some special characters"
                    + ", and it should not be blank";

    // All special characters and spaces are allowed alongside alphanumeric characters
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9!@#$%^&*(),./?\"'<>;:{}\\[\\]\\-=_+\\s]+$";

    /** Hint to be displayed to user when prompted */
    public final String hint;

    /**
     * Constructs a {@code Hint}.
     *
     * @param hint A Hint to be displayed when prompted.
     */
    public Hint(String hint) {
        requireNonNull(hint);

        checkArgument(isValidHint(hint), MESSAGE_CONSTRAINTS);
        this.hint = hint;
    }

    /**
     * Returns true if a given string is a valid hint.
     */
    // @@author weeweh-reused
    // Reused from AB3 to match regex
    public static boolean isValidHint(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return hint;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Hint)) {
            return false;
        }

        Hint otherHint = (Hint) other;
        return hint.equals(otherHint.hint);
    }

    @Override
    public int hashCode() {
        return hint.hashCode();
    }

    /**
     * Represents an EmptyHint when a Card is not provided a Hint
     */
    public static class EmptyHint extends Hint {
        public static final String EMPTY_HINT_STRING = "No Hint has been provided.";

        public EmptyHint() {
            super(EMPTY_HINT_STRING);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EmptyHint)) {
                return false;
            }
            return super.equals(other);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
