package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MatchPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MatchPropertyCommandParserTest {
    private MatchPropertyCommandParser parser = new MatchPropertyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MatchPropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsMatchCustomerCommand() throws ParseException {
        String indexString = "1";
        Index index;
        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MatchPropertyCommand.MESSAGE_USAGE),
                    pe
            );
        }
        String userInput = indexString;

        MatchPropertyCommand expectedMatchPropertyCommand =
                new MatchPropertyCommand(index);
        assertParseSuccess(parser, userInput, expectedMatchPropertyCommand);
    }
}
