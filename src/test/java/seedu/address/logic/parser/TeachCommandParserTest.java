package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TeachCommand;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;

public class TeachCommandParserTest {

    private final TeachCommandParser parser = new TeachCommandParser();

    @Test
    public void parse_validArgs_returnsTeachCommand() {
        Course course = UniqueCourseList.findByCourseCode("CS2103T");
        assertParseSuccess(parser, " c/CS2103T", new TeachCommand(course));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TeachCommand.MESSAGE_USAGE));
    }
}
