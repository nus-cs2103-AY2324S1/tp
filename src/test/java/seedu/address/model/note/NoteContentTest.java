package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NoteContentTest {
    @Test
    public void equals() {
        NoteContent content = NoteContent.fromString("Valid Note Content");

        // same values -> returns true
        assertTrue(content.equals(NoteContent.fromString("Valid Note Content")));

        // same object -> returns true
        assertTrue(content.equals(content));

        // null -> returns false
        assertFalse(content.equals(null));

        // different types -> returns false
        assertFalse(content.equals(5.0f));

        // different values -> returns false
        assertFalse(content.equals(NoteContent.fromString("Other Valid Note Content")));
    }
}
