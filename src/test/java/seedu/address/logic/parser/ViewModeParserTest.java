package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNAVAILABLE_COMMAND_IN_VIEW_MODE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewExitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewModeParserTest {
    private final ViewModeParser parser = new ViewModeParser();
    @Test
    public void parseCommand_viewExit() throws Exception {
        assertTrue(parser.parseCommand(ViewExitCommand.COMMAND_WORD) instanceof ViewExitCommand);
        assertTrue(parser.parseCommand(ViewExitCommand.COMMAND_WORD + " 3") instanceof ViewExitCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
                ParseException.class,
                MESSAGE_UNAVAILABLE_COMMAND_IN_VIEW_MODE, () ->
                        parser.parseCommand("unknownCommand")
        );
    }
}
