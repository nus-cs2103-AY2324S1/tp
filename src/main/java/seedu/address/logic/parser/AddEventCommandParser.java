package seedu.address.logic.parser;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.ui.MainWindow;

import java.time.format.DateTimeParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATETIME_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddEventCommandParser implements Parser<AddEventCommand> {


    @Override
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EVENT_NAME, PREFIX_EVENT_START_TIME,
                PREFIX_EVENT_END_TIME, PREFIX_EVENT_LOCATION, PREFIX_EVENT_INFORMATION);

        if (!AddressBookParser.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EVENT_NAME, PREFIX_EVENT_START_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_EVENT_NAME, PREFIX_EVENT_START_TIME,
                PREFIX_EVENT_END_TIME, PREFIX_EVENT_LOCATION, PREFIX_EVENT_INFORMATION);

        String contactName = argMultimap.getValue(PREFIX_NAME).get();
        String eventName = argMultimap.getValue(PREFIX_EVENT_NAME).get();
        String startTime = argMultimap.getValue(PREFIX_EVENT_START_TIME).get();
        String endTime = argMultimap.getValue(PREFIX_EVENT_END_TIME).orElseGet(()->"");
        String location = argMultimap.getValue(PREFIX_EVENT_LOCATION).orElseGet(()->"");
        String information = argMultimap.getValue(PREFIX_EVENT_INFORMATION).orElseGet(()->"");
        Event newEvent = null;
        try{
            newEvent = new Event(eventName, startTime, endTime, location, information);
        }
        catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME_FORMAT, e.getMessage()));
        }

        return new AddEventCommand(contactName, newEvent);
    }
}
