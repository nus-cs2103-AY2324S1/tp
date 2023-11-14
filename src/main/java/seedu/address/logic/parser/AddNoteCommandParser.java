package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;

import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteContent;
import seedu.address.model.note.NoteTitle;
import seedu.address.model.person.ContactID;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {
    @Override
    public AddNoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_ID,
                PREFIX_NOTE_TITLE, PREFIX_NOTE_CONTENT);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PERSON_ID, PREFIX_NOTE_TITLE, PREFIX_NOTE_CONTENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PERSON_ID, PREFIX_NOTE_TITLE, PREFIX_NOTE_CONTENT);

        NoteTitle noteTitle = ParserUtil.parseNoteTitle(argMultimap.getValue(PREFIX_NOTE_TITLE).get());
        NoteContent noteContent = ParserUtil.parseNoteContent(argMultimap.getValue(PREFIX_NOTE_CONTENT).get());
        Note newNote = new Note(noteTitle, noteContent);
        ContactID contactId = ParserUtil.parseContactID(argMultimap.getValue(PREFIX_PERSON_ID).get());

        return new AddNoteCommand(contactId, newNote);
    }
}
