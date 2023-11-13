package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.commands.FindCommand.MESSAGE_TOO_LONG;
import static seedu.staffsnap.logic.commands.FindCommand.MESSAGE_WRONG_FORMAT;

import java.util.Arrays;

import seedu.staffsnap.logic.commands.FindCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input is empty or if the user input is not alphanumeric.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String alphanumericRegex = "[\\p{Alnum}][\\p{Alnum} ]*";

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        } else if (!trimmedArgs.matches(alphanumericRegex)) {
            throw new ParseException((
                    String.format(MESSAGE_WRONG_FORMAT, FindCommand.MESSAGE_USAGE)));
        } else if (trimmedArgs.length() > 55) {
            throw new ParseException((
                    String.format(MESSAGE_TOO_LONG, FindCommand.MESSAGE_USAGE)));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
