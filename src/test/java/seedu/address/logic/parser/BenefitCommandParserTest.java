package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.BenefitCommand;
import seedu.address.model.person.Benefit;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Reason;

public class BenefitCommandParserTest {

    private final BenefitCommandParser parser = new BenefitCommandParser();

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

        // reasons for deduction -> throws ParseException
        assertParseFailure(parser, "3 /v 150.00 /r cpf", Benefit.MESSAGE_CONSTRAINTS_INVALID_REASON);
        assertParseFailure(parser, "3 /v 150.00 /r no pay", Benefit.MESSAGE_CONSTRAINTS_INVALID_REASON);
        assertParseFailure(parser, "3 /v 150.00 /r absence", Benefit.MESSAGE_CONSTRAINTS_INVALID_REASON);

        // invalid index -> throws ParseException
        assertParseFailure(parser, "-1 /v 150.00 /r bonus",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE)); // negative index
        assertParseFailure(parser, "99999999999 /v 150.00 /r bonus",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE)); // integer overflow
        assertParseFailure(parser, "1.1 /v 150.00 /r bonus",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE)); // non-integer index

        // missing name and index -> throws ParseException
        assertParseFailure(parser, "/v 150.00 /r bonus",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE));

        // missing value -> throws ParseException
        assertParseFailure(parser, "1 /r bonus",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "/n Yu /r bonus",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE));

        // invalid value -> throws ParseException
        assertParseFailure(parser, "1 /v 150.00.00 /r bonus", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 /v 150 /r bonus", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 /v 150.0 /r bonus", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "/n Yu /v 150.000 /r bonus", Payment.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "/n Yu /v -150.00 /r bonus", Payment.MESSAGE_CONSTRAINTS);

        // empty command arguments -> throws ParseException
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE)); // empty string
        assertParseFailure(parser, " ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE)); // whitespace

        // repetitive prefixes -> throws ParseException
        assertParseFailure(parser, "1 /v 150.00 /v 200.00 /r bonus",
            Messages.getErrorMessageForDuplicatePrefixes(new Prefix("/v")));
        assertParseFailure(parser, "1 /v 150.00 /r transport /r bonus",
            Messages.getErrorMessageForDuplicatePrefixes(new Prefix("/r")));
    }

    @Test
    public void parse_validArgs_returnsBenefitCommand() throws Exception {
        // valid input -> returns BenefitCommand
        BenefitCommand expectedBenefitCommand = new BenefitCommand(Index.fromZeroBased(0),
            new Benefit("150.00", Reason.ANNUAL_BONUS));
        BenefitCommand expectedBenefitCommand2 = new BenefitCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("Yu")),
            new Benefit("150.00", Reason.ANNUAL_BONUS));
        BenefitCommand expectedBenefitCommand3 = new BenefitCommand(Index.fromZeroBased(0),
            new Benefit("150.00", Reason.TRANSPORT_ALLOWANCE));
        BenefitCommand expectedBenefitCommand4 = new BenefitCommand(
            new NameContainsKeywordsPredicate(Collections.singletonList("Yu")),
            new Benefit("150.00", Reason.TRANSPORT_ALLOWANCE));

        assertParseSuccess(parser, " 1 /v 150.00 /r bonus", expectedBenefitCommand);
        assertParseSuccess(parser, " /n Yu /v 150.00 /r bonus", expectedBenefitCommand2);
        assertParseSuccess(parser, " 1 /v 150.00 /r transport", expectedBenefitCommand3);
        assertParseSuccess(parser, " /n Yu /v 150.00 /r transport", expectedBenefitCommand4);
    }
}
