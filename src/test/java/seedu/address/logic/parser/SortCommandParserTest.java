package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.REVERSE_KEYWORD;
import static seedu.address.logic.commands.SortCommand.SORTBY_KEYWORD1;
import static seedu.address.logic.commands.SortCommand.SORTBY_KEYWORD2;
import static seedu.address.logic.commands.SortCommand.SORTBY_KEYWORD3;
import static seedu.address.logic.commands.SortCommand.SORTBY_KEYWORD4;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.SortCommandParser.PARSE_EXCEPTION_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.SortCommand;


public class SortCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingDelimiters_failure() {
        assertParseFailure(parser, " 2", PARSE_EXCEPTION_MESSAGE);

        assertParseFailure(parser, " abc", PARSE_EXCEPTION_MESSAGE);
    }

    @Test
    public void parse_emptyInput_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidDelimiters_failure() {
        assertParseFailure(parser, " -bydescription", PARSE_EXCEPTION_MESSAGE);
        assertParseFailure(parser, " -byname", PARSE_EXCEPTION_MESSAGE);
    }

    @Test
    public void parse_wrongSortComparator_failure() {
        assertParseFailure(parser, " /name", PARSE_EXCEPTION_MESSAGE);
        assertParseFailure(parser, " /phone", PARSE_EXCEPTION_MESSAGE);
    }

    @Test
    public void parse_tooManyArgs_failure() {
        assertParseFailure(parser, " /byname /reverse /byphone", PARSE_EXCEPTION_MESSAGE);
        assertParseFailure(parser, " /byname /byphone", PARSE_EXCEPTION_MESSAGE);
    }

    @Test
    public void parse_firstArgIsReverse_failure() {
        assertParseFailure(parser, " /reverse", PARSE_EXCEPTION_MESSAGE);
    }

    @Test
    public void parse_sortComparator_success1() {
        try {
            Command command = parser.parse(" " + SORTBY_KEYWORD1);
        } catch (Exception e) {
            fail("Fail");
        }
        assertTrue(true);
    }

    @Test
    public void parse_sortComparator_success2() {
        try {
            Command command = parser.parse(" " + SORTBY_KEYWORD2);
        } catch (Exception e) {
            fail("Fail");
        }
        assertTrue(true);
    }
    @Test
    public void parse_sortComparator_success3() {
        try {
            Command command = parser.parse(" " + SORTBY_KEYWORD3);
        } catch (Exception e) {
            fail("Fail");
        }
        assertTrue(true);
    }
    @Test
    public void parse_sortComparator_success4() {
        try {
            Command command = parser.parse(" " + SORTBY_KEYWORD4);
        } catch (Exception e) {
            fail("Fail");
        }
        assertTrue(true);
    }

    @Test
    public void parse_sortComparator_success5() {
        try {
            Command command = parser.parse(" " + SORTBY_KEYWORD2 + " " + REVERSE_KEYWORD);
        } catch (Exception e) {
            fail("Fail");
        }
        assertTrue(true);
    }

    @Test
    public void parse_sortComparator_success6() {
        try {
            Command command = parser.parse(" " + SORTBY_KEYWORD4 + " " + REVERSE_KEYWORD);
        } catch (Exception e) {
            fail("Fail");
        }
        assertTrue(true);
    }
}
