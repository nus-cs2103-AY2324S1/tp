package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Id;
import seedu.address.model.remark.Remark;

/**
 * Parses input arguments and creates a new AddRemarkCommand object.
 */
public class AddRemarkCommandParser implements Parser<AddRemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddRemarkCommand
     * and returns a AddRemarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_REMARK);

        areValidPrefixes(argMultimap);

        Id id;
        Remark remark;
        id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
            return new AddRemarkCommand(id, remark);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRemarkCommand.MESSAGE_USAGE));
    }

    /**
     * Checks validity of prefixes.
     *
     * @param argMultimap ArgumentMultimap to be used
     * @throws ParseException If prefixes are empty or repeated
     */
    private void areValidPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_REMARK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRemarkCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_REMARK);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
