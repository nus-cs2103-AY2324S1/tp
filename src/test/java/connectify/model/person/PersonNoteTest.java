package connectify.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonNoteTest {
    @Test
    public void equals() {
        PersonNote note = new PersonNote("Test");
        // same object -> returns true
        assertTrue(note.equals(note));
        // null -> returns false
        assertFalse(note.equals(null));
        // different type -> returns false
        assertFalse(note.equals(5));
        // different note -> returns false
        assertFalse(note.equals(new PersonNote("Test2")));
        // same content -> returns true
        assertTrue(note.equals(new PersonNote("Test")));
    }
}
