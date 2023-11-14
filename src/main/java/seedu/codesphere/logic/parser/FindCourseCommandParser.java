//@@author tiongjjyi
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.codesphere.logic.commands.FindCourseCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.course.CourseNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCourseCommand object
 */
public class FindCourseCommandParser implements Parser<FindCourseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCourseCommand
     * and returns a FindCourseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCourseCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCourseCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindCourseCommand(new CourseNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
