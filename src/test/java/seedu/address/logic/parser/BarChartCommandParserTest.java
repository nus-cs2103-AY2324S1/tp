package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BarChartCommand;
public class BarChartCommandParserTest {
    private BarChartCommandParser parser = new BarChartCommandParser();

    @Test
    public void parse_validArg_returnsBarChartCommand() {
        assertParseSuccess(parser, "s/", new BarChartCommand("s/"));
        assertParseSuccess(parser, "l/", new BarChartCommand("l/"));
        assertParseSuccess(parser, "g/", new BarChartCommand("g/"));
        assertParseSuccess(parser, "d/2023", new BarChartCommand("d/", 2023));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, BarChartCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "d/xxxx", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, BarChartCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, BarChartCommand.MESSAGE_USAGE));
    }
}
