package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import transact.logic.commands.DeleteStaffCommand;
import transact.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteStaffCommand object
 */
public class DeleteStaffCommandParser implements Parser<DeleteStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteStaffCommand
     * and returns a DeleteStaffCommand object for execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public DeleteStaffCommand parse(String args) throws ParseException {
        try {
            Integer personId = Integer.parseInt(args.trim());
            return new DeleteStaffCommand(personId);
        } catch (NumberFormatException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE), pe);
        }
    }

}
