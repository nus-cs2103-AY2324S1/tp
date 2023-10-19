package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditMeetingCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMeetingCommand
     * and returns an EditMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_LOCATION, PREFIX_START,
                        PREFIX_END, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_LOCATION, PREFIX_START, PREFIX_END, PREFIX_TAG);

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editMeetingDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editMeetingDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_START).isPresent()) {
            editMeetingDescriptor.setStart(ParserUtil.parseTime(argMultimap.getValue(PREFIX_START).get()));
        }
        if (argMultimap.getValue(PREFIX_END).isPresent()) {
            editMeetingDescriptor.setEnd(ParserUtil.parseTime(argMultimap.getValue(PREFIX_END).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editMeetingDescriptor::setTags);

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
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
