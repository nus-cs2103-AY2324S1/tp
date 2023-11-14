package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SeplendidModelStub;
import seedu.address.messages.Messages;
import seedu.address.model.note.Note;
import seedu.address.testutil.NoteBuilder;

public class NoteAddCommandTest {

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteAddCommand(null));
    }

    @Test
    public void execute_noteAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingNoteAdded modelStub = new ModelStubAcceptingNoteAdded();
        Note validNote = new NoteBuilder().withContent("Note 1").build();

        CommandResult commandResult = new NoteAddCommand(validNote).execute(modelStub);

        assertEquals(
                String.format(NoteAddCommand.MESSAGE_SUCCESS, Messages.format(validNote)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.hasNote(validNote));
    }

    @Test
    public void equals() {
        Note note1 = new NoteBuilder().withContent("Note 1").withIndex(1).build();
        Note note2 = new NoteBuilder().withContent("Note 2").withIndex(2).build();
        NoteAddCommand addNote1Command = new NoteAddCommand(note1);
        NoteAddCommand addNote2Command = new NoteAddCommand(note2);

        // same object -> returns true
        assertTrue(addNote1Command.equals(addNote1Command));

        // same values -> returns true
        NoteAddCommand addNote1CommandCopy = new NoteAddCommand(note1);
        assertTrue(addNote1Command.equals(addNote1CommandCopy));

        // different types -> returns false
        assertFalse(addNote1Command.equals(1));

        // null -> returns false
        assertFalse(addNote1Command.equals(null));

        // different note -> returns false
        assertFalse(addNote1Command.equals(addNote2Command));
    }

    /**
     * A Model stub that always accepts the note being added.
     */
    private class ModelStubAcceptingNoteAdded extends SeplendidModelStub {
        private Note note;

        @Override
        public boolean hasNote(Note note) {
            return this.note != null && this.note.equals(note);
        }

        @Override
        public void addNote(Note note) {
            this.note = note;
        }
    }
}
