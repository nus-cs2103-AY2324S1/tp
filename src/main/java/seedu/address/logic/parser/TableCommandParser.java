package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.TableCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new TableCommand object
 */
public class TableCommandParser implements Parser<TableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TableCommand
     * and returns a TableCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TableCommand parse(String args) throws ParseException {
        requireNonNull(args);

        args = args.trim();
        if (args.matches("l/|s/|g/") && args.length() == 2) {
            return new TableCommand(args);
        } else if (args.matches("^d/\\d{4}$") && args.length() == 6) {
            String prefix = args.substring(0, 2);
            int year = Integer.parseInt(args.substring(2));
            return new TableCommand(prefix, year);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TableCommand.MESSAGE_USAGE));
        }
    }
}
