package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.ListLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListLeaveCommand object
 */
public class ListLeaveCommandParser implements Parser<ListLeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListLeaveCommand
     * and returns a ListLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListLeaveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ON);
        areValidPrefixes(argMultimap);

        String dateToParse = argMultimap.getValue(PREFIX_ON).get();

        if (dateToParse.isEmpty()) {
            throw new ParseException(ListLeaveCommand.MISSING_DATE);
        }

        LocalDate focusedDate;
        focusedDate = ParserUtil.parseDate(dateToParse);
        return new ListLeaveCommand(focusedDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Checks validity of prefixes.
     *
     * @param argMultimap ArgumentMultimap to be used
     * @throws ParseException If prefixes are empty or repeated
     */
    private void areValidPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_ON) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListLeaveCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ON);
    }
}
