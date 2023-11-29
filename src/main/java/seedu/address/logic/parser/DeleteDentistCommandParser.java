package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteDentistCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDentistCommand object
 */
public class DeleteDentistCommandParser implements Parser<DeleteDentistCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDentistCommand
     * and returns a DeleteDentistCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteDentistCommand parse(String args) throws ParseException {
        try {
            long dentistId = Long.parseLong(args.trim());
            return new DeleteDentistCommand(dentistId);
        } catch (NumberFormatException | NullPointerException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDentistCommand.MESSAGE_USAGE), e);
        }
    }
}
