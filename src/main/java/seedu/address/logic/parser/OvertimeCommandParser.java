package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OvertimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;

public class OvertimeCommandParser implements Parser<OvertimeCommand>{
    public OvertimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_OVERTIME_HOURS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OvertimeCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_OVERTIME_HOURS);

        OvertimeCommand.OvertimeCommandDescriptor overtimeCommandDescriptor = new OvertimeCommand.OvertimeCommandDescriptor();

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            overtimeCommandDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_OVERTIME_HOURS).isPresent()) {
            overtimeCommandDescriptor.setOvertimeHours(ParserUtil.parseOvertimeHours(argMultimap.getValue(PREFIX_OVERTIME_HOURS).get()));
        }

        if (!overtimeCommandDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new OvertimeCommand(index, overtimeCommandDescriptor);
    }
}
