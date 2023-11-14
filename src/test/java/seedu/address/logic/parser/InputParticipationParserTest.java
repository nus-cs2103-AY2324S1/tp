package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InputParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;


public class InputParticipationParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InputParticipationCommand.MESSAGE_USAGE);
    private InputParticipationParser parser = new InputParticipationParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        assertParseSuccess(parser, "1 t/1 pp/50",
                new InputParticipationCommand(Index.fromOneBased(1),
                        Index.fromOneBased(1), 50));
    }

    @Test
    public void parse_missingIndex_failure() {
        // Missing index
        assertParseFailure(parser, "t/1 pp/50", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingTutorial_failure() {
        // Missing tutorial
        assertParseFailure(parser, "1 pp/50", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPoints_failure() {
        // Missing points
        assertParseFailure(parser, "1 t/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // Invalid index (not a positive integer)
        assertParseFailure(parser, "a t/1 pp/50", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "-1 t/1 pp/50", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "0 t/1 pp/50", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTutorial_failure() {
        // Invalid tutorial (not a positive integer)
        assertParseFailure(parser, "1 t/a pp/50", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (0)
        assertParseFailure(parser, "1 t/0 pp/50", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (negative)
        assertParseFailure(parser, "1 t/-5 pp/50", Attendance.TUTORIAL_ERROR_MSG);

        // Invalid tutorial (greater than 12)
        assertParseFailure(parser, "1 t/13 pp/50", Attendance.TUTORIAL_ERROR_MSG);
    }

    @Test
    public void parse_invalidPoints_failure() {
        // Invalid points (not a positive integer)
        assertParseFailure(parser, "1 t/1 pp/a", Attendance.PARTICIPATION_ERROR_MSG);

        // Invalid points (negative)
        assertParseFailure(parser, "1 t/1 pp/-50", Attendance.PARTICIPATION_ERROR_MSG);

        // Invalid points (more than 1000)
        assertParseFailure(parser, "1 t/1 pp/5000", Attendance.PARTICIPATION_ERROR_MSG);
    }
}
