package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TeachingModPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Prefix prefix = new Prefix(trimmedArgs.substring(0, 2));
        FindCommand command;
        String[] nameKeywords;
        if (CliSyntax.PREFIX_MOD.equals(prefix)) {
            nameKeywords = trimmedArgs.substring(2).split("\\s+");
            command = new FindCommand(new TeachingModPredicate(Arrays.asList(nameKeywords)));
        } else {
            nameKeywords = trimmedArgs.split("\\s+");
            command = new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        return command;
    }

}
