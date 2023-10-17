package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveNoteCommand object.
 */
public class RemoveNoteCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveNoteCommand
     * and returns a RemoveNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RemoveNoteCommand parse(String args) throws ParseException {
        try {
            // Split based on space
            String[] splitArgs = args.trim().split("\\s");

            if (splitArgs.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveNoteCommand.MESSAGE_USAGE));
            }

            Index indexPerson = ParserUtil.parseIndex(splitArgs[0]);
            Index indexNote = ParserUtil.parseIndex(splitArgs[1]);

            return new RemoveNoteCommand(indexPerson, indexNote);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}
