package seedu.address.logic.parser.customer;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIEW_CUSTOMER_ID_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.customer.CustomerViewCommand;

public class CustomerViewCommandParserTest {

    private CustomerViewCommandParser parser = new CustomerViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CustomerViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCustomerViewCommand() {
        assertParseSuccess(parser, VALID_VIEW_CUSTOMER_ID_1,
            new CustomerViewCommand(1));
    }

    @Test
    public void parse_validArgsExtraSpaceBeforeAfter_returnsCustomerViewCommand() {
        assertParseSuccess(parser, " " + VALID_VIEW_CUSTOMER_ID_1 + " ",
            new CustomerViewCommand(1));
    }

    @Test
    public void parse_invalidArgsIntegerMaxValue_throwsParseException() {
        assertParseFailure(parser, "2147483648",
            MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidArgsZero_throwsParseException() {
        assertParseFailure(parser, "0",
            MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidArgsNegative_throwsParseException() {
        assertParseFailure(parser, "-1",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CustomerViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsNaN_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CustomerViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsExtraNumber_throwsParseException() {
        assertParseFailure(parser, "1 1",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CustomerViewCommand.MESSAGE_USAGE));
    }
}
