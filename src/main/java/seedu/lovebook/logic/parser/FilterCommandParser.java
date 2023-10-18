package seedu.lovebook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CliSyntax.*;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;

import seedu.lovebook.logic.commands.FilterCommand;
import seedu.lovebook.logic.parser.exceptions.ParseException;
import seedu.lovebook.model.person.MetricContainsKeywordPredicate;

import java.util.ArrayList;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME,
                        PREFIX_HOROSCOPE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME,
                PREFIX_HOROSCOPE);

        String keyword = null;
        Prefix metric = null;
        ArrayList<MetricContainsKeywordPredicate> predicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_NAME).get();
            metric = new Prefix("name/");
            predicates.add(new MetricContainsKeywordPredicate(keyword, metric));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_AGE).get();
            metric = new Prefix("age/");
            predicates.add(new MetricContainsKeywordPredicate(keyword, metric));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_GENDER).get();
            metric = new Prefix("gender/");
            predicates.add(new MetricContainsKeywordPredicate(keyword, metric));
        }
        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_HEIGHT).get();
            metric = new Prefix("height/");
            predicates.add(new MetricContainsKeywordPredicate(keyword, metric));
        }
        if (argMultimap.getValue(PREFIX_INCOME).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_INCOME).get();
            metric = new Prefix("income/");
            predicates.add(new MetricContainsKeywordPredicate(keyword, metric));
        }
        if (argMultimap.getValue(PREFIX_HOROSCOPE).isPresent()) {
            keyword = argMultimap.getValue(PREFIX_HOROSCOPE).get();
            metric = new Prefix("horoscope/");
            predicates.add(new MetricContainsKeywordPredicate(keyword, metric));
        }
        if (metric == null || keyword.isEmpty()){
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        return new FilterCommand(predicates);
    }
}

