package seedu.address.logic.parser.events;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.events.EditEventCommand;
import seedu.address.logic.commands.events.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new EditEventCommand object.
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand
     * and returns an EditEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME, PREFIX_TIME_START, PREFIX_TIME_END,
                        PREFIX_CLIENT, PREFIX_LOCATION, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditEventCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_NAME, PREFIX_TIME_START, PREFIX_TIME_END,
                PREFIX_LOCATION, PREFIX_DESCRIPTION);

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();

        if (argMultimap.getValue(PREFIX_EVENT_NAME).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME_START).isPresent()) {
            editEventDescriptor.setTimeStartString(argMultimap.getValue(PREFIX_TIME_START).get());
        }
        if (argMultimap.getValue(PREFIX_TIME_END).isPresent()) {
            editEventDescriptor.setTimeEndString(argMultimap.getValue(PREFIX_TIME_END).get());
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editEventDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editEventDescriptor.setEventDescription(ParserUtil
                    .parseEventDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        parseClientsForEdit(argMultimap.getAllValues(PREFIX_CLIENT)).ifPresent(editEventDescriptor::setClients);

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditEventCommand(index, editEventDescriptor);
    }

    /**
     * Parses {@code Collection<String> clients} into a {@code Set<Person>} if {@code clients} is non-empty.
     * If {@code clients} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Person>} containing zero clients.
     */
    private Optional<Set<Person>> parseClientsForEdit(Collection<String> clients) throws ParseException {
        assert clients != null;

        if (clients.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = clients.size() == 1 && clients.contains("") ? Collections.emptySet() : clients;
        return Optional.of(ParserUtil.parseClients(tagSet));
    }
}
