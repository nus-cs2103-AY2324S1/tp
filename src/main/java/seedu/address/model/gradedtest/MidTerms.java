package seedu.address.model.gradedtest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a MidTerm result.
 * A MidTerm result is a positive number that indicates the score achieved in the exam.
 * The result can be an integer or a decimal number.
 */
public class MidTerms {
    public static final String MESSAGE_CONSTRAINTS =
            "MidTerm scores should be a positive number";
    public static final String VALIDATION_REGEX = "(?:-|\\d+(\\.\\d+)?)";

    public final String value;

    /**
     * Constructs a {@code MidTerms}.
     *
     * @param midTermResults A valid midterms result.
     */
    public MidTerms(String midTermResults) {
        requireNonNull(midTermResults);
        checkArgument(isValidMidTermResult(midTermResults), MESSAGE_CONSTRAINTS);
        this.value = midTermResults;
    }

    /**
     * Returns true if a given string is a valid Midterms result.
     */
    public static boolean isValidMidTermResult(String test) {
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
        if (!(other instanceof MidTerms)) {
            return false;
        }

        MidTerms otherMidTerm = (MidTerms) other;
        return value.equals(otherMidTerm.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
