package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_BLANK_ARGUMENTS;
import static seedu.address.logic.Messages.MESSAGE_ERROR_STATE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
        if (personType.equals(PersonType.PATIENT)) {
            return parsePatient(args);
        }
        if (personType.equals(PersonType.SPECIALIST)) {
            return parseSpecialist(args);
        }
        throw new ParseException(MESSAGE_ERROR_STATE);
    }

    private EditCommand parsePatient(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_TAG, PREFIX_AGE, PREFIX_MEDICALHISTORY);

        if (!argMultimap.getPreamble().isBlank() && !args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE_PATIENT));
        }

        if (argMultimap.anyValuesBlank(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_AGE)) {
            throw new ParseException(String.format(MESSAGE_BLANK_ARGUMENTS,
                    EditCommand.MESSAGE_USAGE_PATIENT));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_AGE);

        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        parseCommonPersonForEdit(editPatientDescriptor, argMultimap);

        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editPatientDescriptor.setAge(ParserUtil.parseAge(argMultimap
                    .getValue(PREFIX_AGE).get()));
        }
        parseMedicalHistoriesForEdit(argMultimap.getAllValues(PREFIX_MEDICALHISTORY))
                .ifPresent(editPatientDescriptor::setMedicalHistory);

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED + "\n" + EditCommand.MESSAGE_USAGE_PATIENT);
        }
        return new EditCommand(editPatientDescriptor);
    }

    private EditCommand parseSpecialist(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION,
                        PREFIX_TAG, PREFIX_SPECIALTY);

        if (!argMultimap.getPreamble().isBlank() && !args.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE_SPECIALIST));
        }

        if (argMultimap.anyValuesBlank(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION, PREFIX_SPECIALTY)) {
            throw new ParseException(String.format(MESSAGE_BLANK_ARGUMENTS,
                    EditCommand.MESSAGE_USAGE_SPECIALIST));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_LOCATION, PREFIX_SPECIALTY);

        EditSpecialistDescriptor editSpecialistDescriptor = new EditSpecialistDescriptor();
        parseCommonPersonForEdit(editSpecialistDescriptor, argMultimap);

        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editSpecialistDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_SPECIALTY).isPresent()) {
            editSpecialistDescriptor.setSpecialty(ParserUtil.parseSpecialty(
                    argMultimap.getValue(PREFIX_SPECIALTY).get()));
        }

        if (!editSpecialistDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED + "\n" + EditCommand.MESSAGE_USAGE_SPECIALIST);
        }
        return new EditCommand(editSpecialistDescriptor);
    }

    /**
     * Creates an {@code EditPersonDescriptor} by checking if each attribute of a {@code Person}
     * is present within the user input arguments.
     */
    private EditPersonDescriptor parseCommonPersonForEdit(EditPersonDescriptor editPersonDescriptor,
                                                          ArgumentMultimap argMultimap) throws ParseException {
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
