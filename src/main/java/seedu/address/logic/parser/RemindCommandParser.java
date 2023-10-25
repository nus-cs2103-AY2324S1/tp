package seedu.address.logic.parser;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RemindPredicate;

/**
 * Parses input arguments and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemindCommand
     * and returns a RemindCommand object for execution.
     */
    public RemindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
        }

        try {
            int parsedArgs = Integer.parseInt(trimmedArgs);
            if (parsedArgs < 0) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        Messages.MESSAGE_NOT_POSITIVE_NUMBER));
            }
            return new RemindCommand(new RemindPredicate(parsedArgs));
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_NOT_NUMBERS));
        }
    }
}
