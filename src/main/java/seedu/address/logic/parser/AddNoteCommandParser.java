package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Note;

/**
 * Parses input arguments and creates a new NoteCommand object.
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    public static final String MESSAGE_EMPTY_NOTE = "NOTE_CONTENT cannot be empty!";

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        // Split based on space
        String[] splitArgs = args.trim().split("\\s", 2);

        // Empty args
        if (splitArgs[0].isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        // Only 1 arg
        if (splitArgs.length == 1) {
            // Assume that user follows command format so first argument by default is index.
            ParserUtil.parseIndex(splitArgs[0]);
            // If user keys in valid index, will then subsequently throw exception; notes cannot be whitespace/empty.
            throw new ParseException(MESSAGE_EMPTY_NOTE);
        }

        Index index = ParserUtil.parseIndex(splitArgs[0]);
        Note note = new Note(splitArgs[1]);

        return new AddNoteCommand(index, note);
    }
}
