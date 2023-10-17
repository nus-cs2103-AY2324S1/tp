package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 * @author Yuheng
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {


    @Override
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_NAME, PREFIX_DATE,
                        PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_NAME, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MEETING_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        EventName meetingName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_MEETING_NAME).get());
        EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        EventTime meetingStartTime = EventTime.NULL_EVENT_TIME;
        EventTime meetingEndTime = EventTime.NULL_EVENT_TIME;
        Set<Name> nameList = ParserUtil.parsePersonNames(argMultimap.getAllValues(PREFIX_NAME));

        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            meetingStartTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_START_TIME).get());
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            meetingEndTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_END_TIME).get());
        }

        Meeting meeting = new Meeting(meetingName, eventDate,
                Optional.of(meetingStartTime), Optional.of(meetingEndTime), nameList);

        return new AddMeetingCommand(meeting);
    }
}
