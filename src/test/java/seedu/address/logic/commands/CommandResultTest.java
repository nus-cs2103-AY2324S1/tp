package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", TabIndex.Developer);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", TabIndex.Developer)));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, TabIndex.Developer)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", TabIndex.Developer)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, TabIndex.Developer)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, TabIndex.Developer)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", TabIndex.Developer);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", TabIndex.Developer)
                .hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("different", TabIndex.Developer)
                        .hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false,
                TabIndex.Developer).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true,
                TabIndex.Developer).hashCode());
    }
    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback", TabIndex.Developer);
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + ", index=Developer}";
        assertEquals(expected, commandResult.toString());
    }
}
