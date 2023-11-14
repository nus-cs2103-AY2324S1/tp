package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        // empty string
        assertThrows(IllegalArgumentException.class, () -> new Note(""));
        // spaces only
        assertThrows(IllegalArgumentException.class, () -> new Note(" "));
        // starts with a whitespace
        assertThrows(IllegalArgumentException.class, () -> new Note(" do work"));
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid notes
        assertFalse(Note.isValidNote("")); // empty string
        assertFalse(Note.isValidNote(" ")); // spaces only
        assertFalse(Note.isValidNote(" do work")); // starts with a whitespace

        // valid notes
        assertTrue(Note.isValidNote("^")); // only non-alphanumeric characters
        assertTrue(Note.isValidNote("SELECT*")); // contains non-alphanumeric characters
        assertTrue(Note.isValidNote("do work")); // alphabets only
        assertTrue(Note.isValidNote("12345")); // numbers only
        assertTrue(Note.isValidNote("submit by the 2nd")); // alphanumeric characters
        assertTrue(Note.isValidNote("CS2103")); // with capital letters
        assertTrue(Note.isValidNote("CoordiMate for Event Planners of CS2103")); // long notes
        assertTrue(Title.isValidTitle("\uD83D\uDC4B\uD83C\uDFFB \uD83C\uDDF8\uD83C\uDDEC")); // emojis
        assertTrue(Title.isValidTitle("(ノಠ益ಠ)ノ彡┻━┻")); // non-English characters
    }

    @Test
    public void equals() {
        Note note = new Note("Valid Note");

        // same values -> returns true
        assertEquals(note, new Note("Valid Note"));

        // same object -> returns true
        assertEquals(note, note);

        // null -> returns false
        // assertNotEquals calls the 1st argument's equals method
        assertNotEquals(note, null);

        // different types -> returns false
        assertNotEquals(note, 5.0f);

        // different values -> returns false
        assertNotEquals(note, new Note("Other Valid Note"));
    }

    @Test
    public void toStringMethod() {
        Note note = new Note("Valid Note");
        assertEquals("Valid Note", note.toString());
    }

    @Test
    public void hashCodeTest() {
        Note note = new Note("Valid Note");

        // same note -> same hashcode
        assertEquals(note.hashCode(), new Note("Valid Note").hashCode());

        // different note -> different hashcode
        assertNotEquals(note.hashCode(), new Note("Other Valid Note").hashCode());

        // different types -> returns false
        assertNotEquals(note.hashCode(), "Test Note".hashCode());
    }
}
