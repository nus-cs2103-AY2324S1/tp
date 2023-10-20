package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class EventTimeTest {

    @Test
    public void factoryMethod_null_throwsParseException() {
        assertThrows(ParseException.class, () -> EventTime.of(null));
    }


    @Test
    public void equals() throws ParseException {
        EventTime eventTime = EventTime.of("1200");

        // same values -> returns true
        assertTrue(eventTime.equals(EventTime.of("1200")));

        // same object -> returns true
        assertTrue(eventTime.equals(eventTime));

        // null -> returns false
        assertFalse(eventTime.equals(null));

        // different types -> returns false
        assertFalse(eventTime.equals(5.0f));

        // different values -> returns false
        assertFalse(eventTime.equals(EventTime.of("1300")));
    }

    @Test
    public void isValidEventTime() {
        // invalid times
        assertFalse(() -> EventTime.isValidTime("2400")); // invalid time
        assertFalse(() -> EventTime.isValidTime("123")); // invalid time

        // valid times
        assertTrue(() -> EventTime.isValidTime("")); // when time is not given
        assertTrue(() -> EventTime.isValidTime(" ")); // spaces only
        assertTrue(() -> EventTime.isValidTime("0000")); // valid time
        assertTrue(() -> EventTime.isValidTime("2359")); // valid time
        assertTrue(() -> EventTime.isValidTime("1200")); // valid time
    }

    @Test
    public void toStringTest() throws ParseException {
        EventTime eventTime = EventTime.of("1200");
        assertTrue(eventTime.toString().equals("1200"));
    }

    @Test
    public void forDisplayTest() throws ParseException {
        EventTime eventTime = EventTime.of("1200");
        assertTrue(eventTime.forDisplay().equals("12:00"));
    }

    @Test
    public void ofEventTimeTest() throws ParseException {
        EventTime eventTime = EventTime.of("1200");
        assertTrue(eventTime.toString().equals("1200"));


        assertEquals("0000", eventTime.of("").toString()); // empty time
        assertEquals("0000", eventTime.of(" ").toString()); // spaces only
        assertEquals("1200", eventTime.of("1200").toString()); // 1200
    }

    @Test
    public void getEventTimeTest() throws ParseException {
        EventTime eventTime = EventTime.of("1200");
        assertTrue(eventTime.getEventTime().equals(LocalTime.of(12, 0)));
    }
}
