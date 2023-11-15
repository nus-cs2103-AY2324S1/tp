package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TWO_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DELETE_MONTH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELETE_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELETE_MONTH_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_MONTH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.BatchDeleteCommand;
import seedu.address.model.month.DeleteMonth;
import seedu.address.model.policy.Company;

public class BatchDeleteCommandParserTest {
    private BatchDeleteCommandParser parser = new BatchDeleteCommandParser();

    @Test
    public void parse_validInputs_success() {
        DeleteMonth month = new DeleteMonth(VALID_DELETE_MONTH);
        assertParseSuccess(parser, VALID_DELETE_MONTH_DESC, new BatchDeleteCommand(month, null));

        Company company = new Company(VALID_COMPANY_AMY);
        assertParseSuccess(parser, COMPANY_DESC_AMY, new BatchDeleteCommand(null, company));
    }

    @Test
    public void parse_twoPrefixExistInput_failure() {
        assertParseFailure(parser, VALID_DELETE_MONTH_DESC + COMPANY_DESC_AMY,
                String.format(MESSAGE_INVALID_TWO_FIELD, PREFIX_DELETE_MONTH, PREFIX_COMPANY));
    }

    @Test
    public void parse_noPrefixInput_failure() {
        assertParseFailure(parser, VALID_COMPANY_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedValue_failure() {
        // multiple delete months
        assertParseFailure(parser, VALID_DELETE_MONTH_DESC + VALID_DELETE_MONTH_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DELETE_MONTH));

        // multiple companies
        assertParseFailure(parser, COMPANY_DESC_AMY + COMPANY_DESC_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid delete month
        assertParseFailure(parser, INVALID_DELETE_MONTH_DESC, DeleteMonth.MESSAGE_CONSTRAINTS);
    }
}
