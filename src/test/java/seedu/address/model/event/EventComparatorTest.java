package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalMeetings.MEETING_LATER_THAN_TP_MEETING;
import static seedu.address.testutil.TypicalMeetings.TP_MEETING;

import org.junit.jupiter.api.Test;

public class EventComparatorTest {

    @Test
    public void compareTest() {
        EventComparator eventComparator = new EventComparator();
        assertEquals(1, eventComparator.compare(TP_MEETING, MEETING_LATER_THAN_TP_MEETING));
        assertEquals(0, eventComparator.compare(TP_MEETING, TP_MEETING));
        assertEquals(-1, eventComparator.compare(MEETING_LATER_THAN_TP_MEETING, TP_MEETING));
    }
}
