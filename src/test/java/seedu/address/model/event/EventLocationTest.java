package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventLocationTest {

    @Test
    public void isValidEventLocation() {
        // blank event location
        assertFalse(EventLocation.isValidEventLocation(""));

        // valid event location
        assertTrue(EventLocation.isValidEventLocation("Blk 456, Den Road, #01-355"));
        assertTrue(EventLocation.isValidEventLocation("-"));
        assertTrue(EventLocation.isValidEventLocation("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }

    @Test
    public void test_eventLocationEquals_pass() {
        EventLocation location = EventLocation.fromString("SampleLocation0");

        // same object --> returns true
        assertTrue(location.equals(location));

        // same values --> returns true
        assertTrue(EventLocation.fromString("SampleLocation")
                .equals(EventLocation.fromString("SampleLocation")));

        // different types --> returns false
        assertFalse(EventLocation.fromString("SampleLocation").equals(10));

        // different values --> returns false
        assertFalse(
                EventLocation.fromString("SampleLocation1")
                        .equals(EventLocation.fromString("SampleLocation2")));

        // empty object -> returns false
        assertFalse(
                EventLocation.fromString("SampleLocation1").equals(new Object()));
    }

}
