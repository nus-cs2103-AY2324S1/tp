package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventLocationTest {
    @Test
    public void test_eventLocationEquals_pass() {
        EventLocation location = EventLocation.fromString("SampleLocation0");
        assertTrue(location.equals(location));
        assertTrue(EventLocation.fromString("SampleLocation")
                .equals(EventLocation.fromString("SampleLocation")));
        assertFalse(
                EventLocation.fromString("SampleLocation1")
                        .equals(EventLocation.fromString("SampleLocation2")));
        assertFalse(
                EventLocation.fromString("SampleLocation1").equals(new Object()));
    }

}
