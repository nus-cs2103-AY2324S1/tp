package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ViewExitCommand;

public class ViewModeParserTest {
    private final ViewModeParser parser = new ViewModeParser();
    @Test
    public void parseCommand_viewExit() throws Exception {
        assertTrue(parser.parseCommand(ViewExitCommand.COMMAND_WORD) instanceof ViewExitCommand);
        assertTrue(parser.parseCommand(ViewExitCommand.COMMAND_WORD + " 3") instanceof ViewExitCommand);
    }
}
