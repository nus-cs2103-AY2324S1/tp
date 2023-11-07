package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.TrendCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TrendCommand instance.
 */
public class TrendCommandParser implements Parser<TrendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TrendCommand
     * and returns a TrendCommand object for execution.
     * @param args String representation of a year.
     * @return a TrendCommand instance.
     * @throws ParseException if the user input does not conform expected format.
     */
    public TrendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        args = args.trim();
        if (args.isEmpty() || !args.matches("^y/\\d{4}$")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrendCommand.MESSAGE_USAGE)
            );
        }

        return new TrendCommand(args.substring(2));
    }
}
