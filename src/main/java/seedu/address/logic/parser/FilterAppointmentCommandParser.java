package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterAppointmentCommandParser object.
 */
public class FilterAppointmentCommandParser implements Parser<FilterAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of FilterAppointmentCommand
     * and returns a FilterAppointmentCommand object for execution.
     *
     * @param args The string of arguments to be parsed.
     * @return A FilterAppointmentCommand
     * @throws ParseException If the user input does not confrom to the expected format.
     */
    public FilterAppointmentCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterAppointmentCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmedArgs.split("\\s+");

        if (parts.length == 2) {
            try {
                long id = Long.parseLong(parts[1]);
                return new FilterAppointmentCommand(parts[0], id);
            } catch (NumberFormatException e) {
                throw new ParseException("ID should be a valid number");
            }
        } else {
            throw new ParseException(FilterAppointmentCommand.INVALID_INPUTS);
        }
    }
}
