package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.application.logic.commands.ListCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.FieldComparator;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand(new FieldComparator(FieldComparator.EMPTY_COMPARATOR_SPECIFIER));
        }

        String[] splitArgs = trimmedArgs.split("\\s+");
        if (splitArgs.length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String specifier = splitArgs[0];
        if (!(FieldComparator.isValidSpecifier(specifier))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_INVALID_SPECIFIER));
        }

        return new ListCommand(new FieldComparator(specifier));
    }
}
