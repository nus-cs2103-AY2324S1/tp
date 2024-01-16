package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's score in JABPro.
 */
public class Score implements Comparable<Score> {
    public static final String MESSAGE_CONSTRAINTS = "Score should be a non-negative integer";
    public final int value;


    /**
     * Constructs a {@code Score}.
     * @param value
     */
    public Score(int value) {
        requireNonNull(value);
        checkArgument(isValidScoreValue(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns if a given integer is a valid score.
     * @param test integer to be tested
     * @return true if test is a non-negative integer
     */
    public static boolean isValidScoreValue(int test) {
        return test >= 0;
    }

    public static boolean isValidScore(Score score) {
        return isValidScoreValue(score.value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Score)) {
            return false;
        }

        Score otherScore = (Score) other;
        return value == otherScore.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.value, other.value);
    }
}

