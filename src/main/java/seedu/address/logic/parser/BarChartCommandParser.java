package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.BarChartCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new BarChartCommand object
 */
public class BarChartCommandParser implements Parser<BarChartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BarChartCommand
     * and returns a BarChartCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BarChartCommand parse(String args) throws ParseException {
        requireNonNull(args);

        args = args.trim();
        if (args.matches("l/|s/|g/") && args.length() == 2) {
            return new BarChartCommand(args);
        } else if (args.matches("^d/\\d{4}$") && args.length() == 6) {
            String prefix = args.substring(0, 2);
            int year = Integer.parseInt(args.substring(2));
            return new BarChartCommand(prefix, year);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BarChartCommand.MESSAGE_USAGE));
        }
    }
}
