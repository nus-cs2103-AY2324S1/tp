package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListSellerCommand;

public class ListSellerCommandParserTest {
    private final ListSellerCommandParser parser = new ListSellerCommandParser();

    @Test
    public void parse_zeroInput_exceptionThrown() {
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeInput_exceptionThrown() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonZeroUnsignedInteger_success() {
        assertParseSuccess(parser, "2", new ListSellerCommand(Index.fromZeroBased(1)));

        assertParseSuccess(parser, "11", new ListSellerCommand(Index.fromZeroBased(10)));

        assertParseSuccess(parser, "101", new ListSellerCommand(Index.fromZeroBased(100)));
    }

}
