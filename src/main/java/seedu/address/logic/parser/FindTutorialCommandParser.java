package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

/**
 * Parses the FindTutorialCommand
 */
public class FindTutorialCommandParser implements Parser<FindTutorialCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindTutorialCommand
     * and returns a FindTutorialCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTutorialCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTutorialCommand.MESSAGE_USAGE));
        }

        String[] tutorialKeywords = trimmedArgs.split("\\s+");

        return new FindTutorialCommand(new TutorialContainsKeywordsPredicate(Arrays.asList(tutorialKeywords)));
    }

}
