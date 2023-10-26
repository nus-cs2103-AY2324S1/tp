package seedu.staffsnap.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;
import static seedu.staffsnap.testutil.TypicalApplicants.CARL;
import static seedu.staffsnap.testutil.TypicalApplicants.DANIEL;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "a";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // negative rating
        assertFalse(Rating.isValidRating("-1"));

        // rating out of range
        assertFalse(Rating.isValidRating("15"));

        // non numeric rating
        assertFalse(Rating.isValidRating("a"));
    }

    @Test
    public void equals() {
        // same rating -> returns true
        Rating sameRating = new Rating(DANIEL.getInterviews().get(0).getRating().value);
        assertTrue(BENSON.getInterviews().get(0).getRating().equals(sameRating));

        // same object -> returns true
        assertTrue(sameRating.equals(sameRating));

        // null -> returns false
        assertFalse(sameRating.equals(null));

        // different rating -> returns false
        Rating differentRating = CARL.getInterviews().get(0).getRating();
        assertFalse(sameRating.equals(differentRating));
    }

    @Test
    public void toStringMethod() {
        String expected = BENSON.getInterviews().get(0).getRating().value;
        assertEquals(expected, BENSON.getInterviews().get(0).getRating().toString());
    }

    @Test
    public void testHashCode() {
        // same object -> equal hashcode
        String rating = BENSON.getInterviews().get(0).getRating().value;
        assertEquals(rating.hashCode(), rating.hashCode());

        // same rating -> equal hashcode
        String sameRating = DANIEL.getInterviews().get(0).getRating().value;
        assertEquals(rating.hashCode(), sameRating.hashCode());
    }
}
