package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String invalidRating = "";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid rating
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating("-0.0")); // negative rating
        assertFalse(Rating.isValidRating("5.1")); // a number larger than 5.0
        assertFalse(Rating.isValidRating("1.23")); // not one decimal place
        assertFalse(Rating.isValidRating("1.a")); // not a number

        // valid rating
        assertTrue(Rating.isValidRating("0.0")); // min value
        assertTrue(Rating.isValidRating("1.4"));
        assertTrue(Rating.isValidRating("2.6"));
        assertTrue(Rating.isValidRating("3.8"));
        assertTrue(Rating.isValidRating("5.0")); // max value
    }

    @Test
    public void equals() {
        Rating rating = new Rating("5.0");

        // same values -> returns true
        assertTrue(rating.equals(new Rating("5.0")));

        // same object -> returns true
        assertTrue(rating.equals(rating));

        // null -> returns false
        assertFalse(rating.equals(null));

        // different types -> returns false
        assertFalse(rating.equals(5.0f));

        // different values -> returns false
        assertFalse(rating.equals(new Rating("3.0")));
    }

    @Test
    public void compareRating() {
        Rating rating1 = new Rating("1.2");
        Rating rating2 = new Rating("3.6");
        Rating rating3 = new Rating("2.4");
        Rating rating4 = new Rating("2.4");
        assertEquals(2, rating1.compareTo(rating2)); // less than
        assertEquals(-1, rating2.compareTo(rating3)); // more than
        assertEquals(0, rating3.compareTo(rating4)); // equals
    }

    @Test
    public void string() {
        assertEquals("1.2", new Rating("1.2").toString());
    }
}
