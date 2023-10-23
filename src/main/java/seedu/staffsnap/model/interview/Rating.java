package seedu.staffsnap.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.AppUtil.checkArgument;

/**
 * Represents an Interview's rating in the applicant book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating {

    public static final String MESSAGE_CONSTRAINTS =
            "Rating should be a number between 0.0 and 10.0 to 1 decimal place";
    public static final String VALIDATION_REGEX = "^(0|10|\\d{1}(\\.\\d{1})?)$";

    public final String value;

    /**
     * Constructs a {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.equals("-") || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Rating)) {
            return false;
        }

        Rating otherRating = (Rating) other;
        return value.equals(otherRating.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
