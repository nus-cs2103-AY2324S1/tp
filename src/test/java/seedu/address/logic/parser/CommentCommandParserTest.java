package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommentCommand;
import seedu.address.model.student.Comment;
import seedu.address.model.student.StudentNumber;

/**
 * To test CommentCommandParser
 */
public class CommentCommandParserTest {

    private CommentCommandParser parser = new CommentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CommentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCommentCommand() {
        assertParseSuccess(parser, "comment s/ " + VALID_STUDENT_NUMBER_BOB + " cm/ Good student",
                new CommentCommand(new StudentNumber(VALID_STUDENT_NUMBER_BOB), new Comment("Good student")));
    }
}
