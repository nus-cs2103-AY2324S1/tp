package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PayrollCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class PayrollCommandParserTest {

    private PayrollCommandParser parser = new PayrollCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayrollCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsPayrollCommand() {
        PayrollCommand expectedPayrollCommand =
                new PayrollCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy")));
        assertParseSuccess(parser, " /n Amy", expectedPayrollCommand);
    }

    @Test
    public void parse_invalidArgs_returnsParseException() {
        assertParseFailure(parser, "abcd", String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayrollCommand.MESSAGE_USAGE));
    }
}
