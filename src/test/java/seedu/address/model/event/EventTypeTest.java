package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventType(null));
    }

    @Test
    public void toStringTest() {
        EventType eventType = new EventType("meeting");
        assertEquals(eventType.toString(), "meeting");
    }
}
