package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class HintTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Hint(null));
    }

    @Test
    public void constructor_invalidHint_throwsIllegalArgumentException() {
        String invalidHint = "";
        assertThrows(IllegalArgumentException.class, () -> new Hint(invalidHint));
    }

    @Test
    public void equals() {
        Hint hint = new Hint("Valid Hint");

        // same values -> returns true
        assertTrue(hint.equals(new Hint("Valid Hint")));

        // same object -> returns true
        assertTrue(hint.equals(hint));

        // null -> returns false
        assertFalse(hint.equals(null));

        // different types -> returns false
        assertFalse(hint.equals("clown"));

        // different values -> returns false
        assertFalse(hint.equals(new Hint("Other Valid Hint")));
    }

    @Test
    public void hashcode() {
        Hint hint = new Hint("Valid Hint");

        // same values -> returns true
        assertEquals(hint.hashCode(), new Hint("Valid Hint").hashCode());

        // same object -> returns true
        assertEquals(hint.hashCode(), hint.hashCode());

        // different values -> returns false
        assertNotEquals(hint.hashCode(), new Hint("Other Valid Hint").hashCode());
    }

    @Test
    public void equal_empty() {
        Hint.EmptyHint emptyHint = new Hint.EmptyHint();
        Hint.EmptyHint emptyHint2 = new Hint.EmptyHint();
        assertTrue(emptyHint.equals(new Hint.EmptyHint()));

        // same object -> returns true
        assertTrue(emptyHint.equals(emptyHint));

        assertTrue(emptyHint.equals(emptyHint2));

    }
}
