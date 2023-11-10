package seedu.address.model.event.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.EVENT_OVERLAP;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;

public class EventOverlapExceptionTest {
    @Test
    public void test_createException_pass() {
        Event event1 = new Event("name1", "00:01:00", "00:02:00", "location1",
                "information1");
        Event event2 = new Event("name2", "00:00:00", "00:03:00", "location2",
                "information2");
        assertEquals(new EventOverlapException(event1, event2).getMessage(),
                String.format(EVENT_OVERLAP, event1.getUiText(), event2.getUiText()));
    }
}
