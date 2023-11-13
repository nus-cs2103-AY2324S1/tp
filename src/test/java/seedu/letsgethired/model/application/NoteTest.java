package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void isValidNote() {
        // null status
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // empty notes
        assertFalse(Note.isValidNote("")); // empty string
        assertFalse(Note.isValidNote(" ")); // spaces only

        // valid statuses
        assertTrue(Note.isValidNote("Jane Street is the leading market maker in the APAC region"));
    }

    @Test
    public void equals() {
        Note note = new Note("Jane Street is the leading market maker in the APAC region");

        // same object -> returns true
        assertTrue(note.equals(note));

        // same values -> returns true
        Note noteCopy = new Note(note.value);
        assertTrue(note.equals(noteCopy));

        // different types -> returns false
        assertFalse(note.equals(1));

        // null -> returns false
        assertFalse(note.equals(null));

        // different note -> returns false
        Note differentNote = new Note("Bytedance requires back end developers to know the MERN stack");
        assertFalse(note.equals(differentNote));
    }
}
