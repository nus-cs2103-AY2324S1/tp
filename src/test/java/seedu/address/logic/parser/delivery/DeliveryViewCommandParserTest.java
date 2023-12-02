package seedu.address.logic.parser.delivery;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_MAX_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_NAN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_ZERO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIEW_DELIVERY_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.delivery.DeliveryViewCommand;


public class DeliveryViewCommandParserTest {

    private DeliveryViewCommandParser parser = new DeliveryViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgWhiteSpace_throwsParseException() {
        assertParseFailure(parser, " ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgEscape_throwsParseException() {
        assertParseFailure(parser, "\n\r\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeliveryViewCommand() {
        assertParseSuccess(parser, VALID_VIEW_DELIVERY_ID,
            new DeliveryViewCommand(1));
    }

    @Test
    public void parse_validArgsExtraSpaceBetween_returnsDeliveryViewCommand() {
        assertParseSuccess(parser, " " + VALID_VIEW_DELIVERY_ID,
            new DeliveryViewCommand(1));
    }

    @Test
    public void parse_invalidArgsNanId_returnsDeliveryViewCommand() {
        assertParseFailure(parser, INVALID_ID_NAN,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsZeroId_returnsDeliveryViewCommand() {
        assertParseFailure(parser, INVALID_ID_ZERO,
            MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidArgsNegativeId_returnsDeliveryViewCommand() {
        assertParseFailure(parser, INVALID_ID_NEGATIVE,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsMaxValueId_returnsDeliveryViewCommand() {
        assertParseFailure(parser, INVALID_ID_MAX_VALUE,
            MESSAGE_INVALID_INDEX);
    }
}
