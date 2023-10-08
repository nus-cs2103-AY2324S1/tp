package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;

/**
 * Parses input arguments and creates a new AddEventCommand object.
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {
    @Override
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_START_DATE_TIME,
                        PREFIX_EVENT_END_DATE_TIME);

        if (!arePrefixesPresent(argMultiMap, PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_START_DATE_TIME,
                PREFIX_EVENT_END_DATE_TIME) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        argMultiMap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_START_DATE_TIME,
                PREFIX_EVENT_END_DATE_TIME);
        EventDescription description = ParserUtil.parseEventDescription(argMultiMap
                .getValue(PREFIX_EVENT_DESCRIPTION).get());
        EventPeriod eventPeriod = ParserUtil.parseEventPeriod(argMultiMap.getValue(PREFIX_EVENT_START_DATE_TIME).get(),
                argMultiMap.getValue(PREFIX_EVENT_END_DATE_TIME).get());

        return new AddEventCommand(new Event(description, eventPeriod));
    }

    /**
     * Checks if all the given prefix fields are non-empty.
     *
     * @param argumentMultimap argumentMultimap managing arguments for this command.
     * @param prefixes prefixes to be tested.
     * @return true if all fields are non-empty, false if any field contains empty value.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
