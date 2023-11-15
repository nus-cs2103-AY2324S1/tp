package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewStallCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewStallCommand object
 */
public class ViewStallCommandParser implements Parser<ViewStallCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewStallCommand
     * and returns a ViewStallCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewStallCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STALL);

        if (!arePrefixesPresent(argMultimap, PREFIX_STALL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStallCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STALL, PREFIX_ITEM);
        Index index;
        try {
            index = ParserUtil.parseStallIndex(argMultimap.getValue(PREFIX_STALL).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_STALL_DISPLAYED_INDEX, ViewStallCommand.MESSAGE_USAGE), pe);
        }
        return new ViewStallCommand(index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
