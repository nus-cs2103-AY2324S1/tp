package connexion.logic.parser;

import static connexion.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connexion.logic.parser.CliSyntax.PREFIX_NOTE;
import static java.util.Objects.requireNonNull;

import java.time.Clock;
import java.time.LocalDateTime;

import connexion.commons.core.index.Index;
import connexion.logic.commands.NoteCommand;
import connexion.logic.commands.NoteCommand.NoteDescriptor;
import connexion.logic.parser.exceptions.ParseException;
import connexion.model.person.LastModifiedDateTime;

/**
 * Parses input arguments and creates a new NoteCommand object
 */
public class NoteCommandParser implements ClockDependentParser<NoteCommand> {
    private Clock clock = Clock.systemDefaultZone();

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns an NoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE), pe);
        }

        if (!isNotePrefixPresent(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NOTE);

        NoteDescriptor noteDescriptor = new NoteDescriptor(
                ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get()),
                new LastModifiedDateTime(LocalDateTime.now(clock)));

        return new NoteCommand(index, noteDescriptor);
    }

    /**
     * Returns true if the prefix does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isNotePrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_NOTE).isPresent();
    }

    /**
     * To specify usage of a specific clock.
     *
     * @param clock The clock to use
     * @return an edited parser using the specified clock
     */
    @Override
    public NoteCommandParser withClock(Clock clock) {
        NoteCommandParser toReturn = new NoteCommandParser();
        toReturn.clock = clock;
        return toReturn;
    }
}
