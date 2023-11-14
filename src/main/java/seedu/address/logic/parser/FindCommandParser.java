package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.MESSAGE_EMPTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GROUPTAG);

        //duplicate parameters
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_GROUPTAG);

        // check if either n/ or g/ are present
        if ((!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty())
                && !arePrefixesPresent(argMultimap, PREFIX_GROUPTAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        // if n/ is present
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            String personName = argMultimap.getValue(PREFIX_NAME).get();
            // check if g/ is present
            if (arePrefixesPresent(argMultimap, PREFIX_GROUPTAG)) { // g/ present
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommand.MESSAGE_TWO_PARAMETERS));
            } else if (personName.trim().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_EMPTY_NAME));
            } else {
                String[] nameKeywords = personName.split("\\s+");
                return new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            }
        }

        // n/ not present, g/ should be present
        String groupName = argMultimap.getValue(PREFIX_GROUPTAG).get();
        return new FindGroupCommand(groupName);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
