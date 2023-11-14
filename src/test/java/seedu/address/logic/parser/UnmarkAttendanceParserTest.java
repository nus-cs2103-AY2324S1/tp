package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;

public class UnmarkAttendanceParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkAttendanceCommand.MESSAGE_USAGE);

    private UnmarkAttendanceParser parser = new UnmarkAttendanceParser();

    @Test
    public void parse_missingParts_failure() {
        // Test case 1: Missing index (preamble)
        String userInput1 = "t/1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // Test case 2: Missing week (tutorial)
        String userInput2 = "1";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Missing both index and tutorial
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_incorrectParts_failure() {
        // Test case 1: Incorrect prefix for tutorial
        String userInput1 = "a/1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // Test case 2: Incorrect command in general
        String userInput2 = "n/Homura Akemi";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // Invalid index (not a positive integer)
        assertParseFailure(parser, "a t/1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0 t/1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1 t/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTutorial_failure() {
        // Invalid tutorial (not a positive integer)
        assertParseFailure(parser, "1 t/a", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (0)
        assertParseFailure(parser, "1 t/0", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (negative)
        assertParseFailure(parser, "1 t/-5", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (greater than 12)
        assertParseFailure(parser, "1 t/13", Attendance.TUTORIAL_ERROR_MSG);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        assertParseSuccess(parser, "1 t/1", new UnmarkAttendanceCommand(Index.fromOneBased(1),
                Index.fromOneBased(1)));

        assertParseSuccess(parser, "1 t/12", new UnmarkAttendanceCommand(Index.fromOneBased(1),
                Index.fromOneBased(12)));

        // Valid input with different indices and tutorial
        assertParseSuccess(parser, "2 t/5", new UnmarkAttendanceCommand(Index.fromOneBased(2),
                Index.fromOneBased(5)));
    }
}
