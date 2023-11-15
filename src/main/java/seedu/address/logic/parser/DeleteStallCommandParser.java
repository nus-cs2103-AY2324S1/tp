package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStallCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new DeleteStallCommand object
 */
public class DeleteStallCommandParser implements Parser<DeleteStallCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStallCommand
     * and returns a DeleteStallCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteStallCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STALL);

        if (!arePrefixesPresent(argMultimap, PREFIX_STALL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStallCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STALL, PREFIX_ITEM);
        Index stallIndex;
        try {
            stallIndex = ParserUtil.parseStallIndex(argMultimap.getValue(PREFIX_STALL).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_STALL_DISPLAYED_INDEX, DeleteStallCommand.MESSAGE_USAGE), pe);
        }
        return new DeleteStallCommand(stallIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
