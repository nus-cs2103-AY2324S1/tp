package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseFailure;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.Order;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParserTest {

    private static final String DEFAULT_KEYWORD = SortCommand.DEFAULT_KEYWORD;
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_defaultKeyword_returnsDefaultSortCommand() {
        assertInternshipParseSuccess(parser,
                " " + DEFAULT_KEYWORD,
                new SortCommand());
    }
    @Test
    public void parse_companyNameAsc_returnsSortCommand() {
        assertInternshipParseSuccess(parser,
                " c/ASC",
                new SortCommand(PREFIX_COMPANY_NAME, Order.ASC));
    }

    @Test
    public void parse_invalidCompanyName_throwsParseException() {
        assertInternshipParseFailure(parser,
                " c/INVALID",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_roleAsc_returnsSortCommand() {
        assertInternshipParseSuccess(parser,
                " ro/ASC",
                new SortCommand(PREFIX_ROLE, Order.ASC));
    }

    @Test
    public void parse_invalidRole_throwsParseException() {
        assertInternshipParseFailure(parser,
                " ro/INVALID",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_applicationStatusAsc_returnsSortCommand() {
        assertInternshipParseSuccess(parser,
                " a/ASC",
                new SortCommand(PREFIX_APPLICATION_STATUS, Order.ASC));
    }

    @Test
    public void parse_invalidApplicationStatus_throwsParseException() {
        assertInternshipParseFailure(parser,
                " a/INVALID",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_deadlineAsc_returnsSortCommand() {
        assertInternshipParseSuccess(parser,
                " de/ASC",
                new SortCommand(PREFIX_DEADLINE, Order.ASC));
    }

    @Test
    public void parse_invalidDeadline_throwsParseException() {
        assertInternshipParseFailure(parser,
                " de/INVALID",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_startDateAsc_returnsSortCommand() {
        assertInternshipParseSuccess(parser,
                " s/ASC",
                new SortCommand(PREFIX_START_DATE, Order.ASC));
    }

    @Test
    public void parse_invalidStartDate_throwsParseException() {
        assertInternshipParseFailure(parser,
                " s/INVALID",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_durationDesc_returnsSortCommand() {
        assertInternshipParseSuccess(parser,
                " du/DESC",
                new SortCommand(PREFIX_DURATION, Order.DESC));
    }

    @Test
    public void parse_invalidDuration_throwsParseException() {
        assertInternshipParseFailure(parser,
                " du/INVALID",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Test for more than one prefix
        assertThrows(ParseException.class, () -> parser.parse(" ro/ASC c/GOOGLE"));
        // Test for invalid order
        assertThrows(ParseException.class, () -> parser.parse(" ro/INVALID_ORDER"));
        // Test for no arguments
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

}
