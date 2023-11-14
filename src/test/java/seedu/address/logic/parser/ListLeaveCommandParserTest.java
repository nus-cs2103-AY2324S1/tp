package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListLeaveCommand;

public class ListLeaveCommandParserTest {

    private ListLeaveCommandParser parser = new ListLeaveCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListLeaveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListLeaveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_dateMissing_throwsParseException() {
        assertParseFailure(parser, " on/", ListLeaveCommand.MISSING_DATE);
        assertParseFailure(parser, " on/    ", ListLeaveCommand.MISSING_DATE);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid date
        assertParseFailure(parser, " on/2023-02-30", Messages.MESSAGE_INVALID_DATE);
        assertParseFailure(parser, " on/2023-31-11", Messages.MESSAGE_INVALID_DATE);

        // invalid format
        assertParseFailure(parser, " on/30-12-2023", Messages.MESSAGE_INVALID_DATE);
        assertParseFailure(parser, " on/30 Dec 2023", Messages.MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_validArgs_returnsListLeaveCommand() {
        LocalDate date = LocalDate.parse("2023-11-11", DateTimeFormatter.ISO_LOCAL_DATE);
        ListLeaveCommand expectedListLeaveCommand = new ListLeaveCommand(date);
        assertParseSuccess(parser, " on/2023-11-11", expectedListLeaveCommand);
    }
}
