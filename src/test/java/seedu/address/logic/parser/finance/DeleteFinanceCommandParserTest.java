package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.finance.DeleteFinanceCommand;


class DeleteFinanceCommandParserTest {
    private final DeleteFinanceCommandParser parser = new DeleteFinanceCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteFinanceCommand() {
        assertParseSuccess(parser, "1", new DeleteFinanceCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFinanceCommand.MESSAGE_USAGE));
    }

}
