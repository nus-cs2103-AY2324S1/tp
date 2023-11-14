package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;

public class MarkAttendanceParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE);

    private MarkAttendanceParser parser = new MarkAttendanceParser();

    @Test
    public void parse_missingParts_failure() {
        // Test case 1: Missing index (preamble)
        String userInput1 = "t/1 s/P";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // Test case 2: Missing week (tutorial)
        String userInput2 = "1 s/P";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Test case 3: Missing status
        String userInput3 = "1 t/1";
        assertParseFailure(parser, userInput3, MESSAGE_INVALID_FORMAT);

        // Missing both index, tutorial and status
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_incorrectFormat_failure() {
        // Incorrect parse prefix for tutorial
        assertParseFailure(parser, "1 a/1 s/P", MESSAGE_INVALID_FORMAT);

        // Incorrect parse prefix for status
        assertParseFailure(parser, "1 t/1 n/P", MESSAGE_INVALID_FORMAT);

        // Incorrect command prefixes in general
        assertParseFailure(parser, "n/Jerry Seinfeld", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // Invalid index (not a positive integer)
        assertParseFailure(parser, "a t/1 s/P", MESSAGE_INVALID_FORMAT);

        // Invalid index (zero)
        assertParseFailure(parser, "0 t/1 s/P", MESSAGE_INVALID_FORMAT);

        // Invalid index (negative integer)
        assertParseFailure(parser, "-1 t/1 S/P", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTutorial_failure() {
        // Invalid tutorial (not a positive integer)
        assertParseFailure(parser, "1 t/a s/P", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (0)
        assertParseFailure(parser, "1 t/0 s/P", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (negative)
        assertParseFailure(parser, "1 t/-5 s/P", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (greater than 12)
        assertParseFailure(parser, "1 t/13 s/P", Attendance.TUTORIAL_ERROR_MSG);
    }

    @Test
    public void parse_invalidStatus_failure() {
        // Invalid status (Not a letter)
        assertParseFailure(parser, "1 t/1 s/1", Attendance.STATUS_ERROR_MSG);

        // Invalid status (Not recognised as a valid status)
        assertParseFailure(parser, "1 t/1 s/G", Attendance.STATUS_ERROR_MSG);

        // Invalid status (Unknown - Unmarked status, not one used for marking)
        assertParseFailure(parser, "1 t/1 s/U", Attendance.STATUS_ERROR_MSG);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        assertParseSuccess(parser, "1 t/1 s/P", new MarkAttendanceCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), "P"));

        assertParseSuccess(parser, "1 t/12 s/P", new MarkAttendanceCommand(Index.fromOneBased(1),
                Index.fromOneBased(12), "P"));

        // Valid input with different indices and tutorial
        assertParseSuccess(parser, "2 t/5 s/P", new MarkAttendanceCommand(Index.fromOneBased(2),
                Index.fromOneBased(5), "P"));
    }
}
