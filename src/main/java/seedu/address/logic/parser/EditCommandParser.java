package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    public static final Prefix[] RELEVANT_PREFIXES = new Prefix[]{PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
        PREFIX_GENDER, PREFIX_IC_NUMBER, PREFIX_BIRTHDAY, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRIORITY};

    public static final Prefix[] RELEVANT_PREFIXES_WITHOUT_TAG = new Prefix[]{PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
        PREFIX_GENDER, PREFIX_IC_NUMBER, PREFIX_BIRTHDAY, PREFIX_ADDRESS, PREFIX_PRIORITY};

    public static final Prefix[] REQUIRED_PREFIXES = new Prefix[]{PREFIX_IC_NUMBER};

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, RELEVANT_PREFIXES);

        IcNumber icNumber;
        if (!areRelevantPrefixesPresent(argMultimap, RELEVANT_PREFIXES) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        // check if required prefixes are present and not empty
        if (!areRequiredPrefixesPresent(argMultimap, REQUIRED_PREFIXES)) {
            throw new ParseException(
                String.format(MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT, Arrays.toString(REQUIRED_PREFIXES)));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(RELEVANT_PREFIXES_WITHOUT_TAG);
        icNumber = ParserUtil.parseIcNumber(argMultimap.getValue(PREFIX_IC_NUMBER).get());
        EditPatientDescriptor editPatientDescriptor = createEditPatientDescriptor(argMultimap);

        return new EditCommand(icNumber, editPatientDescriptor);
    }

    /**
     * Takes in {@Code ArgumentMultimap} and creates the {@Code EditPatientDescriptor}
     *
     * @param argMultimap Contains mapping of key which is prefix and value which is argument value
     * @return EditPatientDescriptor to be given to EditCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public static EditPatientDescriptor createEditPatientDescriptor(ArgumentMultimap argMultimap)
            throws ParseException {
        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPatientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPatientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPatientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPatientDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            editPatientDescriptor.setBirthday(ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get()));
        }
        if (argMultimap.getValue(PREFIX_IC_NUMBER).isPresent()) {
            editPatientDescriptor.setIcNumber(ParserUtil.parseIcNumber(argMultimap.getValue(PREFIX_IC_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editPatientDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPatientDescriptor::setTags);
        if (!editPatientDescriptor.isAnyFieldExceptIcNumberEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return editPatientDescriptor;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private static Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    private static boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... requiredPrefixes) {
        return Stream.of(requiredPrefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean areRelevantPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... requiredPrefixes) {
        return Stream.of(requiredPrefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
