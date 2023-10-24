package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_APPOINTMENT, PREFIX_MEDICAL, PREFIX_TAG);

        boolean hasNamePrefix = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean hasNricPrefix = argMultimap.getValue(PREFIX_NRIC).isPresent();

        if (!hasNamePrefix && !hasNricPrefix) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_NRIC, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_APPOINTMENT, PREFIX_MEDICAL, PREFIX_TAG);

        Name name = null;
        Nric nric = null;

        if (hasNamePrefix) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (hasNricPrefix) {
            nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
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
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseMedicalHistoriesForEdit(argMultimap.getAllValues(PREFIX_MEDICAL))
                .ifPresent(editPersonDescriptor::setMedicalHistories);

        if (argMultimap.getValue(PREFIX_APPOINTMENT).isPresent()) {
            Appointment appointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT).get());
            editPersonDescriptor.setAppointment(appointment);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(name, nric, editPersonDescriptor);
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


    /**
     * Parses {@code Collection<String> medicalHistories} into a {@code Set<MedicalHistory>}
     * if {@code medicalHistories} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<MedicalHistory>} containing zero tags.
     */
    private Optional<Set<MedicalHistory>> parseMedicalHistoriesForEdit(Collection<String> medicalHistories)
            throws ParseException {
        assert medicalHistories != null;

        if (medicalHistories.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> medicalHistorySet = medicalHistories.size() == 1 && medicalHistories.contains("")
                ? Collections.emptySet() : medicalHistories;
        return Optional.of(ParserUtil.parseMedicals(medicalHistorySet));
    }

}
