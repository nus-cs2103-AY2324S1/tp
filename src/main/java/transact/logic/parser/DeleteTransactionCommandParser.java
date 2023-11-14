package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import transact.logic.commands.DeleteTransactionCommand;
import transact.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteStaffCommand object
 */
public class DeleteTransactionCommandParser implements Parser<DeleteTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteTransactionCommand
     * and returns a DeleteTransactionCommand object for execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public DeleteTransactionCommand parse(String args) throws ParseException {
        try {
            Integer transactionId = Integer.parseInt(args.trim());
            return new DeleteTransactionCommand(transactionId);
        } catch (NumberFormatException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTransactionCommand.MESSAGE_USAGE), pe);
        }
    }

}
