package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author jonyxzx
/**
 * Represents an Interview's rating in the address book.
 */
public class Rating implements Comparable<Rating> {

    public static final String MESSAGE_CONSTRAINTS =
            "Rating should only contain a non-negative number with one decimal place and between 0.0 to 5.0 inclusive";
    public static final String RATING_REGEX = "^([0-4]\\.\\d|5\\.0)$";
    public final String rating;

    /**
     * Constructs a rating.
     */
    public Rating(String rate) {
        requireNonNull(rate);
        checkArgument(isValidRating(rate), MESSAGE_CONSTRAINTS);
        rating = rate;
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(RATING_REGEX);
    }

    @Override
    public int compareTo(Rating otherRating) {
        return otherRating.rating.compareTo(this.rating);
    }

    @Override
    public String toString() {
        return rating;
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
        return rating.equals(otherRating.rating);
    }

    @Override
    public int hashCode() {
        return rating.hashCode();
    }
}
