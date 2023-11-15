package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CourseContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCourseCommand object
 */
public class FindCourseCommandParser implements Parser<FindCourseCommand> {

    @Override
    public FindCourseCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCourseCommand.MESSAGE_USAGE));
        }

        List<String> courseKeywords = Arrays.asList(trimmedArgs.split("\\s+"));

        return new FindCourseCommand(new CourseContainsKeywordsPredicate(courseKeywords));
    }
}


