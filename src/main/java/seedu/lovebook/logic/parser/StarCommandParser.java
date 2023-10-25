package seedu.lovebook.logic.parser;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.logic.commands.StarCommand;
import seedu.lovebook.logic.parser.exceptions.ParseException;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class StarCommandParser implements Parser<StarCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the StarCommand
     * and returns a StarCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StarCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new StarCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE), pe);
        }
    }
}
