package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalMeetings.MEETING_3_DAYS_AFTER_TODAY;
import static seedu.address.testutil.TypicalMeetings.MEETING_6_DAYS_AFTER_TODAY;
import static seedu.address.testutil.TypicalMeetings.MEETING_LATER_THAN_TP_MEETING;
import static seedu.address.testutil.TypicalMeetings.MEETING_WITHOUT_END_TIME;
import static seedu.address.testutil.TypicalMeetings.MEETING_WITHOUT_START_TIME;
import static seedu.address.testutil.TypicalMeetings.MEETING_WITHOUT_TIME;
import static seedu.address.testutil.TypicalMeetings.TP_MEETING;
import static seedu.address.testutil.TypicalMeetings.TP_MEETING_EARLIER_END_TIME;
import static seedu.address.testutil.TypicalMeetings.TP_MEETING_LATER_START_TIME;

import org.junit.jupiter.api.Test;

public class EventComparatorTest {

    @Test
    public void compareTest() {
        EventComparator eventComparator = new EventComparator();
        assertEquals(-1, eventComparator.compare(TP_MEETING, MEETING_LATER_THAN_TP_MEETING));
        assertEquals(0, eventComparator.compare(TP_MEETING, TP_MEETING));
        assertEquals(1, eventComparator.compare(MEETING_LATER_THAN_TP_MEETING, TP_MEETING));
    }

    @Test
    public void compareTestWithoutTime() {
        EventComparator eventComparator = new EventComparator();

        assertEquals(-1, eventComparator.compare(TP_MEETING, MEETING_WITHOUT_TIME));
        assertEquals(1, eventComparator.compare(MEETING_WITHOUT_TIME, TP_MEETING));
    }

    @Test
    public void compareTestBothDatesWithoutTime() {
        EventComparator eventComparator = new EventComparator();

        assertEquals(-1, eventComparator.compare(MEETING_3_DAYS_AFTER_TODAY, MEETING_6_DAYS_AFTER_TODAY));
        assertEquals(1, eventComparator.compare(MEETING_6_DAYS_AFTER_TODAY, MEETING_3_DAYS_AFTER_TODAY));
    }

    @Test
    public void compareTestWithSameDateAndDifferentStartTime() {
        EventComparator eventComparator = new EventComparator();
        assertEquals(-1, eventComparator.compare(TP_MEETING, TP_MEETING_LATER_START_TIME));
        assertEquals(1, eventComparator.compare(TP_MEETING_LATER_START_TIME, TP_MEETING));
    }

    @Test
    public void compareTestWithSameDateAndDifferentEndTime() {
        EventComparator eventComparator = new EventComparator();
        assertEquals(-1, eventComparator.compare(TP_MEETING_EARLIER_END_TIME, TP_MEETING));
        assertEquals(1, eventComparator.compare(TP_MEETING, TP_MEETING_EARLIER_END_TIME));
    }

    @Test
    public void compareTestWithoutStartTimeAndWithoutEndTime() {
        EventComparator eventComparator = new EventComparator();

        assertEquals(-1, eventComparator.compare(MEETING_WITHOUT_END_TIME, MEETING_WITHOUT_START_TIME));
        assertEquals(1, eventComparator.compare(MEETING_WITHOUT_START_TIME, MEETING_WITHOUT_END_TIME));
    }

    @Test
    public void compareTestWithStartTimeAndWithoutStartTIme() {
        EventComparator eventComparator = new EventComparator();

        assertEquals(-1, eventComparator.compare(TP_MEETING, MEETING_WITHOUT_START_TIME));
        assertEquals(1, eventComparator.compare(MEETING_WITHOUT_START_TIME, TP_MEETING));
    }

    @Test
    public void compareTestWithEndTimeAndWithoutEndTime() {
        EventComparator eventComparator = new EventComparator();

        assertEquals(-1, eventComparator.compare(TP_MEETING, MEETING_WITHOUT_END_TIME));
        assertEquals(1, eventComparator.compare(MEETING_WITHOUT_END_TIME, TP_MEETING));
    }


}
