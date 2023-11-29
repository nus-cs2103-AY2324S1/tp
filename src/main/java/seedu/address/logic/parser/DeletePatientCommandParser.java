package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePatientCommand object
 */
public class DeletePatientCommandParser implements Parser<DeletePatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePatientCommand
     * and returns a DeletePatientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeletePatientCommand parse(String args) throws ParseException {
        try {
            long patientId = Long.parseLong(args.trim());
            return new DeletePatientCommand(patientId);
        } catch (NumberFormatException | NullPointerException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE), e);
        }
    }
}



