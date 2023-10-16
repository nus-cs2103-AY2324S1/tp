package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NoteTitleTest {
    @Test
    public void equals() {
        NoteTitle title = NoteTitle.fromString("Valid Note Title");

        // same values -> returns true
        assertTrue(title.equals(NoteTitle.fromString("Valid Note Title")));

        // same object -> returns true
        assertTrue(title.equals(title));

        // null -> returns false
        assertFalse(title.equals(null));

        // different types -> returns false
        assertFalse(title.equals(5.0f));

        // different values -> returns false
        assertFalse(title.equals(NoteTitle.fromString("Other Valid Note Title")));
    }
}
