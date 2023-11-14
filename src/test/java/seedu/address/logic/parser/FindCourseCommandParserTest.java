package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCourseCommand;
import seedu.address.model.person.CourseContainsKeywordsPredicate;

public class FindCourseCommandParserTest {

    private FindCourseCommandParser parser = new FindCourseCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCourseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCourseCommand() {
        // no leading and trailing whitespaces
        FindCourseCommand expectedFindCourseCommand =
                new FindCourseCommand(new CourseContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS2100")));
        assertParseSuccess(parser, "CS2103T CS2100", expectedFindCourseCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103T \n \t CS2100 \t", expectedFindCourseCommand);

    }

}
