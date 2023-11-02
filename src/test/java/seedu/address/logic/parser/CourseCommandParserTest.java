package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CourseCommand;
import seedu.address.logic.parser.ParserUtil.CourseOperation;

public class CourseCommandParserTest {
    private CourseCommandParser parser = new CourseCommandParser();

    @Test
    public void parse_validArgs_returnsCourseCommand() {
        assertParseSuccess(parser, "create course/CS2030S",
                new CourseCommand(CourseOperation.CREATE, "CS2030S"));
        assertParseSuccess(parser, "switch course/CS2030S",
                new CourseCommand(CourseOperation.SWITCH, "CS2030S"));
        assertParseSuccess(parser, "delete course/CS2030S",
                new CourseCommand(CourseOperation.DELETE, "CS2030S"));
        assertParseSuccess(parser, "edit course/CS2030S",
                new CourseCommand(CourseOperation.EDIT, "CS2030S"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CourseCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CourseCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "create",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CourseCommand.MESSAGE_USAGE));
    }
}
