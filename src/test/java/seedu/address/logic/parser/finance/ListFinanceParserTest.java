package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.finance.ListFinancesCommand;
import seedu.address.model.finance.FinanceListType;

class ListFinanceParserTest {
    private final ListFinanceParser parser = new ListFinanceParser();

    @Test
    public void parse_validArgs_returnsListFinanceCommand() {
        ListFinancesCommand expectedListAllFinanceCommand = new ListFinancesCommand(FinanceListType.ALL);
        ListFinancesCommand expectedListExpenseFinanceCommand = new ListFinancesCommand(FinanceListType.EXPENSE);
        ListFinancesCommand expectedListCommissionFinanceCommand = new ListFinancesCommand(FinanceListType.COMMISSION);

        assertParseSuccess(parser, "       ", expectedListAllFinanceCommand);
        assertParseSuccess(parser, "expense", expectedListExpenseFinanceCommand);
        assertParseSuccess(parser, "commission", expectedListCommissionFinanceCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "fail",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListFinancesCommand.MESSAGE_USAGE));
    }

}
