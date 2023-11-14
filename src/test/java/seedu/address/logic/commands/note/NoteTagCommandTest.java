package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class NoteTagCommandTest {
    @Test
    public void constructor_nullNoteIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteTagCommand(null,
                new Tag("test")));
    }

    @Test
    public void constructor_nullNoteTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteTagCommand(1, null));
    }

    @Test
    public void equals() {
        NoteTagCommand tagCommand1 = new NoteTagCommand(1, new Tag("test"));
        NoteTagCommand tagCommand2 = new NoteTagCommand(1, new Tag("test2"));
        NoteTagCommand tagCommand3 = new NoteTagCommand(2, new Tag("test"));
        NoteTagCommand tagCommand4 = new NoteTagCommand(1, new Tag("test"));

        // same object -> returns true
        assertTrue(tagCommand1.equals(tagCommand1));

        // different types -> returns false
        assertFalse(tagCommand1.equals(1));

        // null -> returns false
        assertFalse(tagCommand1.equals(null));

        // different tag model same index, different tags -> returns false
        assertFalse(tagCommand1.equals(tagCommand2));

        // different tag model different index -> returns true
        assertFalse(tagCommand1.equals(tagCommand3));

        // different tag model same index, same tags -> returns false
        assertTrue(tagCommand1.equals(tagCommand4));
    }
}
