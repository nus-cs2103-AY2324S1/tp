package seedu.flashlingo.logic.parser;

import seedu.flashlingo.logic.commands.LanguageCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.flashcard.WordLanguagePredicate;

/**
 * Parses input arguments and creates a new LanguageCommand object
 */
public class LanguageCommandParser implements Parser<LanguageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LanguageCommand
     * and returns a LanguageCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LanguageCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new LanguageCommand(new WordLanguagePredicate(trimmedArgs));
        }

        try {
            return new LanguageCommand(new WordLanguagePredicate(trimmedArgs));
        } catch (IllegalArgumentException iae) {
            throw new ParseException(iae.getMessage());
        }
    }

}
