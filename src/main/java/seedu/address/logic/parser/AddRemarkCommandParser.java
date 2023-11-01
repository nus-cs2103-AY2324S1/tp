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
 * Parses input arguments and creates a new AddRemarkCommand object
 */
public class AddRemarkCommandParser implements Parser<AddRemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddLeaveCommand
     * and returns a AddLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_REMARK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRemarkCommand.MESSAGE_USAGE));
        }

        Id id;
        Remark remark;

        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRemarkCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_REMARK).isEmpty()) {
            throw new ParseException(AddRemarkCommand.MISSING_REMARK);
        }

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
            return new AddRemarkCommand(id, remark);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRemarkCommand.MESSAGE_USAGE));
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
