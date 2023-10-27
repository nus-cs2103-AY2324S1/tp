package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.model.person.Attendance;
import seedu.address.model.week.Week;

/**
 * Test class for MarkAttendanceCommandParser.
 *
 * Tests include parsing valid arguments to return a MarkAttendanceCommand object and handling invalid arguments.
 */
public class MarkAttendanceCommandParserTest {

    private MarkAttendanceCommandParser parser = new MarkAttendanceCommandParser();

    /**
     * Tests the parsing of valid arguments using a name to return a {@code MarkAttendanceCommand}.
     */
    @Test
    public void parse_validArgsWithName_returnsMarkAttendanceCommand() {
        String userInput = " " + PREFIX_NAME + " " + VALID_NAME_AMY + " " + PREFIX_ATTENDANCE + " 1 "
                + PREFIX_WEEK + " 1";
        MarkAttendanceCommand expectedCommand = new MarkAttendanceCommand(List.of(VALID_NAME_AMY), true, new Week(1));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the parsing of valid arguments using an ID to return a {@code MarkAttendanceCommand}.
     */
    @Test
    public void parse_validArgsWithId_returnsMarkAttendanceCommand() {
        String userInput = " " + PREFIX_ID + " " + VALID_ID_AMY + " " + PREFIX_ATTENDANCE + " 1 " + PREFIX_WEEK + " 1";
        MarkAttendanceCommand expectedCommand = new MarkAttendanceCommand(List.of(VALID_ID_AMY), true, new Week(1));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the parsing of valid arguments using multiple names to return a {@code MarkAttendanceCommand}.
     */
    @Test
    public void parse_validArgsWithMultipleNames_returnsMarkAttendanceCommand() {
        String userInput = " " + PREFIX_NAME + " " + VALID_NAME_AMY + "," + VALID_NAME_BOB + " "
                + PREFIX_ATTENDANCE + " 1 " + PREFIX_WEEK + " 1";
        MarkAttendanceCommand expectedCommand = new MarkAttendanceCommand(List.of(VALID_NAME_AMY, VALID_NAME_BOB), true,
                new Week(1));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the parsing of valid arguments using multiple IDs to return a {@code MarkAttendanceCommand}.
     */
    @Test
    public void parse_validArgsWithMultipleIds_returnsMarkAttendanceCommand() {
        String reasonForAbsence = "Late";
        String userInput = " " + PREFIX_ID + " " + VALID_ID_AMY + "," + VALID_ID_BOB + " " + PREFIX_ATTENDANCE + " 0 "
                + PREFIX_WEEK + " 1 " + PREFIX_REASON + " " + reasonForAbsence;
        MarkAttendanceCommand expectedCommand = new MarkAttendanceCommand(List.of(VALID_ID_AMY, VALID_ID_BOB), false,
                new Week(1), reasonForAbsence);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the parsing of arguments with unexpected preamble data to ensure a {@code ParseException} is thrown.
     */
    @Test
    public void parse_unexpectedPreamble_throwsParseException() {
        // Unexpected preamble data "unexpected" before the valid arguments
        assertParseFailure(parser, "unexpected " + PREFIX_NAME + " " + VALID_NAME_AMY + " "
                        + PREFIX_ATTENDANCE + " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));

        // Unexpected preamble data "unexpected" before the valid arguments with ID
        assertParseFailure(parser, "unexpected " + PREFIX_ID + " " + VALID_ID_AMY + " "
                        + PREFIX_ATTENDANCE + " 0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
    }

    /**
     * Tests the parsing of invalid arguments to ensure a {@code ParseException} is thrown.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid attendance value
        assertParseFailure(parser, " " + PREFIX_NAME + " " + VALID_NAME_AMY + " "
                        + PREFIX_ATTENDANCE + " 2 " + PREFIX_WEEK + " 1",
                String.format(Attendance.MESSAGE_CONSTRAINTS));

        // Missing name prefix
        assertParseFailure(parser, VALID_NAME_AMY + " " + PREFIX_ATTENDANCE + " 1 " + PREFIX_WEEK + " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));

        // Missing attendance prefix
        assertParseFailure(parser, " " + PREFIX_NAME + " " + VALID_NAME_AMY + " 1 " + PREFIX_WEEK + " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));

        // Missing week prefix
        assertParseFailure(parser, " " + PREFIX_NAME + " " + VALID_NAME_AMY + " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
    }
}
