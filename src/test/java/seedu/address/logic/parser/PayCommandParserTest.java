package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PayCommand;
import seedu.address.model.person.Balance;

class PayCommandParserTest {

    private PayCommandParser parser = new PayCommandParser();

    @Test
    public void parse_validArgs_returnsPayCommand() {
        assertParseSuccess(parser, "1 2.50",
                new PayCommand(INDEX_FIRST_PERSON, new Balance(250)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Test with invalid index
        assertParseFailure(parser, "a 2.50",
                ParserUtil.MESSAGE_NOT_A_INDEX);

        // Test with invalid negative balance
        assertParseFailure(parser, "1 -2.50", Balance.MESSAGE_CONSTRAINTS);

        // Test with missing arguments
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayCommand.MESSAGE_USAGE));

        // Test with extra arguments
        assertParseFailure(parser, "1 2.50 extraArg", Balance.MESSAGE_CONSTRAINTS);
    }
}
