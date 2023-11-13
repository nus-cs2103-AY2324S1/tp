package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteAttendanceCommand object
 */
public class DeleteAttendanceCommandParser implements Parser<DeleteAttendanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AttendanceCommand
     * and returns an AttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE);

        if (!isPrefixPresent(argMultimap, PREFIX_ATTENDANCE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteAttendanceCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ATTENDANCE);

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Tag toAdd = ParserUtil.parseTag(argMultimap.getValue(PREFIX_ATTENDANCE).get());

        return new DeleteAttendanceCommand(index, toAdd);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
