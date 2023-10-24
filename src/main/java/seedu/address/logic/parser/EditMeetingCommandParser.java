package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new EditMeetingCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    @Override
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_NAME, PREFIX_DATE,
                        PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_NAME, PREFIX_UNASSIGN_PERSONS,
                        PREFIX_GROUP, PREFIX_UNASSIGN_GROUPS);

        Index index;

        // Getting the index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MEETING_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();

        if (argMultimap.getValue(PREFIX_MEETING_NAME).isPresent()) {
            editMeetingDescriptor.setName(ParserUtil.parseEventName(argMultimap.getValue(PREFIX_MEETING_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editMeetingDescriptor.setDate(ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editMeetingDescriptor
                    .setStartTime(ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editMeetingDescriptor.setEndTime(ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_END_TIME).get()));
        }

        parseNamesForEdit(argMultimap.getAllValues(PREFIX_NAME)).ifPresent(editMeetingDescriptor::setPersonNames);
        parseNamesForEdit(argMultimap.getAllValues(PREFIX_UNASSIGN_PERSONS))
                .ifPresent(editMeetingDescriptor::setUnassignPersons);

        parseGroupsForEdit(argMultimap.getAllValues(PREFIX_GROUP)).ifPresent(editMeetingDescriptor::setGroups);
        parseGroupsForEdit(argMultimap.getAllValues(PREFIX_UNASSIGN_GROUPS))
                .ifPresent(editMeetingDescriptor::setUnassignGroups);

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }

    private Optional<Set<Name>> parseNamesForEdit(Collection<String> names) throws ParseException {
        assert names != null;

        if (names.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> nameSet = names.size() == 1 && names.contains("") ? Collections.emptySet() : names;
        return Optional.of(ParserUtil.parsePersonNames(nameSet));
    }

    private Optional<Set<Group>> parseGroupsForEdit(Collection<String> groups) throws ParseException {
        assert groups != null;

        if (groups.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> groupSet = groups.size() == 1 && groups.contains("") ? Collections.emptySet() : groups;
        return Optional.of(ParserUtil.parseGroups(groupSet));
    }
}
