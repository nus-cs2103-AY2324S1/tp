package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EMPTY_MEDICAL_HISTORY_TO_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Id;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    private static final Logger logger = Logger.getLogger(EditCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_APPOINTMENT, PREFIX_MEDICAL);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_APPOINTMENT);

        boolean hasNamePrefix = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean hasIdPrefix = argMultimap.getValue(PREFIX_ID).isPresent();

        if (!hasNamePrefix && !hasIdPrefix) {
            logger.log(Level.WARNING, "Missing NAME or ID prefix in edit command: {0}", args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        Name name = null;
        Id id = null;

        if (hasNamePrefix) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (hasIdPrefix) {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        parseMedicalHistoriesForEdit(argMultimap.getAllValues(PREFIX_MEDICAL))
                .ifPresent(editPersonDescriptor::setMedicalHistories);

        if (argMultimap.getValue(PREFIX_APPOINTMENT).isPresent()) {
            Appointment appointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT).get());
            editPersonDescriptor.setAppointment(appointment);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        logger.log(Level.INFO, "Parsed EditCommand with Name: {0}, ID: {1}", new Object[]{name, id});
        return new EditCommand(name, id, editPersonDescriptor);
    }



    /**
     * Parses {@code Collection<String> medicalHistories} into a {@code Set<MedicalHistory>}
     * if {@code medicalHistories} is non-empty.
     * If {@code medicalHistories } contain only one element which is an empty string, it will be parsed into a
     * {@code Set<MedicalHistory>} containing zero medicalHistories.
     */
    private Optional<Set<MedicalHistory>> parseMedicalHistoriesForEdit(Collection<String> medicalHistories)
            throws ParseException {
        assert medicalHistories != null;

        if (medicalHistories.size() == 1 && medicalHistories.contains("")) {
            throw new ParseException(MESSAGE_EMPTY_MEDICAL_HISTORY_TO_EDIT);
        }

        if (medicalHistories.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ParserUtil.parseMedicals(medicalHistories));
    }
}
