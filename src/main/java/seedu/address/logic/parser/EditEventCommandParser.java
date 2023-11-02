package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_GROUPS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditMeetingCommand object
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {

    @Override
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME, PREFIX_DATE,
                        PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_NAME, PREFIX_UNASSIGN_PERSONS,
                        PREFIX_GROUP, PREFIX_UNASSIGN_GROUPS);

        Index index;

        // Getting the index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditEventCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();

        if (argMultimap.getValue(PREFIX_EVENT_NAME).isPresent()) {
            editEventDescriptor.setName(ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editEventDescriptor.setDate(ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editEventDescriptor
                    .setStartTime(ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editEventDescriptor.setEndTime(ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_END_TIME).get()));
        }

        if (!argMultimap.getAllValues(PREFIX_NAME).isEmpty()) {
            editEventDescriptor.setPersonNames(ParserUtil.parsePersonNames(argMultimap.getAllValues(PREFIX_NAME)));
        }

        if (!argMultimap.getAllValues(PREFIX_UNASSIGN_PERSONS).isEmpty()) {
            editEventDescriptor
                    .setUnassignPersons(ParserUtil.parsePersonNames(argMultimap.getAllValues(PREFIX_UNASSIGN_PERSONS)));
        }

        if (!argMultimap.getAllValues(PREFIX_GROUP).isEmpty()) {
            editEventDescriptor.setGroups(ParserUtil.parseGroups(argMultimap.getAllValues(PREFIX_GROUP)));
        }

        if (!argMultimap.getAllValues(PREFIX_UNASSIGN_GROUPS).isEmpty()) {
            editEventDescriptor
                    .setUnassignGroups(ParserUtil.parseGroups(argMultimap.getAllValues(PREFIX_UNASSIGN_GROUPS)));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditEventCommand(index, editEventDescriptor);
    }
}
