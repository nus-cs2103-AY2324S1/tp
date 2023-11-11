package seedu.address.logic.parser;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.RemindPredicate;

/**
 * Parses input arguments and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {

    private static final int VALID_DAYS_FROM = 0;
    private static final int VALID_DAYS_TO = 7305;
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

            if (parsedArgs < VALID_DAYS_FROM || parsedArgs > VALID_DAYS_TO) {
                throw new ParseException(String.format(Messages.MESSAGE_NOT_IN_RANGE, VALID_DAYS_FROM, VALID_DAYS_TO));
            }
            return new RemindCommand(new RemindPredicate(parsedArgs));
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_NOT_NUMBERS));
        }
    }
}
