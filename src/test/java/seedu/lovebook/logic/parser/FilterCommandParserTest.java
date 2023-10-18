package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.FilterCommand;
import seedu.lovebook.model.person.MetricContainsKeywordPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();
    private final ArrayList<MetricContainsKeywordPredicate> predicateList = new ArrayList<>();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        predicateList.add(new MetricContainsKeywordPredicate("Alice", PREFIX_NAME));
        FilterCommand expectedFilterCommand =
                new FilterCommand(predicateList);
        assertParseSuccess(parser, "filter name/ Alice", expectedFilterCommand);
    }
}
