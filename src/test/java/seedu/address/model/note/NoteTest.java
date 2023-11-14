package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_B_CONTENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_B_TITLE;
import static seedu.address.testutil.TypicalNotes.NOTE_A;
import static seedu.address.testutil.TypicalNotes.NOTE_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.NoteBuilder;

public class NoteTest {
    @Test
    public void equals() {
        // same values -> returns true
        Note noteACopy = new NoteBuilder(NOTE_A).build();
        assertTrue(NOTE_A.equals(noteACopy));

        // same object -> returns true
        assertTrue(NOTE_A.equals(NOTE_A));

        // null -> returns false
        assertFalse(NOTE_A.equals(null));

        // different type -> returns false
        assertFalse(NOTE_A.equals(5));

        // different note -> returns false
        assertFalse(NOTE_A.equals(NOTE_B));

        // different title -> returns false
        Note editedNoteA = new NoteBuilder(NOTE_A).withTitle(VALID_NOTE_B_TITLE).build();
        assertFalse(NOTE_A.equals(editedNoteA));

        // different content -> returns false
        editedNoteA = new NoteBuilder(NOTE_A).withContent(VALID_NOTE_B_CONTENT).build();
        assertFalse(NOTE_A.equals(editedNoteA));
    }
}
