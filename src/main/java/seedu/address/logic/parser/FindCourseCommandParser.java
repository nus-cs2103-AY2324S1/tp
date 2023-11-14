package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CourseContainsKeywordsPredicate;

/**
 * Parses the FindCourseCommand
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

        String[] courseKeywords = trimmedArgs.split("\\s+");

        return new FindCourseCommand(new CourseContainsKeywordsPredicate(Arrays.asList(courseKeywords)));
    }

}
