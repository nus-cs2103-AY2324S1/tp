package seedu.address.logic.parser.events;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_END_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_START_MEETING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtilTest.createMoreThanAllowedString;
import static seedu.address.testutil.TypicalEvents.EVENT5;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;



public class AddEventCommandParserTest {

    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_duplicateInvalidFormat_failure() throws ParseException {
        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_MEETING),
                DateTimeParser.parseDateTimeDuration(VALID_TIME_START_MEETING, VALID_TIME_END_MEETING),
                Set.of(new PersonBuilder().build()),
                new Location(VALID_LOCATION_MEETING),
                new EventDescription(VALID_DESCRIPTION_MEETING));

        // multiple names - last name accepted
        assertParseFailure(parser, " n/Birthday Meeting n/Meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        // multiple timeStart - last timeStart accepted
        assertParseFailure(parser, " s/01-01-2023 14:00 s/01-01-2023 15:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        // multiple timeEnd - last timeEnd accepted
        assertParseFailure(parser, " e/01-01-2023 15:00 e/01-01-2023 16:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        // multiple clients - all accepted
        assertParseFailure(parser, " c/Bob c/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        // multiple locations - last location accepted
        assertParseFailure(parser, " l/Meeting Room l/Conference Room",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        // multiple descriptions - last description accepted
        assertParseFailure(parser, " d/Meeting for discussion d/Team meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() throws ParseException {
        final Event expectedEvent1;
        expectedEvent1 = new EventBuilder(EVENT5).withEventDescription("").build();

        final Event expectedEvent2;
        expectedEvent2 = new EventBuilder(EVENT5).withLocation("").build();

        final Event expectedEvent3;
        expectedEvent3 = new EventBuilder(EVENT5).withLocation("").withEventDescription("").build();

        //no description
        assertParseSuccess(parser, " n/Meeting s/01-01-2024 14:00 e/01-01-2024 15:00 l/Meeting Room",
                new AddEventCommand(expectedEvent1));
        // no location
        assertParseSuccess(parser, " n/Meeting s/01-01-2024 14:00 e/01-01-2024 15:00 d/Meeting for discussion",
                new AddEventCommand(expectedEvent2));
        // no description and location
        assertParseSuccess(parser, " n/Meeting s/01-01-2024 14:00 e/01-01-2024 15:00",
                new AddEventCommand(expectedEvent3));
    }

    @Test
    public void parse_compulsoryFieldMissing_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, " s/01-01-2023 14:00 e/01-01-2023 15:00", expectedMessage);

        // missing timeStart prefix
        assertParseFailure(parser, " n/Meeting e/01-01-2023 15:00", expectedMessage);

        // missing timeEnd prefix
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " " + VALID_EVENT_NAME_MEETING, expectedMessage);
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        final String textMoreThan256 = createMoreThanAllowedString();
        // invalid name
        assertParseFailure(parser, String.format(" n/%s s/01-01-2023 14:00 e/01-01-2023 15:00",
                textMoreThan256), EventName.MESSAGE_CONSTRAINTS);
        // invalid timeStart
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00x e/01-01-2023 15:00",
                DateTimeParser.INVALID_DATETIME_FORMAT);
        // invalid timeEnd
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00x",
                DateTimeParser.INVALID_DATETIME_FORMAT);
        // invalid client
        assertParseFailure(parser, String.format(" n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 c/%s ",
                textMoreThan256), Name.MESSAGE_CONSTRAINTS);
        // invalid location
        assertParseFailure(parser, String.format(" n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 l/%s",
                textMoreThan256), Location.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_timeStartAfterTimeEnd_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_DATE_TIME);

        // timeStart after timeEnd
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 13:00",
                expectedMessage);
    }

    @Test
    public void parse_preambleNonEmpty_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // non-empty preamble
        assertParseFailure(parser, " some random string", expectedMessage);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        Person dummyBob = new Person(new Name("Bob"), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));

        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_MEETING),
                DateTimeParser.parseDateTimeDuration(VALID_TIME_START_MEETING, VALID_TIME_END_MEETING),
                Set.of(dummyBob),
                new Location(VALID_LOCATION_MEETING),
                new EventDescription(VALID_DESCRIPTION_MEETING));

        // valid input
        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 "
                        + "e/01-01-2023 15:00 c/Bob l/Meeting Room d/Meeting for discussion",
                new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_validClientPrefixForMultipleClients_success() throws ParseException {
        Person dummyAmy = new PersonBuilder().dummyPersonWithName("Amy Bee").build();
        Person dummyBob = new PersonBuilder().dummyPersonWithName("Bob Choo").build();

        Set<Person> expectedClientList = new HashSet<>();
        expectedClientList.add(dummyAmy);
        expectedClientList.add(dummyBob);

        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_MEETING),
                DateTimeParser.parseDateTimeDuration(VALID_TIME_START_MEETING, VALID_TIME_END_MEETING),
                expectedClientList,
                new Location(VALID_LOCATION_MEETING),
                new EventDescription(VALID_DESCRIPTION_MEETING));

        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 "
                        + "c/Amy Bee c/Bob Choo l/Meeting Room d/Meeting for discussion",
                new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_validEventWithInvalidClients_throwsParseException() {
        // Only invalid clients
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 "
                        + "c/ l/Meeting Room d/Meeting for discussion",
                Name.MESSAGE_CONSTRAINTS);

        // Both valid and invalid clients
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 "
                        + "c/Bob, c/ l/Meeting Room d/Meeting for discussion",
                Name.MESSAGE_CONSTRAINTS);
    }

}
