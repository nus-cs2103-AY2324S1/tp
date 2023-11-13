package seedu.address.model.event;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNUSED_DESCRIPTION;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventDescriptionBuilder;
import seedu.address.testutil.EventPeriodBuilder;

public class EventTest {
    private static final EventDescription VALID_EVENT_DESCRIPTION = new EventDescriptionBuilder().build();
    private static final EventPeriod VALID_EVENT_PERIOD = new EventPeriodBuilder().build();
    @Test
    public void constructorTest() {
        assertThrows(NullPointerException.class, () -> new Event(null, null));

        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_DESCRIPTION, null));

        assertThrows(NullPointerException.class, () -> new Event(null, VALID_EVENT_PERIOD));
    }

    @Test
    public void createNonConflictingEventTest() {
        Event nonConflictingEvent = Event.createNonConflictingEvent();

        assertEquals(nonConflictingEvent.getDescription().getDescription(),
                VALID_UNUSED_DESCRIPTION);
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(new EventBuilder().withDescription(VALID_DESCRIPTION).build().getDescription(),
                new EventDescriptionBuilder().changeDescription(VALID_DESCRIPTION).build());

        assertNotEquals(new EventBuilder().withDescription(VALID_DESCRIPTION).build().getDescription(),
                new EventDescriptionBuilder().changeDescription(VALID_UNUSED_DESCRIPTION).build());
    }

    @Test
    public void getEventPeriodTest() {
        assertEquals(new EventBuilder().withStartEndDate(VALID_START_DATE_EARLIER,
                        VALID_END_DATE_EARLIER).build().getEventPeriod(),
                new EventPeriodBuilder().changeStartAndEnd(VALID_START_DATE_EARLIER,
                        VALID_END_DATE_EARLIER).build());

        assertNotEquals(new EventBuilder().withStartEndDate(VALID_START_DATE_EARLIER,
                        VALID_END_DATE_EARLIER).build().getEventPeriod(),
                new EventPeriodBuilder().changeStartAndEnd(VALID_START_DATE_LATER,
                        VALID_END_DATE_LATER).build());
    }

    @Test
    public void isConflictingTest() {
        Event validEvent = new EventBuilder().withStartEndDate(VALID_START_DATE_EARLIER, VALID_END_DATE_LATER).build();
        Event conflictingEvent = new EventBuilder().withStartEndDate(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER)
                .build();
        Event nonConflictingEvent = new EventBuilder().withStartEndDate(VALID_START_DATE_EARLIER,
                VALID_END_DATE_EARLIER).build();
        Event nonConflictingEventOther = new EventBuilder().withStartEndDate(VALID_START_DATE_LATER,
                VALID_END_DATE_LATER).build();

        assertThrows(NullPointerException.class, () -> validEvent.isConflicting(null));

        assertTrue(validEvent.isConflicting(conflictingEvent));

        assertTrue(validEvent.isConflicting(validEvent));

        assertFalse(nonConflictingEvent.isConflicting(nonConflictingEventOther));
    }

    @Test
    public void changeDescriptionTest() {
        EventDescription changeTo = new EventDescriptionBuilder().changeDescription(VALID_UNUSED_DESCRIPTION).build();
        Event original = new EventBuilder().build();

        assertThrows(NullPointerException.class, () -> original.changeDescription(null));

        original.changeDescription(changeTo);
        assertEquals(original.getDescription(), changeTo);
    }

    @Test
    public void changeEventPeriodTest() {
        EventPeriod changeTo = new EventPeriodBuilder().changeStartAndEnd(VALID_START_DATE_EARLIER,
                VALID_END_DATE_EARLIER).build();
        Event original = new EventBuilder().build();

        assertThrows(NullPointerException.class, () -> original.changeEventPeriod(null));

        original.changeEventPeriod(changeTo);
        assertEquals(original.getEventPeriod(), changeTo);
    }

    @Test
    public void getEventDaysTest() {
        assertFalse(new EventBuilder().build().getEventDays().isEmpty());
    }

    @Test
    public void equalsTest() {
        Event validEvent = new EventBuilder().build();
        Event equalEvent = new EventBuilder().build();
        Event differentDescriptionEvent = new EventBuilder().withDescription(VALID_UNUSED_DESCRIPTION).build();
        Event differentPeriodEvent = new EventBuilder().withStartEndDate(VALID_START_DATE_LATER, VALID_END_DATE_LATER)
                .build();
        Object nonEventObject = new Object();

        assertTrue(validEvent.equals(validEvent));

        assertTrue(validEvent.equals(equalEvent));

        assertFalse(validEvent.equals(nonEventObject));

        assertFalse(validEvent.equals(differentDescriptionEvent));

        assertFalse(validEvent.equals(differentPeriodEvent));
    }

    @Test
    public void compareStartTimeTest() {
        EventBuilder earlierEventBuilder = new EventBuilder();
        earlierEventBuilder.withStartEndDate(VALID_START_DATE_EARLIER, VALID_START_DATE_LATER);
        EventBuilder laterEventBuilder = new EventBuilder();
        laterEventBuilder.withStartEndDate(VALID_END_DATE_EARLIER, VALID_END_DATE_LATER);
        Event earlierEvent = earlierEventBuilder.build();
        Event laterEvent = laterEventBuilder.build();

        assertThrows(NullPointerException.class, () -> earlierEvent.compareStartTime(null));

        assertTrue(earlierEvent.compareStartTime(laterEvent) < 0);

        assertTrue(laterEvent.compareStartTime(earlierEvent) > 0);

        assertEquals(0, earlierEvent.compareStartTime(earlierEvent));
    }

    @Test
    public void compareEndTimeTest() {
        EventBuilder earlierEventBuilder = new EventBuilder();
        earlierEventBuilder.withStartEndDate(VALID_START_DATE_EARLIER, VALID_START_DATE_LATER);
        EventBuilder laterEventBuilder = new EventBuilder();
        laterEventBuilder.withStartEndDate(VALID_END_DATE_EARLIER, VALID_END_DATE_LATER);
        Event earlierEvent = earlierEventBuilder.build();
        Event laterEvent = laterEventBuilder.build();

        assertThrows(NullPointerException.class, () -> earlierEvent.compareEndTime(null));

        assertTrue(earlierEvent.compareEndTime(laterEvent) < 0);

        assertTrue(laterEvent.compareEndTime(earlierEvent) > 0);

        assertEquals(0, earlierEvent.compareEndTime(earlierEvent));
    }

    @Test
    public void getStartTimeTest() {
        Event event = new EventBuilder().build();
        assertNotNull(event.getStartTime());
    }

    @Test
    public void getEndTimeTest() {
        Event event = new EventBuilder().build();
        assertNotNull(event.getEndTime());
    }

    @Test
    public void getDurationOfEventTest() {
        Event event = new EventBuilder().build();
        assertNotNull(event.getDurationOfEvent());
    }

    @Test
    public void getDescriptionStringTest() {
        Event event = new EventBuilder().build();
        assertNotNull(event.getDescriptionString());
    }

    @Test
    public void getDayOfWeekTest() {
        EventBuilder nonSingleDayEventBuilder = new EventBuilder();
        nonSingleDayEventBuilder.withStartEndDate(VALID_START_DATE_EARLIER, VALID_END_DATE_LATER);
        Event nonSingleDayEvent = nonSingleDayEventBuilder.build();
        Event singleDayEvent = new EventBuilder().build();

        assertThrows(AssertionError.class, nonSingleDayEvent::getDayOfWeek);

        assertNotNull(singleDayEvent.getDayOfWeek());
    }

    @Test
    public void getMinutesFromTimeToStartTimeTest() {
        EventBuilder eventBuilder = new EventBuilder();
        Event event = eventBuilder.build();

        assertThrows(NullPointerException.class, () -> event.getMinutesFromTimeToStartTime(null));

        assertEquals(event.getMinutesFromTimeToStartTime(LocalTime.MIDNIGHT),
                MINUTES.between(LocalTime.MIDNIGHT, eventBuilder.getStartTime()));
    }
}
