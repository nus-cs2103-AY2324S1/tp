package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_WORK;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Title;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandParserTest {
    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = new MeetingBuilder(MEETING1).withAttendees().withTags().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1
                + START_DESC_MEETING1 + END_DESC_MEETING1,
                new AddMeetingCommand(expectedMeeting));


        // multiple tags - all accepted
        Meeting expectedMeetingMultipleTags = new MeetingBuilder(MEETING1).withAttendees().withTags(VALID_TAG_WORK)
                .build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1
                + START_DESC_MEETING1 + END_DESC_MEETING1 + TAG_DESC_WORK,
                new AddMeetingCommand(expectedMeetingMultipleTags));

        /*
        // multiple attendees - all accepted
        Meeting expectedMeetingMultipleAttendees = new MeetingBuilder(MEETING1)
                .withAttendees("Alice Pauline", "Bob Choo").build();
        assertParseSuccess(parser, TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1
                + START_DESC_MEETING1 + END_DESC_MEETING1 + ATTENDEE_DESC_ALICE + ATTENDEE_DESC_BOB,
                new AddMeetingCommand(expectedMeetingMultipleAttendees));
         */
    }

    @Test
    public void parse_repeatedNonAttendeeValue_failure() {
        String validExpectedMeetingString = TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1;

        // multiple title
        assertParseFailure(parser, TITLE_DESC_MEETING1 + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE));

        // multiple locations
        assertParseFailure(parser, LOCATION_DESC_MEETING1 + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // multiple starts
        assertParseFailure(parser, START_DESC_MEETING1 + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START));

        // multiple endss
        assertParseFailure(parser, END_DESC_MEETING1 + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END));

        // multiple fields repeated
        assertParseFailure(parser, validExpectedMeetingString + LOCATION_DESC_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1 + TITLE_DESC_MEETING1 + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE, PREFIX_LOCATION, PREFIX_START, PREFIX_END));

        // invalid value followed by valid value

        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE));

        // invalid location
        assertParseFailure(parser, INVALID_LOCATION_DESC + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // invalid start
        assertParseFailure(parser, INVALID_START_DESC + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START));

        // invalid end
        assertParseFailure(parser, INVALID_END_DESC + validExpectedMeetingString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END));

        // valid value followed by invalid value

        // invalid title
        assertParseFailure(parser, validExpectedMeetingString + INVALID_TITLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE));

        // invalid location
        assertParseFailure(parser, validExpectedMeetingString + INVALID_LOCATION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // invalid start
        assertParseFailure(parser, validExpectedMeetingString + INVALID_START_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START));

        // invalid end
        assertParseFailure(parser, validExpectedMeetingString + INVALID_END_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero attendees
        Meeting expectedMeeting = new MeetingBuilder(MEETING1).withAttendees().withTags().build();
        assertParseSuccess(parser, TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1
                + START_DESC_MEETING1 + END_DESC_MEETING1, new AddMeetingCommand(expectedMeeting));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_MEETING1 + LOCATION_DESC_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, TITLE_DESC_MEETING1 + VALID_LOCATION_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1, expectedMessage);

        // missing start prefix
        assertParseFailure(parser, TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1 + VALID_START_MEETING1
                + END_DESC_MEETING1, expectedMessage);

        // missing end prefix
        assertParseFailure(parser, TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1 + START_DESC_MEETING1
                + VALID_END_MEETING1, expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_MEETING1 + VALID_LOCATION_MEETING1 + VALID_START_MEETING1
                + VALID_END_MEETING1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + LOCATION_DESC_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1, Title.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, TITLE_DESC_MEETING1 + INVALID_LOCATION_DESC + START_DESC_MEETING1
                + END_DESC_MEETING1, Location.MESSAGE_CONSTRAINTS);

        // invalid start
        assertParseFailure(parser, TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1 + INVALID_START_DESC
                + END_DESC_MEETING1, MeetingTime.MESSAGE_CONSTRAINTS);

        // invalid end
        assertParseFailure(parser, TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1 + START_DESC_MEETING1
                + INVALID_END_DESC, MeetingTime.MESSAGE_CONSTRAINTS);


        /*
        // invalid attendee
        assertParseFailure(parser, TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1 + INVALID_ATTENDEE_DESC, Attendee.MESSAGE_CONSTRAINTS);
         */
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_LOCATION_DESC + START_DESC_MEETING1
                + END_DESC_MEETING1, Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_MEETING1 + LOCATION_DESC_MEETING1
                + START_DESC_MEETING1 + END_DESC_MEETING1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddMeetingCommand.MESSAGE_USAGE));
    }
}
