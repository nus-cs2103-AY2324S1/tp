package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventInformation;
import seedu.address.model.event.EventLocation;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.person.ContactID;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {


    @Override
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_ID,
                PREFIX_EVENT_NAME, PREFIX_EVENT_START_TIME, PREFIX_EVENT_END_TIME,
                PREFIX_EVENT_LOCATION, PREFIX_EVENT_INFORMATION);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PERSON_ID, PREFIX_EVENT_NAME,
                PREFIX_EVENT_START_TIME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PERSON_ID,
                PREFIX_EVENT_NAME, PREFIX_EVENT_START_TIME, PREFIX_EVENT_END_TIME, PREFIX_EVENT_LOCATION,
                PREFIX_EVENT_INFORMATION);

        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get());
        EventTime startTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_EVENT_START_TIME).get());
        EventTime endTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_EVENT_END_TIME).orElseGet(()->null));
        EventLocation location =
                ParserUtil.parseEventLocation(argMultimap.getValue(PREFIX_EVENT_LOCATION).orElseGet(()->null));
        EventInformation information =
                ParserUtil.parseEventInformation(argMultimap.getValue(PREFIX_EVENT_INFORMATION).orElseGet(()->null));
        ContactID contactId = ParserUtil.parseContactID(argMultimap.getValue(PREFIX_PERSON_ID).get());
        Event newEvent = new Event(eventName, startTime, endTime, location, information);

        return new AddEventCommand(contactId, newEvent);
    }
}
