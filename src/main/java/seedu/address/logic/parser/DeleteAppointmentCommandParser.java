package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteAppointmentCommandParser implements Parser<DeleteAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAppointmentCommand parse(String args) throws ParseException {
        try {
            long appointmentId = Long.parseLong(args.trim());
            return new DeleteAppointmentCommand(appointmentId);
        } catch (NumberFormatException | NullPointerException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE), e);
        }
    }
}
