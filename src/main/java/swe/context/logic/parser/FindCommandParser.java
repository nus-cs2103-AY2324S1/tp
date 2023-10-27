package swe.context.logic.parser;

import java.util.Arrays;

import swe.context.logic.Messages;
import swe.context.logic.commands.FindCommand;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.contact.NameContainsKeywordsPredicate;



/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Returns a {@link FindCommand} from parsing the specified arguments.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                Messages.commandInvalidFormat(FindCommand.MESSAGE_USAGE)
            );
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
