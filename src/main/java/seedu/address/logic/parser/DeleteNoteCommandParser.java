package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;

import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.NoteID;
import seedu.address.model.person.ContactID;

/**
 * Parses input arguments and creates a new DeleteNoteCommand object
 */
public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {
    @Override
    public DeleteNoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_ID, PREFIX_NOTE_ID);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PERSON_ID, PREFIX_NOTE_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PERSON_ID, PREFIX_NOTE_ID);

        ContactID contactId = ParserUtil.parseContactID(argMultimap.getValue(PREFIX_PERSON_ID).get());
        NoteID noteId = ParserUtil.parseNoteID(argMultimap.getValue(PREFIX_NOTE_ID).get());

        return new DeleteNoteCommand(contactId, noteId);
    }
}
