package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListTodayCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListTodayCommand object
 */
public class ListTodayCommandParser implements Parser<ListTodayCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListTodayCommand
     * and returns a ListTodayCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTodayCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTodayCommand.MESSAGE_USAGE));
        }

        return new ListTodayCommand();
    }
}
