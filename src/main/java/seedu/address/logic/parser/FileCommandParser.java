package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FileCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FileCommand object
 */
public class FileCommandParser implements Parser<FileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FileCommand
     * and returns a FileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FileCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FileCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FileCommand.MESSAGE_USAGE), pe);
        }
    }

}
