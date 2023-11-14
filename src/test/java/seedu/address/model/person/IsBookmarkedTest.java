package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsBookmarkedTest {

    @Test
    public void equals() {
        IsBookmarked isBookmarked = new IsBookmarked(true);

        // same values -> returns true
        assertTrue(isBookmarked.equals(new IsBookmarked(true)));

        // same object -> returns true
        assertTrue(isBookmarked.equals(isBookmarked));

        // null -> returns false
        assertFalse(isBookmarked.equals(null));

        // different types -> returns false
        assertFalse(isBookmarked.equals("Test"));

        // different values -> returns false
        assertFalse(isBookmarked.equals(new IsBookmarked(false)));
    }
}
