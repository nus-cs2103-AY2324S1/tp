package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Id;

/**
 * Parses input arguments and creates a new EditLeaveCommand object
 */
public class EditLeaveCommandParser implements Parser<EditLeaveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditLeaveCommand
     * and returns an EditLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLeaveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_OLD, PREFIX_NEW);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_OLD, PREFIX_NEW)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLeaveCommand.MESSAGE_USAGE));
        }

        Id id;
        LocalDate oldDate;
        LocalDate newDate;

        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLeaveCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_OLD).isEmpty() || argMultimap.getValue(PREFIX_NEW).isEmpty()) {
            throw new ParseException(EditLeaveCommand.MISSING_DATE);
        }

        if (argMultimap.getValue(PREFIX_OLD).isPresent() && argMultimap.getValue(PREFIX_NEW).isPresent()) {
            oldDate = ParserUtil.parseLeaveDate(argMultimap.getValue(PREFIX_OLD).get());
            newDate = ParserUtil.parseLeaveDate(argMultimap.getValue(PREFIX_NEW).get());
            return new EditLeaveCommand(id, oldDate, newDate);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLeaveCommand.MESSAGE_USAGE));
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
