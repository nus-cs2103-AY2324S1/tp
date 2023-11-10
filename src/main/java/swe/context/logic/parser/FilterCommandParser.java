package swe.context.logic.parser;

import swe.context.logic.Messages;
import swe.context.logic.commands.FilterCommand;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.contact.ContainsTagPredicate;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Returns a {@link FilterCommand} from parsing the specified arguments.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                Messages.commandInvalidFormat(FilterCommand.MESSAGE_USAGE)
            );
        }

        return new FilterCommand(new ContainsTagPredicate(trimmedArgs));
    }
}
