package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteDeleteCommandTest {

    @Test
    public void constructor_nullNoteIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteDeleteCommand(null));
    }

    @Test
    public void equals() {
        int noteIndex1 = 1;
        int noteIndex2 = 2;
        NoteDeleteCommand deleteCommand1 = new NoteDeleteCommand(noteIndex1);
        NoteDeleteCommand deleteCommand1Copy = new NoteDeleteCommand(noteIndex1);
        NoteDeleteCommand deleteCommand2 = new NoteDeleteCommand(noteIndex2);

        // Check equality with itself
        assertTrue(deleteCommand1.equals(deleteCommand1));

        // Check equality with a copy
        assertTrue(deleteCommand1.equals(deleteCommand1Copy));

        // Check equality with a different command
        assertFalse(deleteCommand1.equals(deleteCommand2));

        // Check equality with null
        assertFalse(deleteCommand1.equals(null));

        // Check equality with a different type of object
        assertFalse(deleteCommand1.equals(1));
    }
}
