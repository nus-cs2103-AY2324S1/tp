package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteIdTest {

    @Test
    public void fromString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> NoteID.fromString(null));
    }

    @Test
    public void equals() {
        NoteID id = NoteID.fromString("1");

        // same values -> returns true
        assertTrue(id.equals(NoteID.fromString("1")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // same values but using fromInt method -> returns true
        assertTrue(id.equals(NoteID.fromInt(1)));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(NoteID.fromString("2")));
    }
}
