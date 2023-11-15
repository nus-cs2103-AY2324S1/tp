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
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCourseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCourseCommand expectedFindCourseCommand =
                new FindCourseCommand(new CourseContainsKeywordsPredicate(Arrays.asList("MA2001", "CS2030")));
        assertParseSuccess(parser, "MA2001 CS2030", expectedFindCourseCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n MA2001 \n \t CS2030  \t", expectedFindCourseCommand);
    }
}

