package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemoveReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveReminderCommand object
 */
public class RemoveReminderCommandParser implements Parser<RemoveReminderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveReminderCommand
     * and returns a RemoveReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveReminderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveReminderCommand.MESSAGE_USAGE));
        }

        return new RemoveReminderCommand(trimmedArgs);
    }
}

