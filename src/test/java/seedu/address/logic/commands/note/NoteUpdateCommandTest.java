package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.Content;

public class NoteUpdateCommandTest {
    @Test
    public void constructor_nullNoteIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteUpdateCommand(null, new Content("test")));
    }

    @Test
    public void constructor_nullNoteContent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteUpdateCommand(1, null));
    }

    @Test
    public void equals() {
        NoteUpdateCommand updateCommand1 = new NoteUpdateCommand(1, new Content("test"));
        NoteUpdateCommand updateCommand2 = new NoteUpdateCommand(1, new Content("test2"));
        NoteUpdateCommand updateCommand3 = new NoteUpdateCommand(2, new Content("test"));
        NoteUpdateCommand updateCommand4 = new NoteUpdateCommand(1, new Content("test"));

        // same object -> returns true
        assertTrue(updateCommand1.equals(updateCommand1));

        // different types -> returns false
        assertFalse(updateCommand1.equals(1));

        // null -> returns false
        assertFalse(updateCommand1.equals(null));

        // different tag model same index, different tags -> returns false
        assertFalse(updateCommand1.equals(updateCommand2));

        // different tag model different index -> returns true
        assertFalse(updateCommand1.equals(updateCommand3));

        // different tag model same index, same tags -> returns false
        assertTrue(updateCommand1.equals(updateCommand4));
    }
}
