package seedu.address.model.review;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Review's rating in the review list.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating implements Comparable<Rating> {
    public static final String MESSAGE_CONSTRAINTS =
            "Rating should be an integer from 1 to 5, inclusive";

    public static final String VALIDATION_REGEX = "[1-5]";

    public final String starRating;
    public final String rating;

    /**
     * Constructs a {@code Rating}.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        this.rating = rating;
        this.starRating = starRating(Integer.parseInt(rating));
    }

    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private String starRating(int rating) {
        assert rating >= 1 && rating <= 5;
        StringBuilder starRating = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                starRating.append("★");
            } else {
                starRating.append("☆");
            }
        }
        return starRating.toString();
    }

    @Override
    public int compareTo(Rating other) {
        if (other == null) {
            // null is considered to be the largest value
            return -1;
        }
        return Integer.compare(Integer.parseInt(other.rating), Integer.parseInt(rating));
    }

    @Override
    public String toString() {
        return rating;
    }

    @Override
    public int hashCode() {
        return rating.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && rating.equals(((Rating) other).rating)); // state check
    }

}
