package seedu.classmanager.logic.parser;

import static seedu.classmanager.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classmanager.logic.commands.CommandTestUtil.INVALID_STUDENT_NUMBER_DESC;
import static seedu.classmanager.logic.commands.CommandTestUtil.STUDENT_NUMBER_DESC_BOB;
import static seedu.classmanager.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classmanager.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.logic.commands.MarkAbsentCommand;
import seedu.classmanager.model.student.ClassDetails;
import seedu.classmanager.model.student.StudentNumber;

/**
 * Tests MarkAbsentCommandParser.
 */
public class MarkAbsentCommandParserTest {
    private MarkAbsentCommandParser parser = new MarkAbsentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAbsentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAbsentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        int tut = 1;
        assertParseSuccess(parser, " " + tut + STUDENT_NUMBER_DESC_BOB,
                new MarkAbsentCommand(Index.fromOneBased(tut), new StudentNumber(VALID_STUDENT_NUMBER_BOB)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        int tut = 1;
        assertParseFailure(parser, STUDENT_NUMBER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAbsentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " " + tut,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAbsentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStudentNumber_throwsParseException() {
        int tut = 1;
        assertParseFailure(parser, " " + tut + INVALID_STUDENT_NUMBER_DESC, StudentNumber.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTutorialIndex_throwsParseException() {
        ClassDetails.setTutorialCount(10);
        assertParseFailure(parser, " -1" + STUDENT_NUMBER_DESC_BOB,
                ClassDetails.getMessageInvalidTutorialIndex());
        assertParseFailure(parser, " test" + STUDENT_NUMBER_DESC_BOB,
                ClassDetails.getMessageInvalidTutorialIndex());
    }
}
