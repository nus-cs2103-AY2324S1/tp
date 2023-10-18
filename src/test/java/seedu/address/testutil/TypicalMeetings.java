package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meetings} objects to be used in
 * tests.
 */
public class TypicalMeetings {
    public static final Meeting MEETING1 = new MeetingBuilder().withTitle("CS2103T meeting")
            .withLocation("Zoom call url")
            .withStart("20.09.2023 1000").withEnd("20.09.2023 1200")
            .withAttendees(TypicalPersons.getTypicalAttendees())
            .withTags("work", "important")
            .build();

    public static final Meeting MEETING2 = new MeetingBuilder().withTitle("CS2101 meeting")
            .withLocation("Zoom call url")
            .withStart("20.09.2023 1000").withEnd("20.09.2023 1200")
            .withAttendees(Arrays.copyOfRange(TypicalPersons.getTypicalAttendees(), 1, 7))
            .build();

    public static final Meeting MEETING3 = new MeetingBuilder().withTitle("CS2101 meeting")
            .withLocation("com 3")
            .withStart("20.09.2023 1000").withEnd("20.09.2023 1200")
            .withAttendees(TypicalPersons.getTypicalAttendees())
            .build();

    public static final Meeting MEETING4 = new MeetingBuilder().withTitle("ABCDE meeting")
            .withLocation("com 3")
            .withStart("30.09.2023 1000").withEnd("30.09.2023 1200")
            .withAttendees(TypicalPersons.getTypicalAttendees())
            .withTags("work", "important")
            .build();

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        return ab;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(MEETING1, MEETING2, MEETING3, MEETING4));
    }
}
