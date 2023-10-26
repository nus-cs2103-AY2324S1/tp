package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;

import seedu.address.logic.commands.CourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;

/**
 * Parses input arguments and creates a new CourseCommand object
 */
public class CourseCommandParser implements Parser<CourseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CourseCommand
     * and returns a CourseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CourseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COURSE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COURSE);

        if (argMultimap.getValue(PREFIX_COURSE).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CourseCommand.MESSAGE_USAGE) + "\n"
                            + "Available Courses: " + UniqueCourseList.COURSE_LIST);
        }

        Course course = ParserUtil.parseCourse(argMultimap.getValue(PREFIX_COURSE).get());
        return new CourseCommand(course);
    }

}
