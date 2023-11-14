//@@author tiongjjyi
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.codesphere.logic.commands.ResetCourseCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResetCommand object
 */
public class ResetCourseCommandParser implements Parser<ResetCourseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ResetCourseCommand
     * and returns a ResetCourseCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format
     */
    public ResetCourseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (argMultimap.getPreamble().isEmpty()) {
            return new ResetCourseCommand();
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetCourseCommand.MESSAGE_USAGE));
    }
}
