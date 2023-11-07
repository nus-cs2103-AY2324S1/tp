package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TrendCommand;

public class TrendCommandParserTest {
    private TrendCommandParser parser = new TrendCommandParser();

    @Test
    public void parse_validArg_returnsTrendCommand() {
        assertParseSuccess(parser, "y/2000", new TrendCommand("2000"));
        assertParseSuccess(parser, "y/2020", new TrendCommand("2020"));
        assertParseSuccess(parser, "y/2023", new TrendCommand("2023"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, TrendCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, TrendCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, TrendCommand.MESSAGE_USAGE));
    }
}
