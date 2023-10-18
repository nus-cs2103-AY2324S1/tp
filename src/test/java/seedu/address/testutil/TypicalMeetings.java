package seedu.address.testutil;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting TP_MEETING;

    static {
        try {
            TP_MEETING = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_WITHOUT_TIME;

    static {
        try {
            MEETING_WITHOUT_TIME = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_WITHOUT_PERSONS;

    static {
        try {
            MEETING_WITHOUT_PERSONS = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_LATER_THAN_TP_MEETING;

    static {
        try {
            MEETING_LATER_THAN_TP_MEETING = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-19")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
