package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's assignment in the assignment book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssignment(String)}
 */
public class Assignment {

    public static final String MESSAGE_CONSTRAINTS =
        "Assignments should only contain alphanumeric characters "
        + "and spaces, and it should not be blank";

    public static final int MAXIMUM_ALLOWED_MAX_SCORE = 1000;
    public static final String MESSAGE_INVALID_SCORE = "Assignment score should be between 0 and the maximum score.";
    public static final String MESSAGE_INVALID_MAX_SCORE =
        "Assignment score should be between 1 and " + MAXIMUM_ALLOWED_MAX_SCORE + ".";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final Name name;
    private int score;
    private int maxScore;

    /**
     * Constructs an assignment with the given name and maximum score.
     *
     * @param name The name of the assignment. Must not be null and must satisfy the constraints.
     * @param maxScore The maximum possible score for the assignment.
     * @throws NullPointerException If the provided name is null.
     * @throws IllegalArgumentException If the provided name does not satisfy the constraints.
     */
    public Assignment(Name name, int maxScore) {
        requireNonNull(name);
        checkArgument(isValidAssignment(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.score = 0; // default score will be zero
        this.maxScore = maxScore;
    }

    public Assignment(Name name) {
        this(name, 100);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAssignment(Name test) {
        return test.toString().matches(VALIDATION_REGEX);
    }

    /**
     * Checks if a score is greater than equal to 0 and less than equal to maximum score.
     *
     * @param testScore A integer representing the score to be given.
     * @param maxScore A integer representing the maximum score for the specific assignment.
     * @return `true` if score is valid; otherwise, `false`.
     */
    public static boolean isValidScore(int testScore, int maxScore) {
        if (testScore > maxScore || testScore < 0) {
            return false;
        }
        return true;
    }

    public int getScore() {
        return score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setScore(int score) {
        checkArgument(isValidScore(score, this.maxScore), MESSAGE_INVALID_SCORE);
        this.score = score;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return String.format("%s: %d/%d", name, score, maxScore);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return name.equals(otherAssignment.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
