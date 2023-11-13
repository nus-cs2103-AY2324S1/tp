package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.EventBuilder.DEFAULT_START_TIME_STRING;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class AllDaysEventListManagerTest {
    private static Event sample = new EventBuilder().build();
    private static LocalDateTime eventTime = LocalDateTime.parse(DEFAULT_START_TIME_STRING, DATE_TIME_STRING_FORMATTER);

    private AllDaysEventListManager eventListManager = new AllDaysEventListManager();

    @Test
    public void constructorTest() {
    }

    @Test
    public void canAddEventTest() {
        eventListManager.clear();
        assertTrue(eventListManager.canAddEvent(sample));
    }

    @Test
    public void clearTest() {
        eventListManager.clear();
        assertTrue(eventListManager.isEmpty());
        eventListManager.addEvent(sample);
        eventListManager.clear();
        assertTrue(eventListManager.isEmpty());
    }

    @Test
    public void hasEventTest() {
        eventListManager.clear();
        assertFalse(eventListManager.hasEvents());
        eventListManager.addEvent(sample);
        assertTrue(eventListManager.hasEvents());
        eventListManager.deleteEventAt(eventTime);
        assertFalse(eventListManager.hasEvents());
    }

    @Test
    public void eventAtTest() {
        eventListManager.clear();
        assertTrue(eventListManager.eventAt(eventTime).isEmpty());
        eventListManager.addEvent(sample);
        assertEquals(eventListManager.eventAt(eventTime).get(), sample);
    }

    @Test
    public void canAddEvent_eventOverlap_unsuccessful() {
        eventListManager.clear();
        eventListManager.addEvent(sample);
        assertFalse(eventListManager.canAddEvent(sample));
    }
}
