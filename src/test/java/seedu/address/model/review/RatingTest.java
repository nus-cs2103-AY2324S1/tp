package seedu.address.model.review;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RatingTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "6";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void equals() {
        Rating rating = new Rating("5");
        Rating ratingCopy = new Rating("5");
        Rating rating2 = new Rating("1");

        // same object -> returns true
        assertTrue(rating.equals(rating));

        // same values -> returns true
        assertTrue(rating.equals(ratingCopy));

        // different types -> returns false
        assertFalse(rating.equals(1));

        // null -> returns false
        assertFalse(rating.equals(null));

        // different rating -> returns false
        assertFalse(rating.equals(rating2));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid rating
        // ep and bva
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating(" ")); // spaces only
        assertFalse(Rating.isValidRating("abc")); // not a number
        assertFalse(Rating.isValidRating("6")); // greater than 5
        assertFalse(Rating.isValidRating("0")); // less than 1

        // valid rating
        assertTrue(Rating.isValidRating("1")); // exactly 1
        assertTrue(Rating.isValidRating("5")); // exactly 5
    }

    @Test
    public void starRating() {
        Rating rating = new Rating("5");
        assertTrue(rating.starRating.equals("★★★★★"));
        rating = new Rating("1");
        assertTrue(rating.starRating.equals("★☆☆☆☆"));
    }
}
