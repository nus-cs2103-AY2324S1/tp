package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeductCommand;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Reason;

public class DeductCommandParserTest {

    private final DeductCommandParser parser = new DeductCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() throws Exception {
        // null -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // missing reason -> throws ParseException
        assertParseFailure(parser, "3 /v 150.00",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, Reason.MISSING_ALERT));
        assertParseFailure(parser, "/n Yu /v 150.00",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, Reason.MISSING_ALERT));

        // unsupported reason -> throws ParseException
        assertParseFailure(parser, "3 /v 150.00 /r xxx", Reason.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "/n Yu /v 150.00 /r xxx", Reason.MESSAGE_CONSTRAINTS);

        // reasons for benefit -> throws ParseException
        assertParseFailure(parser, "3 /v 150.00 /r bonus", Deduction.MESSAGE_CONSTRAINTS_INVALID_REASON);
        assertParseFailure(parser, "3 /v 150.00 /r transport", Deduction.MESSAGE_CONSTRAINTS_INVALID_REASON);

        // invalid index -> throws ParseException
        assertParseFailure(parser, "-1 /v 150.00 /r cpf",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE)); // negative index
        assertParseFailure(parser, "99999999999 /v 150.00 /r cpf",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE)); // integer overflow
        assertParseFailure(parser, "1.1 /v 150.00 /r cpf",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE)); // non-integer index

        // missing name and index -> throws ParseException
        assertParseFailure(parser, "/v 150.00 /r cpf",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE));

        // missing value -> throws ParseException
        assertParseFailure(parser, "1 /r cpf",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "/n Yu /r cpf",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE));

        // invalid value -> throws ParseException
        assertParseFailure(parser, "1 /v 150.00.00 /r cpf", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 /v 150 /r cpf", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 /v 150.0 /r cpf", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "/n Yu /v 150.000 /r cpf", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "/n Yu /v -150.00 /r cpf", Payment.MESSAGE_CONSTRAINTS);

        // empty command arguments -> throws ParseException
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE)); // empty string
        assertParseFailure(parser, " ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeductCommand.MESSAGE_USAGE)); // whitespace

        // repetitive prefixes -> throws ParseException
        assertParseFailure(parser, "1 /v 150.00 /v 200.00 /r cpf",
            Messages.getErrorMessageForDuplicatePrefixes(new Prefix("/v")));
        assertParseFailure(parser, "1 /v 150.00 /r no pay /r cpf",
            Messages.getErrorMessageForDuplicatePrefixes(new Prefix("/r")));
    }

    @Test
    public void parse_validArgs_returnsDeductCommand() throws Exception {
        // valid input -> returns DeductCommand
        DeductCommand expectedDeductCommand = new DeductCommand(Index.fromZeroBased(0),
            new Deduction("150.00", Reason.EMPLOYEE_CPF_DEDUCTION));
        DeductCommand expectedDeductCommand2 = new DeductCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("Yu")),
            new Deduction("150.00", Reason.EMPLOYEE_CPF_DEDUCTION));
        DeductCommand expectedDeductCommand3 = new DeductCommand(Index.fromZeroBased(0),
            new Deduction("150.00", Reason.NO_PAY_LEAVE));
        DeductCommand expectedDeductCommand4 = new DeductCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("Yu")),
            new Deduction("150.00", Reason.NO_PAY_LEAVE));
        DeductCommand expectedDeductCommand5 = new DeductCommand(Index.fromZeroBased(0),
            new Deduction("150.00", Reason.ABSENCE));
        DeductCommand expectedDeductCommand6 = new DeductCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("Yu")),
            new Deduction("150.00", Reason.ABSENCE));

        assertParseSuccess(parser, " 1 /v 150.00 /r cpf", expectedDeductCommand);
        assertParseSuccess(parser, " /n Yu /v 150.00 /r cpf", expectedDeductCommand2);
        assertParseSuccess(parser, " 1 /v 150.00 /r no pay", expectedDeductCommand3);
        assertParseSuccess(parser, " /n Yu /v 150.00 /r no pay", expectedDeductCommand4);
        assertParseSuccess(parser, " 1 /v 150.00 /r absence", expectedDeductCommand5);
        assertParseSuccess(parser, " /n Yu /v 150.00 /r absence", expectedDeductCommand6);
    }
}
