package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_NOTE;
import static java.util.Objects.requireNonNull;

import connectify.commons.core.index.Index;
import connectify.commons.exceptions.IllegalValueException;
import connectify.logic.commands.CompanyNoteCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.company.CompanyNote;

/**
 * Parses input arguments and creates a new {@code NoteCommand} object
 */
public class CompanyNoteCommandParser implements Parser<CompanyNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code CompanyNoteCommand}
     * and returns a {@code CompanyNoteCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CompanyNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTE);

        Index index;
        try {
            index = ParserCompanyUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CompanyNoteCommand.MESSAGE_USAGE), ive);
        }

        String note = argMultimap.getValue(PREFIX_NOTE).orElse("");

        return new CompanyNoteCommand(index, new CompanyNote(note));
    }
}
