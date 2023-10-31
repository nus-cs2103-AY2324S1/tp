package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.HourCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HourCommand object
 */
public class HourCommandParser implements Parser<HourCommand> {

    /**
     * Parses the given user input to create a new HourCommand object.
     *
     * @param args The user input representing the number of hours to add or subtract from work hours.
     * @return A new HourCommand object with the parsed input.
     * @throws ParseException If the input format is invalid or cannot be parsed as an integer.
     */
    public HourCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            Integer duration = Integer.parseInt(trimmedArgs);
            return new HourCommand(duration);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HourCommand.MESSAGE_USAGE), nfe);
        }
    }
}
