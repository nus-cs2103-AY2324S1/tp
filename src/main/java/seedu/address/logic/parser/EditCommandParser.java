package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Ic;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    public static final String CANNOT_CHANGE_IC_MESSAGE = "You cannot modify a person's IC";
    private static final Logger logger = LogsCenter.getLogger(EditCommand.class);
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @param args The input arguments string to be parsed.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_GENDER, PREFIX_NRIC, PREFIX_TAG,
                        PREFIX_BLOODTYPE, PREFIX_CONDITION, PREFIX_EMERGENCY_CONTACT);

        Ic nric;

        try {
            nric = ParserUtil.parseIc(argMultimap.getPreamble());
        } catch (ParseException pe) {
            logger.severe("Error parsing NRIC: " + pe.getMessage());
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NRIC,
                PREFIX_GENDER, PREFIX_BLOODTYPE, PREFIX_CONDITION, PREFIX_DOCTOR, PREFIX_EMERGENCY_CONTACT);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            editPersonDescriptor.setIc(ParserUtil.parseIc(argMultimap.getValue(PREFIX_NRIC).get()));
        }
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
        if (argMultimap.getValue(PREFIX_CONDITION).isPresent()) {
            editPersonDescriptor.setCondition(ParserUtil.parseCondition(argMultimap.getValue(PREFIX_CONDITION).get()));
        }
        if (argMultimap.getValue(PREFIX_BLOODTYPE).isPresent()) {
            editPersonDescriptor.setBloodType(ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOODTYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editPersonDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        if (argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).isPresent()) {
            editPersonDescriptor.setEmergencyContact(ParserUtil.parsePhone(argMultimap
                    .getValue(PREFIX_EMERGENCY_CONTACT).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            logger.warning("No fields were edited for the EditCommand.");
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        logger.info("Successfully parsed EditCommand.");
        return new EditCommand(nric, editPersonDescriptor);
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
