package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewContactCommand object
 */
public class ViewContactCommandParser implements Parser<ViewContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewContactCommand
     * and returns a ViewContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewContactCommand parse(String args) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndexes(args, ViewContactCommand.EXPECTED_INDEXES).get(0);
        } catch (ParseException pe) {
            throw new ParseException(ViewContactCommand.MESSAGE_USAGE, pe);
        }

        return new ViewContactCommand(index);
    }
}
