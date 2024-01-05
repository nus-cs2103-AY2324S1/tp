package seedu.address.model.gradedtest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reading Assessment result.
 * A Reading Assessment result is a positive number that indicates the score achieved in the assessment.
 * The result can be an integer or a decimal number.
 */
public class ReadingAssessment2 {
    public static final String MESSAGE_CONSTRAINTS =
            "Reading Assessment 2 scores should be a positive number";
    public static final String VALIDATION_REGEX = "(?:-|\\d+(\\.\\d+)?)";

    public final String value;

    /**
     * Constructs a {@code ReadingAssessment2}.
     *
     * @param raResult A valid ra2 Result.
     */
    public ReadingAssessment2(String raResult) {
        requireNonNull(raResult);
        checkArgument(isValidRaResult(raResult), MESSAGE_CONSTRAINTS);
        this.value = raResult;
    }

    /**
     * Returns true if a given string is a valid RA2 result.
     */
    public static boolean isValidRaResult(String test) {
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
        if (!(other instanceof ReadingAssessment2)) {
            return false;
        }

        ReadingAssessment2 otherRA = (ReadingAssessment2) other;
        return value.equals(otherRA.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
