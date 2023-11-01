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
                new Event("Sample", "00:01", "00:02", "Location", "Information"),
                new Event("Sample", "00:02", "00:03", "Location", "Information"));
    }

}
