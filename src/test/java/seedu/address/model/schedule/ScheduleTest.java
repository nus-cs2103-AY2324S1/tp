package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ONE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_TWO_SECOND_JAN;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

class ScheduleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleBuilder(SCHEDULE_ONE_FIRST_JAN)
                .withTutorIndex(null)
                .build());
        assertThrows(NullPointerException.class, () -> new ScheduleBuilder(SCHEDULE_ONE_FIRST_JAN)
                .withStartTime(null)
                .build());
        assertThrows(NullPointerException.class, () -> new ScheduleBuilder(SCHEDULE_ONE_FIRST_JAN)
                .withEndTime(null)
                .build());
    }

    @Test
    public void constructor_startTimeAfterEndTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ScheduleBuilder()
                .withStartTime(LocalDateTime.of(2023, 1, 2, 0, 0, 0))
                .withEndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .build());
    }

    @Test
    public void constructor_startTimeEqualsEndTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ScheduleBuilder()
                .withStartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .withEndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .build());
    }

    @Test
    void testEquals() {
        // same values -> returns true
        Schedule defaultCopy = new ScheduleBuilder(SCHEDULE_ONE_FIRST_JAN).build();
        assertTrue(SCHEDULE_ONE_FIRST_JAN.equals(defaultCopy));

        // same object -> returns true
        assertTrue(SCHEDULE_ONE_FIRST_JAN.equals(SCHEDULE_ONE_FIRST_JAN));

        // null -> returns false
        assertFalse(SCHEDULE_ONE_FIRST_JAN.equals(null));

        // different type -> returns false
        assertFalse(SCHEDULE_ONE_FIRST_JAN.equals(5));

        // different person -> returns false
        assertFalse(SCHEDULE_ONE_FIRST_JAN.equals(SCHEDULE_TWO_SECOND_JAN));

        // different tutor index -> returns false
        Schedule editedOne = new ScheduleBuilder(SCHEDULE_ONE_FIRST_JAN).withTutorIndex(5).build();
        assertFalse(SCHEDULE_ONE_FIRST_JAN.equals(editedOne));

        // different start time -> returns false
        editedOne = new ScheduleBuilder(SCHEDULE_ONE_FIRST_JAN)
                .withStartTime(LocalDateTime.of(2022, 12, 31, 10, 0, 0))
                .build();
        assertFalse(SCHEDULE_ONE_FIRST_JAN.equals(editedOne));

        // different end time -> returns false
        editedOne = new ScheduleBuilder(SCHEDULE_ONE_FIRST_JAN)
                .withEndTime(LocalDateTime.of(2023, 1, 2, 10, 0, 0))
                .build();
        assertFalse(SCHEDULE_ONE_FIRST_JAN.equals(editedOne));
    }

    @Test
    public void toStringMethod() {
        String expected = Schedule.class.getCanonicalName() + "{tutorIndex=" + SCHEDULE_ONE_FIRST_JAN.getTutorIndex()
                + ", startTime=" + SCHEDULE_ONE_FIRST_JAN.getStartTime()
                + ", endTime=" + SCHEDULE_ONE_FIRST_JAN.getEndTime() + "}";
        assertEquals(expected, SCHEDULE_ONE_FIRST_JAN.toString());
    }
}
