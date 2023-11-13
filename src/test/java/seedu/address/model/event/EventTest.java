package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void test_eventEquals_pass() {
        // same values --> returns true
        assertEquals(
                new Event("Sample", "00:01", "00:02", "Location", "Information"),
                new Event("Sample", "00:01", "00:02", "Location", "Information"));

        // different name --> returns false
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample1", "00:01", "00:03", "Location", "Information"));

        // different start time --> returns false
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample", "00:02", "00:03", "Location", "Information"));

        // different end time --> returns false
        assertNotEquals(
                new Event("Sample", "00:01", "00:02", "Location", "Information"),
                new Event("Sample", "00:01", "00:03", "Location", "Information"));

        // different location --> returns false
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample", "00:01", "00:03", "Location1", "Information"));

        // different information --> returns false
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample", "00:01", "00:03", "Location", "Information1"));
    }

    @Test public void equals() {
        Event sampleEvent = new Event("Sample", "00:01", "00:02", "Location", "Information");

        // same values --> returns true
        assertTrue(sampleEvent.equals(
                new Event("Sample", "00:01", "00:02", "Location", "Information")
        ));

        // same object --> returns true
        assertTrue(sampleEvent.equals(sampleEvent));

        // empty object --> returns false
        assertFalse(sampleEvent.equals(new Object()));

        // different name --> returns false
        assertFalse(sampleEvent.equals(
                new Event("Sample1", "00:01", "00:03", "Location", "Information")
        ));

        // different start time --> returns false
        assertFalse(sampleEvent.equals(
                new Event("Sample", "00:02", "00:03", "Location", "Information")
        ));

        // different end time --> returns false
        assertFalse(sampleEvent.equals(
                new Event("Sample", "00:01", "00:03", "Location", "Information")
        ));

        // different location --> returns false
        assertFalse(sampleEvent.equals(
                new Event("Sample", "00:01", "00:03", "Location1", "Information")
        ));

        // different information --> returns false
        assertFalse(sampleEvent.equals(
                new Event("Sample", "00:01", "00:03", "Location", "Information1")
        ));
    }

    @Test
    public void test_getStringData_pass() {
        Event event = new Event("Sample", "2023-11-10 00:01:00", "2023-11-10 00:02:00",
                "Location", "Information");
        assertEquals(event.getName(), "Sample");
        assertEquals(event.getStartString(), "2023-11-10 00:01:00");
        assertEquals(event.getEndString(), "2023-11-10 00:02:00");
        assertEquals(event.getLocationStr(), "Location");
        assertEquals(event.getInformationStr(), "Information");
    }

}
