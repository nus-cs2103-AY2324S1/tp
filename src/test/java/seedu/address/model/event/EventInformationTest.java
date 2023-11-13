package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventInformationTest {

    @Test
    public void isValidEventInformation() {
        // blank event information
        assertFalse(EventInformation.isValidEventInformation(""));

        // valid event information
        assertTrue(EventInformation.isValidEventInformation("Google Developer Intern interview"));
        assertTrue(EventInformation.isValidEventInformation("Catching up session"));
        assertTrue(EventInformation.isValidEventInformation("TikTok office visit"));
    }

    @Test
    public void test_eventInformationEquals_pass() {
        EventInformation info = EventInformation.fromString("SampleInformation0");

        // same object --> returns true
        assertTrue(info.equals(info));

        // same values --> returns true
        assertTrue(EventInformation.fromString("SampleInformation")
                .equals(EventInformation.fromString("SampleInformation")));

        // different types --> returns false
        assertFalse(
                EventInformation.fromString("SampleInformation1").equals(50));

        // different values --> returns false
        assertFalse(
                EventInformation.fromString("SampleInformation1")
                .equals(EventInformation.fromString("SampleInformation2")));

        // empty object --> returns false
        assertFalse(
                EventInformation.fromString("SampleInformation1").equals(new Object()));
    }

}
