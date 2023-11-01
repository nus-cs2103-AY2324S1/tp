package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewExitCommand;

public class ViewModeParserTest {
    private final ViewModeParser parser = new ViewModeParser();
    @Test
    public void parseCommand_viewExit() throws Exception {
        assertTrue(parser.parseCommand(ViewExitCommand.COMMAND_WORD, null, null) instanceof ViewExitCommand);
    }

    //    @Test
    //    public void parseCommand_unrecognisedInput_throwsParseException() {
    //        assertThrows(ParseException.class,
    //        String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
    //                -> parser.parseCommand("", null, null));
    //    }

    //    @Test
    //    public void parseCommand_unknownCommand_throwsParseException() {
    //        assertThrows(
    //                ParseException.class,
    //                MESSAGE_UNAVAILABLE_COMMAND_IN_VIEW_MODE, () ->
    //                        parser.parseCommand("unknownCommand", null, null)
    //        );
    //    }
}
