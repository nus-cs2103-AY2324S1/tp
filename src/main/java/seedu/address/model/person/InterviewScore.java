package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Grade Point Average (InterviewScore) in the applicant list.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewScore(double)}
 */
public class InterviewScore implements Comparable<InterviewScore> {

    public static final String MESSAGE_CONSTRAINTS = "The interview score must be between 0.0 and 10.0";

    public final double value;

    /**
     * Constructs a {@code InterviewScore}.
     *
     * @param score A possible interview score of a person.
     */
    public InterviewScore(double score) {
        requireNonNull(score);
        checkArgument(isValidInterviewScore(score), MESSAGE_CONSTRAINTS);
        value = score;
    }

    /**
     * Returns true if a given double is a valid {@code InterviewScore}.
     */
    public static boolean isValidInterviewScore(double test) {
        return test >= 0.0 && test <= 10.0;
    }

    /**
     * Compares this score with another score and returns an integer representing the comparison.
     *
     * @param other The other {@code InterviewScore} object to compare with.
     * @return A negative value if this score is less than the other score, zero if they are equal,
     *         and a positive value if this score is greater than the other score.
     */
    @Override
    public int compareTo(InterviewScore other) {
        return Double.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewScore)) {
            return false;
        }

        InterviewScore otherInterviewScore = (InterviewScore) other;
        return value == otherInterviewScore.value;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
