package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeDuration;

import java.util.stream.Stream;

import seedu.address.logic.commands.finance.FilterTimeDueCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Duration;
import seedu.address.model.finance.TimeDueBetweenPredicate;

/**
 * Parses input arguments and creates a new FilterTimeDueCommand object.
 */
public class FilterTimeDueCommandParser implements Parser<FilterTimeDueCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTimeDueCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TIME_START, PREFIX_TIME_END);
        if (!arePrefixesPresent(argMultimap, PREFIX_TIME_START, PREFIX_TIME_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTimeDueCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TIME_START, PREFIX_TIME_END);
        Duration duration = parseDateTimeDuration(
                argMultimap.getValue(PREFIX_TIME_START).orElse("now"),
                argMultimap.getValue(PREFIX_TIME_END).orElse("now"));

        return new FilterTimeDueCommand(new TimeDueBetweenPredicate(duration));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
