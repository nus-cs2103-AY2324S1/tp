package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.commands.CompleteCommand.CompleteDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class CompleteCommandParser implements Parser<CompleteCommand> {
    @Override
    public CompleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_DATE);

        CompleteDescriptor completeDescriptor = new CompleteDescriptor();

        Index index;
        if (!argMultimap.getPreamble().trim().isEmpty()) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
                completeDescriptor.setIndex(index);
            }  catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CompleteCommand.MESSAGE_USAGE), pe);
            }
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPOINTMENT_DATE);

        if (argMultimap.getValue(PREFIX_APPOINTMENT_DATE).isPresent()) {
            LocalDateTime date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());
            completeDescriptor.setDate(date);
        }

        if (!completeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(CompleteCommand.MESSAGE_NOT_COMPLETED);
        }

        return new CompleteCommand(completeDescriptor);
    }
}
