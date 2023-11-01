package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OvertimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.OvertimeHours;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.*;

public class OvertimeCommandParser implements Parser<OvertimeCommand>{
    public OvertimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX, OvertimeCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_OPERATION, PREFIX_AMOUNT);

        if (argMultimap.getValue(PREFIX_OPERATION).isEmpty() || argMultimap.getValue(PREFIX_AMOUNT).isEmpty()) {
            throw new ParseException(OvertimeCommand.MISSING_OPERATION_AMOUNT);
        }



        OvertimeHours overtimeHours = new OvertimeHours();

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            overtimeCommandDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }

        return new OvertimeCommand(index, overtimeHours, order);
    }
}
