package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CourseCommand;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;

public class CourseCommandParserTest {

    private final CourseCommandParser parser = new CourseCommandParser();

    @Test
    public void parse_validArgs_returnsCourseCommand() {
        Course course = UniqueCourseList.findByCourseCode("CS2103T");
        assertParseSuccess(parser, " c/CS2103T", new CourseCommand(course));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CourseCommand.MESSAGE_USAGE));
    }
}
