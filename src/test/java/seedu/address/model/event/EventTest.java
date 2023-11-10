package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void test_eventEquals_pass() {
        assertEquals(
                new Event("Sample", "00:01", "00:02", "Location", "Information"),
                new Event("Sample", "00:01", "00:02", "Location", "Information"));
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample1", "00:01", "00:03", "Location", "Information"));
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample", "00:02", "00:03", "Location", "Information"));
        assertNotEquals(
                new Event("Sample", "00:01", "00:02", "Location", "Information"),
                new Event("Sample", "00:01", "00:03", "Location", "Information"));
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample", "00:01", "00:03", "Location1", "Information"));
        assertNotEquals(
                new Event("Sample", "00:01", "00:03", "Location", "Information"),
                new Event("Sample", "00:01", "00:03", "Location", "Information1"));
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
