package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.ParserUtil.parseCourse;

import seedu.address.logic.commands.TeachCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;

/**
 * Parses input arguments and creates a new TeachCommand object.
 */
public class TeachCommandParser implements Parser<TeachCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TeachCommand
     * and returns a TeachCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TeachCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COURSE);


        if (argMultimap.getValue(PREFIX_COURSE).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TeachCommand.MESSAGE_USAGE));
        }

        Course course = parseCourse(argMultimap.getValue(PREFIX_COURSE).get());

        return new TeachCommand(course);
    }
}
