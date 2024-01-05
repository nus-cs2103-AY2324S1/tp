package seedu.address.model.gradedtest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Practical Exam result.
 * A Practical Exam result is a positive number that indicates the score achieved in the exam.
 * The result can be an integer or a decimal number.
 */
public class PracticalExam {
    public static final String MESSAGE_CONSTRAINTS =
            "Practical Exam scores should be a positive number";
    public static final String VALIDATION_REGEX = "(?:-|\\d+(\\.\\d+)?)";

    public final String value;

    /**
     * Constructs a {@code PracticalExam}.
     *
     * @param peResults A valid practical exam result.
     */
    public PracticalExam(String peResults) {
        requireNonNull(peResults);
        checkArgument(isValidPeResult(peResults), MESSAGE_CONSTRAINTS);
        this.value = peResults;
    }

    /**
     * Returns true if a given string is a valid PE result.
     */
    public static boolean isValidPeResult(String test) {
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
        if (!(other instanceof PracticalExam)) {
            return false;
        }

        PracticalExam otherPE = (PracticalExam) other;
        return value.equals(otherPE.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
