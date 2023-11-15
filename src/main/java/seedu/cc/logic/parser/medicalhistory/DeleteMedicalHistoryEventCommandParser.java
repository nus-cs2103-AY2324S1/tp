package seedu.cc.logic.parser.medicalhistory;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.EditCommand;
import seedu.cc.logic.commands.medhisteventcommands.DeleteMedicalHistoryEventCommand;
import seedu.cc.logic.parser.ArgumentMultimap;
import seedu.cc.logic.parser.ArgumentTokenizer;
import seedu.cc.logic.parser.Parser;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMedicalHistoryEventCommand object.
 */
public class DeleteMedicalHistoryEventCommandParser implements Parser<DeleteMedicalHistoryEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMedicalHistoryEventCommand
     * and returns a DeleteMedicalHistoryEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMedicalHistoryEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_INDEX);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_INDEX);

        Index eventIndex;

        try {
            eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMedicalHistoryEventCommand.MESSAGE_USAGE), pe);
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

        return new DeleteMedicalHistoryEventCommand(eventIndex, patientIndex);
    }

}
