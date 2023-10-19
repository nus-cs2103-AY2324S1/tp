package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StatusContainsKeywordsPredicate;


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
        List<String> nameKeywords = new ArrayList<>();
        List<String> statusKeywords = new ArrayList<>();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATUS);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STATUS);
        if (!(arePrefixesPresent(argMultimap, PREFIX_NAME) || arePrefixesPresent(argMultimap, PREFIX_STATUS))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            setKeywords(nameKeywords, statusKeywords, argMultimap, PREFIX_NAME);
        }
        if (arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
            setKeywords(nameKeywords, statusKeywords, argMultimap, PREFIX_STATUS);
        }


        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(statusKeywords);

        return new FindCommand(getPredicatesList(nameKeywords, statusKeywords, namePredicate, statusPredicate));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private List<Predicate<Person>> getPredicatesList(List<String> nameKeywords,
                                                      List<String> statusKeywords,
                                                      NameContainsKeywordsPredicate namePredicate,
                                                      StatusContainsKeywordsPredicate statusPredicate) {
        List<Predicate<Person>> predicatesList = new ArrayList<>() {{
                if (!nameKeywords.isEmpty()) {
                    add(namePredicate);
                }
                if (!statusKeywords.isEmpty()) {
                    add(statusPredicate);
                }
            }};

        return predicatesList;
    }

    private void setKeywords(List<String> nameKeywords,
                             List<String> statusKeywords,
                             ArgumentMultimap argMultimap,
                             Prefix prefix) throws ParseException {
        if (prefix.equals(PREFIX_NAME)) {
            List<String> keywords = ParserUtil.parseSearchNameParams(argMultimap.getAllValues(PREFIX_NAME));
            for (String keyword : keywords) {
                nameKeywords.add(keyword);
            }
        }
        if (prefix.equals(PREFIX_STATUS)) {
            List<String> keywords = ParserUtil.parseSearchStatusParams(argMultimap.getAllValues(PREFIX_STATUS));
            for (String keyword : keywords) {
                statusKeywords.add(keyword);
            }
        }
    }
}
