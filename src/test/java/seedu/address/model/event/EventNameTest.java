package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventNameTest {
    @Test
    public void test_eventLocationEquals_pass() {
        EventName name = EventName.fromString("SampleName0");
        assertTrue(name.equals(name));
        assertTrue(EventLocation.fromString("SampleName")
                .equals(EventLocation.fromString("SampleName")));
        assertFalse(
                EventLocation.fromString("SampleName1")
                        .equals(EventLocation.fromString("SampleName2")));
        assertFalse(
                EventLocation.fromString("SampleName1").equals(new Object()));
    }

}
