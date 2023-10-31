package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static transact.logic.parser.CliSyntax.PREFIX_AFTER_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_BEFORE_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_BY_PERSON;
import static transact.logic.parser.CliSyntax.PREFIX_DESCRIPTION_HAS;
import static transact.logic.parser.CliSyntax.PREFIX_LESS_THAN_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_MORE_THAN_AMOUNT;

import java.util.List;
import java.util.Optional;

import transact.logic.commands.FilterCommand;
import transact.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION_HAS,
                PREFIX_AFTER_DATE, PREFIX_BEFORE_DATE, PREFIX_MORE_THAN_AMOUNT, PREFIX_LESS_THAN_AMOUNT,
                PREFIX_BY_PERSON);
        argMultimap.verifyNotEmpty(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DESCRIPTION_HAS, PREFIX_AFTER_DATE,
                PREFIX_BEFORE_DATE, PREFIX_MORE_THAN_AMOUNT, PREFIX_LESS_THAN_AMOUNT,
                PREFIX_BY_PERSON);

        FilterCommand.FilterConditions filterConditions = new FilterCommand.FilterConditions();

        argMultimap.getValue(PREFIX_DESCRIPTION_HAS).ifPresent(
                string -> filterConditions.setDescriptionHas(List.of(string.split(" "))));

        Optional<String> afterDate = argMultimap.getValue(PREFIX_AFTER_DATE);
        if (afterDate.isPresent()) {
            filterConditions.setAfterDate(ParserUtil.parseDate(afterDate.get()));
        }

        Optional<String> beforeDate = argMultimap.getValue(PREFIX_BEFORE_DATE);
        if (beforeDate.isPresent()) {
            filterConditions.setBeforeDate(ParserUtil.parseDate(beforeDate.get()));
        }

        Optional<String> moreThanAmount = argMultimap.getValue(PREFIX_MORE_THAN_AMOUNT);
        if (moreThanAmount.isPresent()) {
            filterConditions.setMoreThanAmount(ParserUtil.parseAmount(moreThanAmount.get()));
        }

        Optional<String> lessThanAmount = argMultimap.getValue(PREFIX_LESS_THAN_AMOUNT);
        if (lessThanAmount.isPresent()) {
            filterConditions.setLessThanAmount(ParserUtil.parseAmount(lessThanAmount.get()));
        }

        Optional<String> byPersonId = argMultimap.getValue(PREFIX_BY_PERSON);
        if (byPersonId.isPresent()) {
            filterConditions.setByPersonId(ParserUtil.parsePersonId(byPersonId.get()));
        }

        return new FilterCommand(filterConditions);
    }
}
