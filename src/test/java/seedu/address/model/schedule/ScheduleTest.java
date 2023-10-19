package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_BOB_SECOND_JAN;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TypicalPersons;

class ScheduleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withTutor(null)
                .build());
        assertThrows(NullPointerException.class, () -> new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(null)
                .build());
        assertThrows(NullPointerException.class, () -> new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
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
    public void testIsClashing() {
        final LocalDateTime aliceStartDateTime = SCHEDULE_ALICE_FIRST_JAN.getStartTime().getTime();
        final LocalDateTime aliceEndDateTime = SCHEDULE_ALICE_FIRST_JAN.getEndTime().getTime();
        // null -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> SCHEDULE_ALICE_FIRST_JAN.isClashing((Schedule) null));

        // same values -> returns true
        Schedule scheduleCopy = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).build();
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isClashing(scheduleCopy));
        assertTrue(scheduleCopy.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        // same schedule -> returns true
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        // different tutor, same times -> returns false
        Schedule bobSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withTutor(TypicalPersons.BOB).build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isClashing(bobSchedule));
        assertFalse(bobSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        // different tutor, different times -> returns false
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isClashing(SCHEDULE_BOB_SECOND_JAN));

        // same tutor, non-clashing schedules -> returns false
        Schedule beforeSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceStartDateTime.minusHours(2))
                .withEndTime(aliceStartDateTime.minusHours(1))
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isClashing(beforeSchedule));
        assertFalse(beforeSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        Schedule afterSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceEndDateTime.plusHours(1))
                .withEndTime(aliceEndDateTime.plusHours(2))
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isClashing(afterSchedule));
        assertFalse(afterSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        Schedule nextDaySchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceStartDateTime.plusDays(1))
                .withEndTime(aliceEndDateTime.plusDays(1))
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isClashing(nextDaySchedule));
        assertFalse(nextDaySchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        // same tutor, adjacent times -> return false
        beforeSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceStartDateTime.minusHours(2))
                .withEndTime(aliceStartDateTime)
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isClashing(beforeSchedule));
        assertFalse(beforeSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        afterSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceEndDateTime)
                .withEndTime(aliceEndDateTime.plusHours(2))
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isClashing(afterSchedule));
        assertFalse(afterSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        // same tutor, end time overlapping -> return true
        Schedule otherSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceStartDateTime.minusHours(1))
                .withEndTime(aliceStartDateTime.plusSeconds(1))
                .build();
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isClashing(otherSchedule));
        assertTrue(otherSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        // same tutor, start time overlapping -> return true
        otherSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceEndDateTime.minusSeconds(1))
                .withEndTime(aliceEndDateTime.plusHours(2))
                .build();
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isClashing(otherSchedule));
        assertTrue(otherSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));

        // same tutor, both times overlapping -> return true
        otherSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(aliceStartDateTime.plusSeconds(1))
                .withEndTime(aliceEndDateTime.minusSeconds(1))
                .build();
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isClashing(otherSchedule));
        assertTrue(otherSchedule.isClashing(SCHEDULE_ALICE_FIRST_JAN));
    }


    @Test
    void testEquals() {
        // same values -> returns true
        Schedule defaultCopy = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).build();
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.equals(defaultCopy));

        // same object -> returns true
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.equals(SCHEDULE_ALICE_FIRST_JAN));

        // null -> returns false
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(null));

        // different type -> returns false
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(5));

        // different schedule -> returns false
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(SCHEDULE_BOB_SECOND_JAN));

        // different tutor index -> returns false
        Schedule editedOne = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withTutor(TypicalPersons.BOB).build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(editedOne));

        // different start time -> returns false
        editedOne = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withStartTime(LocalDateTime.of(2022, 12, 31, 10, 0, 0))
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(editedOne));

        // different end time -> returns false
        editedOne = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withEndTime(LocalDateTime.of(2023, 1, 2, 10, 0, 0))
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(editedOne));

        // different status -> returns false
        editedOne = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
            .withStatus(Status.COMPLETED)
            .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(editedOne));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(SCHEDULE_ALICE_FIRST_JAN.hashCode(), new ScheduleBuilder()
            .withTutor(TypicalPersons.ALICE)
            .withStartTime(LocalDateTime.of(2023, 1, 1, 9, 0, 0))
            .withEndTime(LocalDateTime.of(2023, 1, 1, 11, 0, 0))
            .withStatus(Status.PENDING)
            .build().hashCode());

        // same values -> returns same hashcode
        assertNotEquals(SCHEDULE_BOB_SECOND_JAN.hashCode(), new ScheduleBuilder()
            .withTutor(TypicalPersons.ALICE)
            .withStartTime(LocalDateTime.of(2023, 1, 1, 9, 0, 0))
            .withEndTime(LocalDateTime.of(2023, 1, 1, 11, 0, 0))
            .withStatus(Status.PENDING)
            .build().hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Schedule.class.getCanonicalName() + "{tutor=" + SCHEDULE_ALICE_FIRST_JAN.getTutor()
                + ", startTime=" + SCHEDULE_ALICE_FIRST_JAN.getStartTime()
                + ", endTime=" + SCHEDULE_ALICE_FIRST_JAN.getEndTime()
                + ", status=" + SCHEDULE_ALICE_FIRST_JAN.getStatus() + "}";
        assertEquals(expected, SCHEDULE_ALICE_FIRST_JAN.toString());
    }
}
