package connectify.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyNoteTest {
    @Test
    public void equals() {
        CompanyNote note = new CompanyNote("Test");
        // same object -> returns true
        assertTrue(note.equals(note));
        // null -> returns false
        assertFalse(note.equals(null));
        // different type -> returns false
        assertFalse(note.equals(5));
        // different note -> returns false
        assertFalse(note.equals(new CompanyNote("Test2")));
        // same content -> returns true
        assertTrue(note.equals(new CompanyNote("Test")));
    }
}
