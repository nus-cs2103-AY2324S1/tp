package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetReminderCommand object
 */
public class SetReminderCommandParser implements Parser<SetReminderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SetReminderCommand
     * and returns a SetReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetReminderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetReminderCommand.MESSAGE_USAGE));
        }

        return new SetReminderCommand(trimmedArgs);
    }
}
