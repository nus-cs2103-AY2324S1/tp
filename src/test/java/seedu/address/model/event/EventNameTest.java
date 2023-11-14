package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventNameTest {
    @Test
    public void isValidEventName() {
        // blank event name
        assertFalse(EventName.isValidEventName(""));

        // valid event name
        assertTrue(EventName.isValidEventName("Interview"));
        assertTrue(EventInformation.isValidEventInformation("Chatting with Recruiter"));
        assertTrue(EventName.isValidEventName("Meetup with Friends"));
    }

    @Test
    public void test_eventLocationEquals_pass() {
        EventName name = EventName.fromString("SampleName0");

        // same object --> returns true
        assertTrue(name.equals(name));

        // same values --> returns true
        assertTrue(EventName.fromString("SampleName")
                .equals(EventName.fromString("SampleName")));

        // different types --> returns false
        assertFalse(EventName.fromString("SampleName1").equals(50));

        // different values --> returns false
        assertFalse(
                EventName.fromString("SampleName1")
                        .equals(EventName.fromString("SampleName2")));

        // empty object --> returns false
        assertFalse(
                EventName.fromString("SampleName1").equals(new Object()));
    }

}
