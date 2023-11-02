package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));


        // Complicated help CommandResult
        CommandResult commandResult2 = new CommandResult("feedback", true, false,
                "Usage", "Example");

        // different text, but same value -> returns true
        assertTrue(commandResult2.equals(new CommandResult("feedback", true, false)));

        // different other values -> returns false
        assertFalse(commandResult2.equals(new CommandResult("feedback", false, false)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());

        // Complicated help CommandResult
        CommandResult commandResult2 = new CommandResult("feedback", true, false, "Usage", "Example");

        // same values -> returns same hashcode
        assertEquals(commandResult2.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult2.hashCode(), new CommandResult("feedback", false, false).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void cmdUsage() {
        CommandResult commandResult = new CommandResult("feedback", true, false, "Usage 1", "Command 1");

        // Same Usage -> return true
        assertEquals(commandResult.getCmdUsage(), "Usage 1");

        // Same object -> return true
        assertEquals(commandResult.getCmdUsage(), commandResult.getCmdUsage());

        CommandResult commandResult2 = new CommandResult("feedback", true, false, "Usage 2", "Command 2");

        // Same Usage -> return true
        assertEquals(commandResult2.getCmdUsage(), "Usage 2");

        // Different command, different usage -> return false
        assertNotEquals(commandResult.getCmdUsage(), commandResult2.getCmdUsage());
    }

    @Test
    public void cmdExample() {
        CommandResult commandResult = new CommandResult("feedback", true, false, "Usage 1", "Command 1");

        // Same Usage -> return true
        assertEquals(commandResult.getCmdExample(), "Command 1");

        // Same object -> return true
        assertEquals(commandResult.getCmdExample(), commandResult.getCmdExample());

        CommandResult commandResult2 = new CommandResult("feedback", true, false, "Usage 2", "Command 2");

        // Same Usage -> return true
        assertEquals(commandResult2.getCmdExample(), "Command 2");

        // Different command, different usage -> return false
        assertNotEquals(commandResult.getCmdExample(), commandResult2.getCmdExample());
    }
}
