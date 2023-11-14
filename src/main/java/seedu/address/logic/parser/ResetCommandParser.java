package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

import java.util.stream.Stream;

import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResetCommand object
 */
public class ResetCommandParser implements Parser<ResetCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ResetCommand
     * and returns a ResetCommand object for execution.
     *
     * @throws ParseException if the user does not conform the expected format
     */
    public ResetCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FIELD);
        areValidPrefixes(argMultimap);

        String field = argMultimap.getValue(PREFIX_FIELD).orElse("");

        return new ResetCommand(field);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Checks validity of prefixes.
     *
     * @param argMultimap ArgumentMultimap to be used
     * @throws ParseException If prefixes are empty or repeated
     */
    private void areValidPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_FIELD) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FIELD);
    }
}
