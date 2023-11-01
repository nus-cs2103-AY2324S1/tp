package seedu.address.logic.parser;

import seedu.address.logic.commands.OvertimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATION;

public class OvertimeCommandParser implements Parser<OvertimeCommand>{
    public OvertimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OvertimeCommand.MESSAGE_USAGE));
        }


        Id id;
        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OvertimeCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT);

        if (argMultimap.getValue(PREFIX_OPERATION).isEmpty() || argMultimap.getValue(PREFIX_AMOUNT).isEmpty()) {
            throw new ParseException(OvertimeCommand.MISSING_OPERATION_AMOUNT);
        }

        String operation = argMultimap.getValue(PREFIX_OPERATION).get();
        OvertimeHours overtimeHoursToChange = ParserUtil.parseOvertimeHours(argMultimap.getValue(PREFIX_AMOUNT).get());

        return new OvertimeCommand(id, overtimeHoursToChange, operation);
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
