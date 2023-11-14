package seedu.address.logic.parser.appointmentparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_ATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;

import java.util.Arrays;

import seedu.address.logic.commands.appointmentcommands.SortCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    private static final String[] ATTRIBUTES = {"time", "priority"};
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BY);

        boolean isAscending;

        try {
            isAscending = ParserUtil.parseIsAscending(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_BY).isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_BY);
        String attribute = argMultimap.getValue(PREFIX_BY).get();

        if (Arrays.stream(ATTRIBUTES).noneMatch(x -> x.equals(attribute))) {
            throw new ParseException(String.format(MESSAGE_INVALID_SORT_ATTRIBUTE,
                    Arrays.toString(ATTRIBUTES)));
        }

        return new SortCommand(isAscending, attribute);
    }
    public static String printAttributes() {
        return Arrays.toString(ATTRIBUTES);
    }

}
