package seedu.address.logic.parser.delivery;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_MAX_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_NAN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_ZERO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.delivery.DeliveryDeleteCommand;

public class DeliveryDeleteCommandParserTest {
    private DeliveryDeleteCommandParser parser = new DeliveryDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "0001", new DeliveryDeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_NAN,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsZero_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_ZERO,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsNegative_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_NEGATIVE,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgIntegerMaxValue_throwsParseException() {
        assertParseFailure(parser, INVALID_ID_MAX_VALUE,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryDeleteCommand.MESSAGE_USAGE));
    }
}


