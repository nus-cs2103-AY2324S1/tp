package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALIZATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditDentistCommand;
import seedu.address.logic.commands.EditDentistCommand.EditDentistDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditDentistCommand object
 */
public class EditDentistCommandParser implements Parser<EditDentistCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditDentistCommand
     * and returns an EditDentistCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDentistCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            String[] argsArray = args.trim().split("\\s+");
            String firstArg = argsArray[0].trim();
            long dentistId = Long.parseLong(firstArg);

            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args,
                            PREFIX_NAME,
                            PREFIX_PHONE,
                            PREFIX_EMAIL,
                            PREFIX_ADDRESS,
                            PREFIX_SPECIALIZATION,
                            PREFIX_YOE,
                            PREFIX_TAG);

            argMultimap.verifyNoDuplicatePrefixesFor(
                    PREFIX_NAME,
                    PREFIX_PHONE,
                    PREFIX_EMAIL,
                    PREFIX_ADDRESS,
                    PREFIX_SPECIALIZATION,
                    PREFIX_YOE);

            EditDentistCommand.EditDentistDescriptor editDentistDescriptor = new EditDentistDescriptor();

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editDentistDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editDentistDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editDentistDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }
            if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                editDentistDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
            }
            if (argMultimap.getValue(PREFIX_SPECIALIZATION).isPresent()) {
                editDentistDescriptor.setSpecialization(
                        ParserUtil.parseSpecialization(argMultimap.getValue(PREFIX_SPECIALIZATION).get()));
            }
            if (argMultimap.getValue(PREFIX_YOE).isPresent()) {
                editDentistDescriptor.setYoe(ParserUtil.parseYoe(argMultimap.getValue(PREFIX_YOE).get()));
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editDentistDescriptor::setTags);

            if (!editDentistDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }

            return new EditDentistCommand(dentistId, editDentistDescriptor);
        } catch (NumberFormatException | NullPointerException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDentistCommand.MESSAGE_USAGE), e);
        }


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
