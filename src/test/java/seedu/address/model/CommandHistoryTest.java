package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    public static final String COMMAND_1 = "dashboard";
    public static final String COMMAND_2 = "list";
    public static final String COMMAND_3 = "this is a random command";

    private CommandHistory commandHistory;
    private CommandHistory commandHistoryWithMaxCommands;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
        commandHistoryWithMaxCommands = new CommandHistory(2);
    }

    @Test
    public void constructor() {
        assertEquals("", commandHistory.getPreviousCommand());
        assertEquals("", commandHistory.getNextCommand());
    }

    @Test
    public void addSingleCommand_commandIsAdded() {
        commandHistory.addCommand(COMMAND_1);
        assertEquals(COMMAND_1, commandHistory.getPreviousCommand());
        assertEquals("", commandHistory.getNextCommand());
    }

    @Test
    public void addMultipleCommands_commandsAreAddedInOrder() {
        commandHistory.addCommand(COMMAND_1);
        commandHistory.addCommand(COMMAND_2);
        assertEquals(COMMAND_2, commandHistory.getPreviousCommand());
        assertEquals(COMMAND_1, commandHistory.getPreviousCommand());
    }

    @Test
    public void addDuplicateSuccessiveCommands_onlyOneCommandIsAdded() {
        commandHistory.addCommand(COMMAND_1);
        commandHistory.addCommand(COMMAND_1); // Duplicate
        assertEquals(COMMAND_1, commandHistory.getPreviousCommand());
        assertEquals("", commandHistory.getNextCommand());
    }

    @Test
    public void addEmptyCommand_emptyCommandIsNotAdded() {
        commandHistory.addCommand("");
        assertEquals("", commandHistory.getPreviousCommand());
        assertEquals("", commandHistory.getNextCommand());
    }

    @Test
    public void addCommandsExceedingMaxHistorySize_oldestCommandIsRemoved() {
        commandHistoryWithMaxCommands.addCommand(COMMAND_1);
        commandHistoryWithMaxCommands.addCommand(COMMAND_2);
        commandHistoryWithMaxCommands.addCommand(COMMAND_3); // Should remove COMMAND_1
        assertEquals(COMMAND_3, commandHistoryWithMaxCommands.getPreviousCommand());
        assertEquals(COMMAND_2, commandHistoryWithMaxCommands.getPreviousCommand());
        assertEquals(COMMAND_2, commandHistoryWithMaxCommands.getPreviousCommand());
    }

    @Test
    public void addCommandToCustomMaxSizeHistory_commandIsAdded() {
        commandHistoryWithMaxCommands.addCommand(COMMAND_1);
        assertEquals(COMMAND_1, commandHistoryWithMaxCommands.getPreviousCommand());
    }

    @Test
    public void resetCurrentCommandIndex_indexIsReset() {
        commandHistory.addCommand(COMMAND_1);
        commandHistory.addCommand(COMMAND_2);
        commandHistory.getPreviousCommand(); // Move index back
        commandHistory.resetCurrentCommandIndex();
        assertEquals("", commandHistory.getNextCommand());
    }
}
