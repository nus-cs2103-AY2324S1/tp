package networkbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", true);

        // same values -> equal
        assertEquals(commandResult, new CommandResult("feedback", true));
        assertEquals(commandResult, new CommandResult("feedback", false, false, true));

        // same object -> equal
        assertEquals(commandResult, commandResult);

        // null -> not equal
        assertNotEquals(commandResult, null);

        // different types -> not equal
        assertNotEquals(commandResult, 0.5f);

        // different feedbackToUser value -> not equal
        assertNotEquals(commandResult, new CommandResult("different", true));

        // different showHelp value -> not equal
        assertNotEquals(commandResult, new CommandResult("feedback", true, false, true));

        // different exit value -> not equal
        assertNotEquals(commandResult, new CommandResult("feedback", false, true, true));

        // different changeData value -> not equal
        assertNotEquals(commandResult, new CommandResult("feedback", false));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", false);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", false).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false).hashCode());

        // different changeData value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback", true);
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + ", changeData=" + commandResult.isChangeData() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
