package seedu.cc.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.EditCommand;
import seedu.cc.logic.commands.appointmentcommands.DeleteAppointmentEventCommand;
import seedu.cc.logic.parser.ArgumentMultimap;
import seedu.cc.logic.parser.ArgumentTokenizer;
import seedu.cc.logic.parser.Parser;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAppointmentEventCommand object.
 */
public class DeleteAppointmentEventCommandParser implements Parser<DeleteAppointmentEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAppointmentEventCommand
     * and returns a DeleteAppointmentEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAppointmentEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_INDEX);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_INDEX);

        Index eventIndex;

        try {
            eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentEventCommand.MESSAGE_USAGE), pe);
        }

        Index patientIndex;

        if (argMultimap.getValue(PREFIX_PATIENT_INDEX).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteAppointmentEventCommand(eventIndex, patientIndex);
    }

}
