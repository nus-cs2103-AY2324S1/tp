//@@author tiongjjyi
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.commands.FindCourseCommand;
import seedu.codesphere.model.course.CourseNameContainsKeywordsPredicate;

public class FindCourseCommandParserTest {

    private FindCourseCommandParser parser = new FindCourseCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCourseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCourseCommand expectedFindCommand =
                new FindCourseCommand(new CourseNameContainsKeywordsPredicate(Arrays.asList("CS", "ST")));
        assertParseSuccess(parser, "CS ST", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS \n \t ST  \t", expectedFindCommand);
    }

}
