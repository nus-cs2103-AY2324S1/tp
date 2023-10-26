package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteContentTest {

    @Test
    public void fromString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> NoteContent.fromString(null));
    }

    @Test
    public void fromString_invalidNoteContent_throwsIllegalArgumentException() {
        String invalidNoteContent = "";
        assertThrows(IllegalArgumentException.class, () -> NoteContent.fromString(invalidNoteContent));
    }

    @Test
    public void isValidNoteContent() {
        // null note content
        assertThrows(NullPointerException.class, () -> NoteContent.isValidNoteContent(null));

        // invalid note contents
        assertFalse(NoteContent.isValidNoteContent("")); // empty string
        assertFalse(NoteContent.isValidNoteContent(" ")); // spaces only

        // valid note contents
        assertTrue(NoteContent.isValidNoteContent("note content"));
        assertTrue(NoteContent.isValidNoteContent("-")); // one character
    }

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
