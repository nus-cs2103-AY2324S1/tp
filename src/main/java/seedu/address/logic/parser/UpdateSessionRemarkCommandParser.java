package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION_REMARK;

import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateSessionRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.session.SessionNumber;
import seedu.address.model.session.SessionRemark;

/**
 * Parses input arguments and creates a new UpdateSessionRemarkCommand object.
 */
public class UpdateSessionRemarkCommandParser implements Parser<UpdateSessionRemarkCommand> {

    /**
     * Parses the given `args` and creates a new `UpdateSessionRemarkCommand` object.
     *
     * @param args The input arguments provided by the user.
     * @return A `UpdateSessionRemarkCommand` object.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public UpdateSessionRemarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSION, PREFIX_SESSION_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSION, PREFIX_SESSION_REMARK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, UpdateSessionRemarkCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SESSION, PREFIX_SESSION_REMARK);
        SessionNumber sessionNumber = ParserUtil.parseSessionNumber(argMultimap.getValue(PREFIX_SESSION).get());
        SessionRemark sessionRemark = ParserUtil.parseSessionRemark(argMultimap.getValue(PREFIX_SESSION_REMARK).get());

        return new UpdateSessionRemarkCommand(sessionNumber, sessionRemark);
    }

    /**
     * Checks if the specified prefixes are present in the given ArgumentMultimap.
     *
     * @param argumentMultimap The ArgumentMultimap to check for prefix presence.
     * @param prefixes The prefixes to be checked.
     * @return `true` if all specified prefixes are present, `false` otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
