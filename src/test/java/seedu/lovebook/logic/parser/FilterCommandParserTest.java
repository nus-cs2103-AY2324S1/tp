package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
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
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "filter name", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyKeyword_throwsParseException() {
        assertParseFailure(parser, "filter name/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        predicateList.add(new MetricContainsKeywordPredicate("Alice", PREFIX_NAME));
        predicateList.add(new MetricContainsKeywordPredicate("22", PREFIX_AGE));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, "filter age/ 22 name/ Alice", expectedFilterCommand);
    }
    @Test
    public void parse_validArgs_returnsFilterCommand2() {
        predicateList.add(new MetricContainsKeywordPredicate("123", PREFIX_HEIGHT));
        predicateList.add(new MetricContainsKeywordPredicate("3000", PREFIX_INCOME));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, "filter income/ 3000 height/ 123", expectedFilterCommand);
    }
    @Test
    public void parse_validArgs_returnsFilterCommand3() {
        predicateList.add(new MetricContainsKeywordPredicate("F", PREFIX_GENDER));
        predicateList.add(new MetricContainsKeywordPredicate("Libra", PREFIX_HOROSCOPE));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, "filter horoscope/ Libra gender/ F", expectedFilterCommand);
    }
}
