package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.predicate.IdContainsKeywordsPredicate;
import seedu.address.model.predicate.NameContainsKeywordsPredicate;

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
        String[] keywords;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        if ((!arePrefixesPresent(argMultimap, PREFIX_NAME) && !arePrefixesPresent(argMultimap, PREFIX_ID))
                || (argMultimap.getValue(PREFIX_NAME).isEmpty() && argMultimap.getValue(PREFIX_ID).isEmpty())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keywords = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else {
            keywords = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()).value.split("\\s+");
            return new FindCommand(new IdContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
