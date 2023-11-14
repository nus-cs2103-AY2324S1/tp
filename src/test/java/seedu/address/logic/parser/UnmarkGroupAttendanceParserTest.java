package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkGroupAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Group;

public class UnmarkGroupAttendanceParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkGroupAttendanceCommand.MESSAGE_USAGE);

    private UnmarkGroupAttendanceParser parser = new UnmarkGroupAttendanceParser();

    @Test
    public void parse_missingParts_failure() {
        // Test case 1: Missing group (preamble)
        String userInput1 = "t/1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // Test case 2: Missing week (tutorial)
        String userInput2 = "1";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Missing both index and tutorial
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_incorrectPrefixes_failure() {
        assertParseFailure(parser, "Group1 a/1", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "Group1 -1/1", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "Group 1 was absent, my bad", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidGroup_failure() {
        // Invalid index (not alphanumeric)
        assertParseFailure(parser, "t/1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "#$%! t/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTutorial_failure() {
        // Invalid tutorial (not a positive integer)
        assertParseFailure(parser, "Group1 t/a", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (0)
        assertParseFailure(parser, "Group1 t/0", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (negative)
        assertParseFailure(parser, "Group1 t/-5", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (greater than 12)
        assertParseFailure(parser, "Group1 t/13", Attendance.TUTORIAL_ERROR_MSG);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        assertParseSuccess(parser, "Group1 t/1",
                new UnmarkGroupAttendanceCommand(new Group("Group1"), Index.fromOneBased(1)));

        assertParseSuccess(parser, "Group1 t/12",
                new UnmarkGroupAttendanceCommand(new Group("Group1"), Index.fromOneBased(12)));

        // Valid input with different group and tutorial
        assertParseSuccess(parser, "Group2 t/5",
                new UnmarkGroupAttendanceCommand(new Group("Group2"), Index.fromOneBased(5)));
    }
}
