package seedu.address.logic.parser.internship.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.internship.task.FindInternshipTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.task.InternshipTaskNameContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindInternshipTaskCommand object
 */
public class FindInternshipTaskParser implements Parser<FindInternshipTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInternshipTaskCommand
     * and returns a FindInternshipTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInternshipTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInternshipTaskCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindInternshipTaskCommand(new InternshipTaskNameContainsKeywordsPredicate(
                Arrays.asList(nameKeywords)));
    }
}
