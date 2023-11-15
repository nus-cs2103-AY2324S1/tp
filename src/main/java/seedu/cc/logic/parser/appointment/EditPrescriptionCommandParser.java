package seedu.cc.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_MEDICINE_NAME;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.appointmentcommands.EditAppointmentEventCommand;
import seedu.cc.logic.commands.appointmentcommands.EditPrescriptionCommand;
import seedu.cc.logic.parser.ArgumentMultimap;
import seedu.cc.logic.parser.ArgumentTokenizer;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddAppointmentEventCommand object.
 */
public class EditPrescriptionCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPrescriptionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_INDEX, PREFIX_MEDICINE_NAME);

        Index eventIndex;
        Index patientIndex;

        try {
            eventIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPrescriptionCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_INDEX, PREFIX_MEDICINE_NAME);

        EditAppointmentEventCommand.EditAppointmentEventDescriptor editAppointmentEventDescriptor =
                new EditAppointmentEventCommand.EditAppointmentEventDescriptor();
        if (argMultimap.getValue(PREFIX_PATIENT_INDEX).isEmpty()
                || argMultimap.getValue(PREFIX_MEDICINE_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPrescriptionCommand.MESSAGE_USAGE));
        }

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPrescriptionCommand.MESSAGE_USAGE), pe);
        }

        Prescription prescription = new Prescription(argMultimap.getValue(PREFIX_MEDICINE_NAME).get());
        Set<Prescription> prescriptions = ParserUtil.parsePrescriptions(argMultimap.getAllValues(PREFIX_MEDICINE_NAME));
        editAppointmentEventDescriptor.setPrescriptions(prescriptions);
        return new EditPrescriptionCommand(eventIndex, patientIndex, editAppointmentEventDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
