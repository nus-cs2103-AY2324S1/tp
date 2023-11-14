package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        String invalidNote = "@";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid note
        assertFalse(Note.isValidNote("^")); // only non-alphanumeric characters
        assertFalse(Note.isValidNote("peter*")); // contains non-alphanumeric characters

        // valid note
        assertTrue(Note.isValidNote("peter jack")); // alphabets only
        assertTrue(Note.isValidNote("12345")); // numbers only
        assertTrue(Note.isValidNote("peter the 2nd")); // alphanumeric characters
        assertTrue(Note.isValidNote("Capital Tan")); // with capital letters
        assertTrue(Note.isValidNote("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        Note note = new Note("Valid Note");

        // same values -> returns true
        assertTrue(note.equals(new Note("Valid Note")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5.0f));

        // different values -> returns false
        assertFalse(note.equals(new Note("Other Valid Note")));
    }

    @Test
    public void isToStringValid() {
        Note note = new Note("Valid Note");
        assertTrue(note.toString().equals("Valid Note"));
        assertFalse(note.toString().equals("Invalid Note"));
    }

    @Test
    public void isHashcodeValid() {
        Note note = new Note("Valid Note");
        Note sameNote = new Note("Valid Note");
        assertEquals(note.hashCode(), sameNote.hashCode());
    }
}
