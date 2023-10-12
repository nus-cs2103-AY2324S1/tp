package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATETIME_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INTEGER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;

import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;

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

        int contactId = -1;
        String eventName = argMultimap.getValue(PREFIX_EVENT_NAME).get();
        String startTime = argMultimap.getValue(PREFIX_EVENT_START_TIME).get();
        String endTime = argMultimap.getValue(PREFIX_EVENT_END_TIME).orElseGet(()->"");
        String location = argMultimap.getValue(PREFIX_EVENT_LOCATION).orElseGet(()->"");
        String information = argMultimap.getValue(PREFIX_EVENT_INFORMATION).orElseGet(()->"");
        Event newEvent = null;
        try {
            newEvent = new Event(eventName, startTime, endTime, location, information);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME_FORMAT, e.getMessage()));
        }
        try {
            contactId = Integer.parseInt(argMultimap.getValue(PREFIX_PERSON_ID).get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_INTEGER_ARGUMENT, e.getMessage()));
        }
        return new AddEventCommand(contactId, newEvent);
    }
}
