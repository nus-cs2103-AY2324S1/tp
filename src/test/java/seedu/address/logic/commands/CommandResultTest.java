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
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, "feedback")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, "help")));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, "exit")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, "help").hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, "exit").hashCode());
    }
    @Test
    public void stateTest() {
        CommandResult commandResult = new CommandResult("feedback", true, false, "view");
        assertEquals("view", commandResult.checkState());
    }

    @Test
    public void notEqualsTest() {
        CommandResult commandResult1 = new CommandResult("feedback", true, true, "view");
        CommandResult commandResult2 = new CommandResult("differentFeedback", true, true, "help");
        assertNotEquals(commandResult1, commandResult2);
    }
    @Test
    public void defaultConstructorTest() {
        CommandResult commandResult = new CommandResult("feedback");
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isExit());
        assertEquals("null", commandResult.checkState());
    }
    @Test
    public void hashCodeConsistencyTest() {
        CommandResult commandResult = new CommandResult("feedback", true, false, "state");
        assertEquals(commandResult.hashCode(), commandResult.hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
