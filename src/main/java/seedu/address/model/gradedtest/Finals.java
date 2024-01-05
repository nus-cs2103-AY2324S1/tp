package seedu.address.model.gradedtest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Final result.
 * A Final result is a positive number and indicates the score achieved in the exam.
 * The result can be an integer or a decimal number.
 */
public class Finals {
    public static final String MESSAGE_CONSTRAINTS =
            "Final scores should be a positive number";
    public static final String VALIDATION_REGEX = "(?:-|\\d+(\\.\\d+)?)";
    public final String value;

    /**
     * Constructs a {@code Finals}.
     *
     * @param finalResults A valid finals result.
     */
    public Finals(String finalResults) {
        requireNonNull(finalResults);
        checkArgument(isValidFinalsResult(finalResults), MESSAGE_CONSTRAINTS);
        this.value = finalResults;
    }

    /**
     * Returns true if a given string is a valid finals result.
     */
    public static boolean isValidFinalsResult(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return value.equals("") ? "No Score Provided" : value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Finals)) {
            return false;
        }

        Finals otherFinals = (Finals) other;
        return value.equals(otherFinals.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
