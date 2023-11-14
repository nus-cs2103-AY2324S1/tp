package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.SeplendidModel;

public class NoteCommandTest {

    @Test
    public void equals() {
        // Ensure that two NoteCommand objects are equal
        Command noteCommand = new NoteCommandStub();
        assertTrue(noteCommand.equals(noteCommand));

        // Ensure that different types are not equal
        assertFalse(noteCommand.equals(1));

        // Ensure that null is not equal to any NoteCommand object
        assertFalse(noteCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        // Ensure that the toString() method does not throw an exception
        Command noteCommand = new NoteCommandStub();
        assertEquals("seedu.address.logic.commands.note.NoteCommandTest.NoteCommandStub{}", noteCommand.toString());
    }

    /**
     * A concrete subclass of NoteCommand for testing.
     */
    private static class NoteCommandStub extends NoteCommand {
        @Override
        public CommandResult execute(SeplendidModel model) {
            return null;
        }
    }
}
