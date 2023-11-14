package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StarTest {

    @Test
    void testToString() {
        Star star = new Star("true");

        assertTrue(star.equals(new Star("true")));

        // Same object -> returns true
        assertTrue(star.equals(star));

        // different types -> returns false
        assertFalse(star.equals(new Star("false")));
        assertFalse(star.equals(1));
        assertFalse(star.equals(1.0f));

        // null -> returns false
        assertFalse(star.equals(null));
    }

    @Test
    void testEquals() {
        Star star = new Star("true");

        // same object -> returns true
        assertTrue(star.equals(star));

        assertFalse(star.equals(new Star("false")));

        // different types -> returns false
        assertFalse(star.equals(1));
        assertFalse(star.equals(1.0f));
        assertFalse(star.equals("abc"));

        // null -> returns false
        assertFalse(star.equals(null));
    }

    @Test
    void testHashCode() {
        Star star = new Star("true");

        assertTrue(star.hashCode() == star.hashCode());

        assertFalse(star.hashCode() == new Star("false").hashCode());
    }
}
