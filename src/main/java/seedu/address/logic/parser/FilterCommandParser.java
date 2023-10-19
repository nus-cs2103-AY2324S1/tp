package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.ParserUtil.FilterOperation;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns an FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIALNUMBER, PREFIX_COURSETUTORIAL);

        FilterOperation operation;

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        try {
            operation = ParserUtil.parseFilterOperation(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE), pe);
        }

        if (operation != FilterOperation.CLEAR) {
            if ((!arePrefixesPresent(argMultimap, PREFIX_COURSETUTORIAL))
                || argMultimap.getValue(PREFIX_COURSETUTORIAL).isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTORIALNUMBER, PREFIX_COURSETUTORIAL);

        return new FilterCommand(operation,
                argMultimap.getValue(PREFIX_COURSETUTORIAL),
                argMultimap.getValue(PREFIX_TUTORIALNUMBER));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
