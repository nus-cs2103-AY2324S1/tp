package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code MarkAttendanceCommand} object
 */
public class MarkAttendanceParser implements Parser<MarkAttendanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code MarkAttendanceCommand}
     * and returns a {@code MarkAttendanceCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL, PREFIX_PARTICIPATION_STATUS);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TUTORIAL, PREFIX_PARTICIPATION_STATUS)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAttendanceCommand.MESSAGE_USAGE), ive);
        }

        int tutorial = ParserUtil.parseTutorial(argMultimap.getValue(PREFIX_TUTORIAL).get());

        String status = ParserUtil.parseParticipationStatus(argMultimap.getValue(PREFIX_PARTICIPATION_STATUS).get());

        return new MarkAttendanceCommand(index, Index.fromOneBased(tutorial), status);
    }
}
