package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_ON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLASH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewLeaveCommand;

class ViewLeaveCommandParserTest {

    private static final LocalDate VALID_DATE = LocalDate.now();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String FORMATTED_DATE = VALID_DATE.format(FORMATTER);
    private ViewLeaveCommandParser parser = new ViewLeaveCommandParser();

    @Test
    public void parse_validArgs_returnsViewLeaveCommand() {
        assertParseSuccess(parser, " " + PREFIX_ADD_ANNUAL_LEAVE_ON + FORMATTED_DATE,
                new ViewLeaveCommand(VALID_DATE));
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_ADD_ANNUAL_LEAVE_ON + "32/11/2023",
                MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_emptyDate_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_ADD_ANNUAL_LEAVE_ON + "       ",
                MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_wrongPrefix_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_SLASH + FORMATTED_DATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewLeaveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "            ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewLeaveCommand.MESSAGE_USAGE));
    }
}
