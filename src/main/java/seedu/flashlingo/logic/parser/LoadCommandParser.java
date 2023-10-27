package seedu.flashlingo.logic.parser;

import seedu.flashlingo.logic.commands.LoadCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;

/**
 * Parses input argument, the file path, and creates a new LoadCommand object.
 */
public class LoadCommandParser implements Parser<LoadCommand> {

    @Override
    public LoadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(LoadCommand.MESSAGE_USAGE));
        }
        return new LoadCommand(trimmedArgs);
    }
}
