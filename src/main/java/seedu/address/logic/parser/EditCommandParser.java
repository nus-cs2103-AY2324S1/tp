package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditCommand.EditSpecialistDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.PersonType;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements ParserComplex<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(PersonType personType, String args) throws ParseException {
        requireNonNull(args);
        if (personType.equals(PersonType.PATIENT)) {
            return parsePatient(args);
        } else if (personType.equals(PersonType.SPECIALIST)) {
            return parseSpecialist(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_PERSON_TYPE));
        }
    }

    private EditCommand parsePatient(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_TAG, PREFIX_AGE, PREFIX_MEDICALHISTORY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_AGE);

        EditPersonDescriptor editPersonDescriptor = parseCommonPersonForEdit(argMultimap);
        EditPatientDescriptor editPatientDescriptor = (EditPatientDescriptor) editPersonDescriptor;

        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editPatientDescriptor.setAge(ParserUtil.parseAge(argMultimap
                    .getValue(PREFIX_AGE).get()));
        }
        parseMedicalHistoriesForEdit(argMultimap.getAllValues(PREFIX_MEDICALHISTORY))
                .ifPresent(editPatientDescriptor::setMedicalHistory);

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(editPatientDescriptor);
    }

    private EditCommand parseSpecialist(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION,
                        PREFIX_TAG, PREFIX_SPECIALTY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_LOCATION, PREFIX_SPECIALTY);

        EditPersonDescriptor editPersonDescriptor = parseCommonPersonForEdit(argMultimap);
        EditSpecialistDescriptor editSpecialistDescriptor = (EditSpecialistDescriptor) editPersonDescriptor;

        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editSpecialistDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_SPECIALTY).isPresent()) {
            editSpecialistDescriptor.setSpecialty(ParserUtil.parseSpecialty(
                    argMultimap.getValue(PREFIX_SPECIALTY).get()));
        }

        if (!editSpecialistDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(editSpecialistDescriptor);
    }


    private EditPersonDescriptor parseCommonPersonForEdit(ArgumentMultimap argMultimap) throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        return editPersonDescriptor;
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
     * If {@code medicalHistories} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<MedicalHistories>} containing zero medical history.
     */
    private Optional<Set<MedicalHistory>> parseMedicalHistoriesForEdit(Collection<String> medicalHistories)
            throws ParseException {
        assert medicalHistories != null;
        if (medicalHistories.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> medHistSet = medicalHistories.size() == 1 && medicalHistories.contains("")
                ? Collections.emptySet()
                : medicalHistories;
        return Optional.of(ParserUtil.parseMedicalHistories(medHistSet));
    }
}
