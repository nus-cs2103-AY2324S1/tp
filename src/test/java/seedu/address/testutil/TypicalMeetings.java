package seedu.address.testutil;

import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meetings} objects to be used in tests.
 */
public class TypicalMeetings {
    public static final Meeting MEETING1 = new MeetingBuilder().withTitle("CS2103T meeting")
            .withLocation("Zoom call url")
            .withStart("20.09.2023 1000").withEnd("20.09.2023 1200")
            .withAttendees().build();
}
