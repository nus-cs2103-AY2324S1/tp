package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.FilterCommand;
import seedu.lovebook.model.person.MetricContainsKeywordPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new MetricContainsKeywordPredicate("Alice", PREFIX_NAME));
        assertParseSuccess(parser, "n/ Alice", expectedFilterCommand);
    }
}
