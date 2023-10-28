package seedu.address.logic.parser.personparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.personcommands.FindIllnessCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IllnessContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindIllnessCommand object
 */
public class FindIllnessCommandParser implements Parser<FindIllnessCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindIllnessCommand
     * and returns a FindIllnessCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindIllnessCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIllnessCommand.MESSAGE_USAGE));
        }

        String[] illnessKeywords = trimmedArgs.split("\\s+");

        return new FindIllnessCommand(new IllnessContainsKeywordsPredicate(Arrays.asList(illnessKeywords)));
    }
}
