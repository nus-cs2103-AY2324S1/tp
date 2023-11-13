package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENROL_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEAREST_MRT_STATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.EnrolDate;
import seedu.address.model.tag.Subject;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_GENDER, PREFIX_SEC_LEVEL, PREFIX_NEAREST_MRT_STATION, PREFIX_SUBJECT, PREFIX_ENROL_DATE);

        Index index = null;
        Name name = null;
        try {
            Object parsedPreamble = ParserUtil.parsePreamble(argMultimap.getPreamble());
            if (parsedPreamble instanceof Index) {
                index = (Index) parsedPreamble;
            } else {
                name = (Name) parsedPreamble;
            }

        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_GENDER, PREFIX_GENDER, PREFIX_NEAREST_MRT_STATION);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_SEC_LEVEL).isPresent()) {
            editPersonDescriptor.setSecLevel(ParserUtil.parseSecLevel(argMultimap.getValue(PREFIX_SEC_LEVEL).get()));
        }
        if (argMultimap.getValue(PREFIX_NEAREST_MRT_STATION).isPresent()) {
            editPersonDescriptor.setNearestMrtStation(ParserUtil.parseMrtStation(
                    argMultimap.getValue(PREFIX_NEAREST_MRT_STATION).get()));
        }

        Collection<EnrolDate> dates = ParserUtil.parseDates(argMultimap.getAllValues(PREFIX_ENROL_DATE));
        Collection<String> tags = argMultimap.getAllValues(PREFIX_SUBJECT);
        parseTagsForEdit(tags, dates).ifPresent(editPersonDescriptor::setSubjects);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return name == null ? new EditCommand(index, editPersonDescriptor)
                : new EditCommand(name, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} and {@code Collection<String> dates}
     * into a {@code Set<Subject>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Subject>} containing zero tags.
     */
    private Optional<Set<Subject>> parseTagsForEdit(Collection<String> tags,
                                                    Collection<EnrolDate> dates) throws ParseException {
        requireNonNull(tags);
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        Set<Subject> subjects = dates.isEmpty() ? ParserUtil.parseTags(tagSet) : ParserUtil.parseTags(tagSet, dates);
        return subjects.isEmpty() ? Optional.empty() : Optional.of(subjects);
    }

}
