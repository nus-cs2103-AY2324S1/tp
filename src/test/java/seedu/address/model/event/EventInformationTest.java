package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventInformationTest {
    @Test
    public void test_eventInformationEquals_pass() {
        EventInformation info = EventInformation.fromString("SampleInformation0");
        assertTrue(info.equals(info));
        assertTrue(EventInformation.fromString("SampleInformation")
                .equals(EventInformation.fromString("SampleInformation")));
        assertFalse(
                EventInformation.fromString("SampleInformation1")
                .equals(EventInformation.fromString("SampleInformation2")));
        assertFalse(
                EventInformation.fromString("SampleInformation1").equals(new Object()));
    }

}
