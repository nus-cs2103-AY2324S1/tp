package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TableCommand;

public class TableCommandParserTest {
    private TableCommandParser parser = new TableCommandParser();

    @Test
    public void parse_validArg_returnsTableCommand() {
        assertParseSuccess(parser, "s/", new TableCommand("s/"));
        assertParseSuccess(parser, "l/", new TableCommand("l/"));
        assertParseSuccess(parser, "g/", new TableCommand("g/"));
        assertParseSuccess(parser, "d/2023", new TableCommand("d/", 2023));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, TableCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "d/xxxx", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, TableCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, TableCommand.MESSAGE_USAGE));
    }
}
