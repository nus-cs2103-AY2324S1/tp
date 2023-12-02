package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_PREFIX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.predicates.ApplicationStatusPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
    * Parses the given {@code String} of arguments in the context of the FilterCommand
    * and returns a FilterCommand object for execution.
    */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        // Checks for empty text after filter word
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_PREFIX, FilterCommand.MESSAGE_USAGE));
        }

        // Checks for missing prefixes after filter word
        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        // Checks for duplicate status prefixes
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STATUS);

        ApplicationStatus applicationStatus;
        ApplicationStatusPredicate predicate;

        applicationStatus = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        predicate = new ApplicationStatusPredicate(applicationStatus);
        return new FilterCommand(applicationStatus, predicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
