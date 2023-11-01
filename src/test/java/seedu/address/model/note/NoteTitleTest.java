package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTitleTest {

    @Test
    public void fromString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> NoteTitle.fromString(null));
    }

    @Test
    public void fromString_invalidNoteTitle_throwsIllegalArgumentException() {
        String invalidNoteTitle = "";
        assertThrows(IllegalArgumentException.class, () -> NoteTitle.fromString(invalidNoteTitle));
    }

    @Test
    public void isValidNoteTitle() {
        // null note title
        assertThrows(NullPointerException.class, () -> NoteTitle.isValidNoteTitle(null));

        // invalid note title
        assertFalse(NoteTitle.isValidNoteTitle("")); // empty string
        assertFalse(NoteTitle.isValidNoteTitle(" ")); // spaces only

        // valid note title
        assertTrue(NoteTitle.isValidNoteTitle("note title"));
        assertTrue(NoteTitle.isValidNoteTitle("-")); // one character
    }

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
