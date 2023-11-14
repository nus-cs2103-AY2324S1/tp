package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.lovebook.logic.commands.SortCommand;
import seedu.lovebook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */

public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE,
                PREFIX_HEIGHT, PREFIX_INCOME, PREFIX_HOROSCOPE, PREFIX_GENDER);
        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_AGE, PREFIX_HEIGHT, PREFIX_INCOME,
                PREFIX_HOROSCOPE);

        String sequence = null;
        Prefix metric = null;
        int prefixCount = 0;
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            prefixCount++;
            sequence = argumentMultimap.getValue(PREFIX_NAME).get();
            metric = new Prefix("name/");
        }
        if (argumentMultimap.getValue(PREFIX_AGE).isPresent()) {
            prefixCount++;
            sequence = argumentMultimap.getValue(PREFIX_AGE).get();
            metric = new Prefix("age/");
        }
        if (argumentMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            prefixCount++;
            sequence = argumentMultimap.getValue(PREFIX_HEIGHT).get();
            metric = new Prefix("height/");
        }
        if (argumentMultimap.getValue(PREFIX_INCOME).isPresent()) {
            prefixCount++;
            sequence = argumentMultimap.getValue(PREFIX_INCOME).get();
            metric = new Prefix("income/");
        }
        if (argumentMultimap.getValue(PREFIX_HOROSCOPE).isPresent()) {
            prefixCount++;
            sequence = argumentMultimap.getValue(PREFIX_HOROSCOPE).get();
            metric = new Prefix("horoscope/");
        }
        if (argumentMultimap.getValue(PREFIX_GENDER).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        if (metric == null || !sequence.equals(SortCommand.SEQUENCE_ASCENDING)
                && !sequence.equals(SortCommand.SEQUENCE_DESCENDING)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        System.out.println("prefixCount: " + prefixCount);
        if (prefixCount > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(metric, sequence);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommandParser)) {
            return false;
        }

        SortCommandParser otherSortCommandParser = (SortCommandParser) other;
        return true;
    }
}
