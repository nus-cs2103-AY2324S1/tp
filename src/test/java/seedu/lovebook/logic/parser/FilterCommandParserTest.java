package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.FilterCommand;
import seedu.lovebook.model.date.MetricContainsKeywordPredicate;
import seedu.lovebook.model.date.Name;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " name", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyKeyword_throwsParseException() {
        assertParseFailure(parser, " name/", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        ArrayList<MetricContainsKeywordPredicate> predicateList = new ArrayList<>();
        predicateList.add(new MetricContainsKeywordPredicate("Alice", PREFIX_NAME));
        predicateList.add(new MetricContainsKeywordPredicate("22", PREFIX_AGE));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, " age/22 name/Alice", expectedFilterCommand);
    }
    @Test
    public void parse_validArgs_returnsFilterCommand2() {
        ArrayList<MetricContainsKeywordPredicate> predicateList = new ArrayList<>();
        predicateList.add(new MetricContainsKeywordPredicate("20", PREFIX_AGE));
        predicateList.add(new MetricContainsKeywordPredicate("123", PREFIX_HEIGHT));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, " height/123 age/20", expectedFilterCommand);
    }
    @Test
    public void parse_validArgs_returnsFilterCommand3() {
        ArrayList<MetricContainsKeywordPredicate> predicateList = new ArrayList<>();
        predicateList.add(new MetricContainsKeywordPredicate("F", PREFIX_GENDER));
        predicateList.add(new MetricContainsKeywordPredicate("123", PREFIX_HEIGHT));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, " height/123 gender/F", expectedFilterCommand);
    }
}
