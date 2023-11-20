package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ConvertLeadToClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ConvertLeadToClientCommandParserTest {

    private ConvertLeadToClientCommandParser parser = new ConvertLeadToClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertLeadToClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsConvertClientToLeadCommand() throws ParseException {
        // no leading and trailing whitespaces
        ConvertLeadToClientCommand expectedConvertLeadToClientCommand =
                new ConvertLeadToClientCommand(ParserUtil.parseIndex("1"));
        assertParseSuccess(parser, "1", expectedConvertLeadToClientCommand);
    }

}
