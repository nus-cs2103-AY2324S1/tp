package seedu.ccacommander.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CommandHistoryTest {
    private CommandHistory commandHistory;

    public static final String FIRST_COMMAND = "FIRST COMMAND";
    public static final String SECOND_COMMAND = "SECOND COMMAND";

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void addCommand_nullCommand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandHistory.addCommand(null));
    }

    @Test
    public void hasNextCommand_noCommandsAdded_returnsFalse() {
        assertFalse(commandHistory.hasNextCommand());
    }

    @Test
    public void hasNextCommand_hasCommandsAdded_returnsTrue() {
        commandHistory.addCommand(FIRST_COMMAND);
        commandHistory.addCommand(SECOND_COMMAND);
        commandHistory.getPreviousCommand();
        commandHistory.getPreviousCommand();
        assertTrue(commandHistory.hasNextCommand());
    }

    @Test
    public void hasPreviousCommand_noCommandsAdded_returnsFalse() {
        assertFalse(commandHistory.hasPreviousCommand());
    }

    @Test
    public void hasPreviousCommand_hasCommandsAdded_returnsTrue() {
        commandHistory.addCommand(FIRST_COMMAND);
        assertTrue(commandHistory.hasPreviousCommand());
    }

    @Test
    public void getNextCommand_hasNextCommand_returnsNextCommand() {
        commandHistory.addCommand(FIRST_COMMAND);
        commandHistory.addCommand(SECOND_COMMAND);

        commandHistory.getPreviousCommand();
        commandHistory.getPreviousCommand();
        String nextCommand = commandHistory.getNextCommand();
        assertEquals(SECOND_COMMAND, nextCommand);
    }

    @Test
    public void getNextCommand_noNextCommand_returnsNull() {
        assertNull(commandHistory.getNextCommand());
    }

    @Test
    public void getPreviousCommand_hasPreviousCommand_returnsPreviousCommand() {
        commandHistory.addCommand(FIRST_COMMAND);
        String previousCommand = commandHistory.getPreviousCommand();
        assertEquals(FIRST_COMMAND, previousCommand);
    }

    @Test
    public void getPreviousCommand_noPreviousCommand_returnsNull() {
        assertNull(commandHistory.getPreviousCommand());
    }
}
