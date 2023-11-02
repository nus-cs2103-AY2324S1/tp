package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
// import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

// import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    /*@Test
    public void parse_validArgs_returnsHelpCommand() {
        // no leading and trailing whitespaces
        HelpCommand expectedHelpCommand =
                new HelpCommand(ExitCommand.MESSAGE_USAGE, ExitCommand.MESSAGE_EXAMPLE);
        assertParseSuccess(parser, "exit", expectedHelpCommand);

        // multiple whitespaces between keywords
        //assertParseSuccess(parser, "exit ", expectedHelpCommand);
    }*/

}
