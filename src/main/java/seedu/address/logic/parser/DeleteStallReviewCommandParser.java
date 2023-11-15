package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStallReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteStallReviewCommand object.
 */
public class DeleteStallReviewCommandParser implements Parser<DeleteStallReviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStallReviewCommand
     * and returns a DeleteStallReviewCommand object for execution.
     *
     * @param args The user input string.
     * @return A DeleteStallReviewCommand object.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteStallReviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STALL);

        if (!arePrefixesPresent(argMultimap, PREFIX_STALL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteStallReviewCommand.MESSAGE_USAGE));
        }

        try {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STALL);
            Index stallIndex = ParserUtil.parseStallIndex(argMultimap.getValue(PREFIX_STALL).get());
            return new DeleteStallReviewCommand(stallIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_STALL_DISPLAYED_INDEX, DeleteStallReviewCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap The argument multimap to check.
     * @param prefixes         The prefixes to check for presence.
     * @return True if all prefixes are present, false otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
