package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.lovebook.logic.commands.FilterCommand;
import seedu.lovebook.logic.parser.exceptions.ParseException;
import seedu.lovebook.model.person.MetricContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        String[] parts = trimmedArgs.split("/ ");
        String value = null;
        Prefix metric = null;

        if (parts.length == 2) {
            String prefix = parts[0].trim() + "/";
            metric = new Prefix(prefix);
            value = parts[1].trim();
        }
        return new FilterCommand(new MetricContainsKeywordPredicate(value, metric));
    }
}
