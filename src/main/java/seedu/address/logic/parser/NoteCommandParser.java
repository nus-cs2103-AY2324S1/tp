package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Objects;

import seedu.address.annotation.Nullable;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Note;



/**
 * Parses a user arguments string to construct a NoteCommand.
 */
public class NoteCommandParser implements Parser<NoteCommand> {
    /**
     * Returns a NoteCommand based on the specified user arguments.
     *
     * @throws ParseException If arguments don't conform to expected format.
     */
    public NoteCommand parse(@Nullable String arguments) throws ParseException {
        Objects.requireNonNull(arguments);
        ArgumentMultimap map = ArgumentTokenizer.tokenize(
            arguments,
            CliSyntax.PREFIX_NOTE
        );

        Index index;
        try {
            index = ParserUtil.parseIndex(map.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(
                String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteCommand.MESSAGE_USAGE
                ),
                e
            );
        }

        String noteString = map.getValue(CliSyntax.PREFIX_NOTE).orElse("");
        Note note = new Note(noteString);

        return new NoteCommand(index, note);
    }
}
