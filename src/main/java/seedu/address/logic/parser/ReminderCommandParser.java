package seedu.address.logic.parser;

import seedu.address.logic.commands.ReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReminderCommand object
 */
public class ReminderCommandParser implements Parser<ReminderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReminderCommand
     * and returns an ReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReminderCommand parse(String args) throws ParseException {
        return new ReminderCommand();
    }

}
