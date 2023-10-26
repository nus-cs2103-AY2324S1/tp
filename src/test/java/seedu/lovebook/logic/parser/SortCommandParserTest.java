package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommandParser parser1 = new SortCommandParser();
        SortCommand expectedSortCommand = new SortCommand(new Prefix("name/"), "increasing");
        assertParseSuccess(parser1, " name/ increasing", expectedSortCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " name/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validPrefix_horoscope() {
        String userInput = " horoscope/ increasing";
        SortCommand expectedSortCommand = new SortCommand(new Prefix("horoscope/"), "increasing");
        assertParseSuccess(parser, userInput, expectedSortCommand);
    }

    @Test
    public void parse_validPrefix_age() {
        String userInput = " age/ increasing";
        SortCommand expectedSortCommand = new SortCommand(new Prefix("age/"), "increasing");
        assertParseSuccess(parser, userInput, expectedSortCommand);
    }

    @Test
    public void parse_validPrefix_height() {
        String userInput = " height/ increasing";
        SortCommand expectedSortCommand = new SortCommand(new Prefix("height/"), "increasing");
        assertParseSuccess(parser, userInput, expectedSortCommand);
    }

    @Test
    public void parse_validPrefix_income() {
        String userInput = " income/ increasing";
        SortCommand expectedSortCommand = new SortCommand(new Prefix("income/"), "increasing");
        assertParseSuccess(parser, userInput, expectedSortCommand);
    }

    @Test
    public void parse_invalidPrefix() {
        assertParseFailure(parser, " gender/ increasing",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
