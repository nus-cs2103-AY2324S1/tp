package seedu.address.logic.commands.commandresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void testCommandResultConstructor() {
        CommandResult commandResult = new RegularCommandResult("Feedback");
        assertEquals("Feedback", commandResult.getFeedbackToUser());
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isExit());
    }

    @Test
    public void testCommandResultConstructorShowHelpAndExit() {
        CommandResult commandResult = new RegularCommandResult("Feedback", true, true);
        assertEquals("Feedback", commandResult.getFeedbackToUser());
        assertTrue(commandResult.isShowHelp());
        assertTrue(commandResult.isExit());
    }

    @Test
    public void testCommandResultShowHelp() {
        CommandResult commandResult = new RegularCommandResult("Feedback", true, false);
        assertTrue(commandResult.isShowHelp());
        assertFalse(commandResult.isExit());
    }

    @Test
    public void testCommandResultExit() {
        CommandResult commandResult = new RegularCommandResult("Feedback", false, true);
        assertTrue(commandResult.isExit());
        assertFalse(commandResult.isShowHelp());
    }
}
