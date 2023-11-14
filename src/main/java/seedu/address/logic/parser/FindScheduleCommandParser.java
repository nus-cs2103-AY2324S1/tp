package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.TutorNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTutorCommand object
 */
public class FindScheduleCommandParser implements Parser<FindScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTutorCommand
     * and returns a FindTutorCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindScheduleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindScheduleCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindScheduleCommand(new TutorNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
