package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_METRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    private static Logger logger = LogsCenter.getLogger(FilterCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * @param args String of arguments
     * @return FilterCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_METRIC, PREFIX_VALUE);


        if (!(argMultimap.getValue(PREFIX_METRIC).isPresent() && argMultimap.getValue(PREFIX_VALUE).isPresent())) {
            throw new ParseException(String.format(FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand();
    }

}
