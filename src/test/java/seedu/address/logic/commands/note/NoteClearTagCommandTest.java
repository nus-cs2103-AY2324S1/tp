package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteClearTagCommandTest {
    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteClearTagCommand(null));
    }

    @Test
    public void equals() {
        NoteClearTagCommand clearTagCommand1 = new NoteClearTagCommand(1);
        NoteClearTagCommand clearTagCommand2 = new NoteClearTagCommand(1);
        NoteClearTagCommand clearTagCommand3 = new NoteClearTagCommand(2);

        // same object -> returns true
        assertTrue(clearTagCommand1.equals(clearTagCommand1));

        // different types -> returns false
        assertFalse(clearTagCommand1.equals(1));

        // null -> returns false
        assertFalse(clearTagCommand1.equals(null));

        // different cleartag model same index -> returns true
        assertTrue(clearTagCommand1.equals(clearTagCommand2));

        // different cleartag model different index -> returns true
        assertFalse(clearTagCommand1.equals(clearTagCommand3));
    }
}
