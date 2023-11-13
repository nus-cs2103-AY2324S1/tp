package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
public class AddEventCommandParserTest {
    private final AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no name specified
        assertParseFailure(parser, " " + PREFIX_DATE + "2020-10-30", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE));

        // no date specified
        assertParseFailure(parser, " " + PREFIX_EVENT_NAME + "FumbleLog Meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE));

        // no name and no date specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, "a" + VALID_EVENT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddEventCommand() throws Exception {
        String userInput = " " + PREFIX_EVENT_NAME + "FumbleLog Meeting" + " " + PREFIX_DATE + "2023-12-30";
        EventName eventName = new EventName("FumbleLog Meeting");
        EventDate eventDate = new EventDate("2023-12-30");
        EventTime eventStartTime = EventTime.NULL_EVENT_TIME;
        EventTime eventEndTime = EventTime.NULL_EVENT_TIME;
        Set<Name> nameList = new HashSet<>();
        Set<Group> groupList = new HashSet<>();
        Meeting meeting = new Meeting(eventName, eventDate, Optional.of(eventStartTime),
                Optional.of(eventEndTime), nameList, groupList);
        AddEventCommand expectedAddEventCommand = new AddEventCommand(meeting);
        assertParseSuccess(parser, userInput, expectedAddEventCommand);
    }

    @Test
    public void parse_validArgsWithOptionalFields_returnsAddEventCommand() throws Exception {
        String userInput = " " + PREFIX_EVENT_NAME + "FumbleLog Meeting" + " " + PREFIX_DATE + "2023-12-30"
                + " " + PREFIX_START_TIME + "1000" + " " + PREFIX_END_TIME + "1200"
                + " " + PREFIX_NAME + "Ken" + " " + PREFIX_NAME + "Yuheng"
                + " " + PREFIX_GROUP + "Team2";
        EventName eventName = new EventName("FumbleLog Meeting");
        EventDate eventDate = new EventDate("2023-12-30");
        EventTime eventStartTime = EventTime.of("1000");
        EventTime eventEndTime = EventTime.of("1200");
        Set<Name> nameList = new HashSet<>();
        nameList.add(new Name("Ken"));
        nameList.add(new Name("Yuheng"));
        Set<Group> groupList = new HashSet<>();
        groupList.add(new Group("Team2"));
        Meeting meeting = new Meeting(eventName, eventDate, Optional.of(eventStartTime),
                Optional.of(eventEndTime), nameList, groupList);
        AddEventCommand expectedAddEventCommand = new AddEventCommand(meeting);
        assertParseSuccess(parser, userInput, expectedAddEventCommand);
    }
}
