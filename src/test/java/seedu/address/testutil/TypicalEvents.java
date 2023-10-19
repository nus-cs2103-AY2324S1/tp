package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNUSED_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_LATER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event CONFERENCE = new EventBuilder().withDescription("Conference")
            .withStartEndDate("2023-11-15 08:30", "2023-11-15 17:00").build();

    public static final Event LAUNCH = new EventBuilder()
            .withDescription("Launch and Marketing Strategy Discussion")
            .withStartEndDate("2023-12-05 10:00", "2023-12-05 12:00").build();

    public static final Event MEETING = new EventBuilder().withDescription("Team Meeting")
            .withStartEndDate("2023-10-25 09:00", "2023-10-25 10:30").build();

    public static final Event WEBINAR = new EventBuilder()
            .withDescription("Webinar")
            .withStartEndDate("2023-10-30 15:00", "2023-10-30 16:30").build();

    public static final Event WORKSHOP = new EventBuilder()
            .withDescription("Workshop")
            .withStartEndDate("2023-11-10 14:00", "2023-11-10 16:30").build();

    // Manually added
    public static final Event TRAINING = new EventBuilder().withDescription("Customer Support Training")
            .withStartEndDate("2023-11-20 09:00", "2023-11-20 12:00").build();
    public static final Event REVIEW = new EventBuilder().withDescription("Project Review")
            .withStartEndDate("2023-10-29 15:00", "2023-10-29 16:30").build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event TEST_EVENT_A = new EventBuilder().withDescription(VALID_DESCRIPTION)
            .withStartEndDate(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER).build();

    public static final Event TEST_EVENT_B = new EventBuilder().withDescription(VALID_UNUSED_DESCRIPTION)
            .withStartEndDate(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code Calendar} with all the typical events.
     */
    public static Calendar getTypicalCalendar() {
        Calendar calendar = new Calendar();
        for (Event event : getTypicalEvents()) {
            calendar.addEvent(event);
        }
        return calendar;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(CONFERENCE, LAUNCH, MEETING, WEBINAR, WORKSHOP));
    }
}
