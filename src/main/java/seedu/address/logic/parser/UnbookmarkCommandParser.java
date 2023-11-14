package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnbookmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new UnookmarkCommand object.
 */
public class UnbookmarkCommandParser implements Parser<UnbookmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnbookmarkCommand
     * and returns a UnbookmarkCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public UnbookmarkCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnbookmarkCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnbookmarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
