package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Id;

/** Parses input arguments and creates a new DeleteLeaveCommand object */
public class DeleteLeaveCommandParser implements Parser<DeleteLeaveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLeaveCommand
     * and returns a DeleteLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLeaveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_FROM, PREFIX_TO);
        areValidPrefixes(argMultimap);

        Id id;
        id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());

        if (argMultimap.getValue(PREFIX_FROM).isPresent() && argMultimap.getValue(PREFIX_TO).isPresent()) {
            LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_FROM).get());
            LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TO).get());
            checkDateOrder(startDate, endDate);
            return new DeleteLeaveCommand(id, startDate, endDate);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
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
        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_FROM, PREFIX_TO)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_FROM, PREFIX_TO);
    }

    private static void checkDateOrder(LocalDate startDate, LocalDate endDate) throws ParseException {
        if (startDate.isAfter(endDate)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_INVALID_DATE_ORDER));
        }
    }
}
