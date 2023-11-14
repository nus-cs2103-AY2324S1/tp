package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NonEmptyTextTest {

    private final String validText1 = "text 1";
    private final String validText2 = "text 2";

    private final String invalidText1 = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NonEmptyText(null));
    }

    @Test
    public void isValidNonEmptyText() {
        assertThrows(NullPointerException.class, () -> NonEmptyText.isValidText(null));

        assertTrue(NonEmptyText.isValidText(validText1));

        assertFalse(NonEmptyText.isValidText(invalidText1));
    }

    @Test
    public void equals() {
        NonEmptyText text = new NonEmptyText(validText1);

        // same values -> returns true
        assertEquals(text, new NonEmptyText(validText1));

        // same object -> returns true
        assertEquals(text, text);

        // null -> returns false
        assertNotEquals(null, text);

        // different types -> returns false
        assertFalse(text.equals(5.0f));

        // different values -> returns false
        assertNotEquals(text, new NonEmptyText(validText2));
    }
}
