package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.listcommands.ListCommand;
import seedu.address.logic.commands.listcommands.ListCompaniesCommand;
import seedu.address.logic.commands.listcommands.ListPersonsCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_invalidTypeArg_failure() {
        String expectedMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

        // Empty argument type
        assertParseFailure(parser, "", expectedMessage);

        // Invalid argument type
        assertParseFailure(parser, " ", expectedMessage);
        assertParseFailure(parser, " hi ", expectedMessage);
    }

    @Test
    public void parse_validTypeArg_success() {
        assertParseSuccess(parser, " p ", new ListPersonsCommand());
        assertParseSuccess(parser, " c ", new ListCompaniesCommand());
    }
}
