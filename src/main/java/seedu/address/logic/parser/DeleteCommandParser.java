package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.StatusContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        // Try index-based deletion first
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            // Ignore parsing exception and continue
        }

        // If not delete by index
        List<String> statusKeywords = new ArrayList<>();
        List<String> tagKeywords = new ArrayList<>();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS, PREFIX_TAG);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STATUS, PREFIX_TAG);

        if (!(arePrefixesPresent(argMultimap, PREFIX_STATUS) || arePrefixesPresent(argMultimap, PREFIX_TAG))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
            setKeywords(statusKeywords, tagKeywords, argMultimap, PREFIX_STATUS);
        }
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            setKeywords(statusKeywords, tagKeywords, argMultimap, PREFIX_TAG);
        }

        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(statusKeywords);
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tagKeywords);
        return new DeleteCommand(getPredicatesList(statusKeywords, tagKeywords, statusPredicate, tagPredicate));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private List<Predicate<Person>> getPredicatesList(List<String> statusKeywords,
                                                      List<String> tagKeywords,
                                                      StatusContainsKeywordsPredicate statusPredicate,
                                                      TagContainsKeywordsPredicate tagPredicate) {
        List<Predicate<Person>> predicatesList = new ArrayList<>() {{
                if (!statusKeywords.isEmpty()) {
                    add(statusPredicate);
                }
                if (!tagKeywords.isEmpty()) {
                    add(tagPredicate);
                }
            }};

        return predicatesList;
    }

    private void setKeywords(List<String> statusKeywords,
                             List<String> tagKeywords,
                             ArgumentMultimap argMultimap,
                             Prefix prefix) throws ParseException {
        if (prefix.equals(PREFIX_STATUS)) {
            List<String> keywords = ParserUtil.parseSinglePrefixStatus(argMultimap.getAllValues(PREFIX_STATUS));
            for (String keyword : keywords) {
                statusKeywords.add(keyword);
            }
        }
        if (prefix.equals(PREFIX_TAG)) {
            List<String> keywords = ParserUtil.parseSinglePrefixTags(argMultimap.getAllValues(PREFIX_TAG));
            for (String keyword : keywords) {
                tagKeywords.add(keyword);
            }
        }
    }
}
