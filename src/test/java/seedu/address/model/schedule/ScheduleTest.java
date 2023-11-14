package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_BOB_SECOND_JAN;

import java.time.LocalDate;
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
                .withStartTime(LocalDateTime.of(2023, 1, 1, 10, 0, 0))
                .withEndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .build());
    }

    @Test
    public void constructor_differentDayStartTimeBeforeEndTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ScheduleBuilder()
                .withStartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .withEndTime(LocalDateTime.of(2023, 1, 2, 0, 0, 0))
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
    public void testIsDuplicate() {
        final LocalDateTime aliceStartDateTime = SCHEDULE_ALICE_FIRST_JAN.getStartTime().getTime();
        final LocalDateTime aliceEndDateTime = SCHEDULE_ALICE_FIRST_JAN.getEndTime().getTime();
        // null -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> SCHEDULE_ALICE_FIRST_JAN.isClashing((Schedule) null));

        // same values -> returns true
        Schedule scheduleCopy = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).build();
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isDuplicate(scheduleCopy));
        assertTrue(scheduleCopy.isDuplicate(SCHEDULE_ALICE_FIRST_JAN));

        // same schedule, different status -> returns true
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isDuplicate(SCHEDULE_ALICE_FIRST_JAN));

        // same schedule, different status -> returns true
        scheduleCopy = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withStatus(Status.COMPLETED).build();
        assertTrue(SCHEDULE_ALICE_FIRST_JAN.isDuplicate(scheduleCopy));

        // different tutor, same times -> returns false
        Schedule bobSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).withTutor(TypicalPersons.BOB).build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isDuplicate(bobSchedule));
        assertFalse(bobSchedule.isDuplicate(SCHEDULE_ALICE_FIRST_JAN));

        // different tutor, different times -> returns false
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isDuplicate(SCHEDULE_BOB_SECOND_JAN));

        // same tutor, non-clashing schedules -> returns false
        Schedule beforeSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
            .withStartTime(aliceStartDateTime.minusHours(2))
            .withEndTime(aliceStartDateTime.minusHours(1))
            .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isDuplicate(beforeSchedule));
        assertFalse(beforeSchedule.isDuplicate(SCHEDULE_ALICE_FIRST_JAN));

        // same tutor, both times overlapping -> return false
        Schedule otherSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
            .withStartTime(aliceStartDateTime.plusSeconds(1))
            .withEndTime(aliceEndDateTime.minusSeconds(1))
            .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.isDuplicate(otherSchedule));
        assertFalse(otherSchedule.isDuplicate(SCHEDULE_ALICE_FIRST_JAN));
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
                .withStartTime(SCHEDULE_ALICE_FIRST_JAN.getStartTime().getTime().minusHours(1))
                .build();
        assertFalse(SCHEDULE_ALICE_FIRST_JAN.equals(editedOne));

        // different end time -> returns false
        editedOne = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN)
                .withEndTime(SCHEDULE_ALICE_FIRST_JAN.getStartTime().getTime().plusHours(1))
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
    void testCompareTo() {
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime tomorrow = today.plusDays(1);
        LocalDateTime yesterday = today.minusDays(1);

        // Same schedule
        Schedule schedule =
                new ScheduleBuilder().withStartTime(today).withEndTime(today.withHour(1)).build();

        assertTrue(schedule.compareTo(schedule) == 0);

        Schedule copy = new ScheduleBuilder(schedule).build();

        assertTrue(schedule.compareTo(copy) == 0);

        // Both schedules before today
        Schedule ealierSchedule =
                new ScheduleBuilder().withStartTime(yesterday.withHour(9)).withEndTime(yesterday.withHour(11)).build();

        Schedule laterSchedule =
                new ScheduleBuilder().withStartTime(yesterday.withHour(11)).withEndTime(yesterday.withHour(13)).build();

        assertTrue(ealierSchedule.compareTo(laterSchedule) > 0);
        assertTrue(laterSchedule.compareTo(ealierSchedule) < 0);

        copy = new ScheduleBuilder(ealierSchedule).build();

        assertTrue(ealierSchedule.compareTo(copy) == 0);

        // Both schedules from today

        ealierSchedule =
                new ScheduleBuilder().withStartTime(today.withHour(9)).withEndTime(today.withHour(11)).build();

        laterSchedule =
                new ScheduleBuilder().withStartTime(today.withHour(11)).withEndTime(today.withHour(13)).build();

        assertTrue(ealierSchedule.compareTo(laterSchedule) < 0);
        assertTrue(laterSchedule.compareTo(ealierSchedule) > 0);

        copy = new ScheduleBuilder(ealierSchedule).build();

        assertTrue(ealierSchedule.compareTo(copy) == 0);

        // Both schedules after today

        ealierSchedule =
                new ScheduleBuilder().withStartTime(tomorrow.withHour(9)).withEndTime(tomorrow.withHour(11)).build();

        laterSchedule =
                new ScheduleBuilder().withStartTime(tomorrow.withHour(11)).withEndTime(tomorrow.withHour(13)).build();

        assertTrue(ealierSchedule.compareTo(laterSchedule) < 0);
        assertTrue(laterSchedule.compareTo(ealierSchedule) > 0);

        copy = new ScheduleBuilder(ealierSchedule).build();

        assertTrue(ealierSchedule.compareTo(copy) == 0);

        // One before, one after

        ealierSchedule =
                new ScheduleBuilder().withStartTime(yesterday.withHour(9)).withEndTime(yesterday.withHour(11)).build();

        laterSchedule =
                new ScheduleBuilder().withStartTime(tomorrow.withHour(11)).withEndTime(tomorrow.withHour(13)).build();

        assertTrue(ealierSchedule.compareTo(laterSchedule) > 0);
        assertTrue(laterSchedule.compareTo(ealierSchedule) < 0);

        // One on, one after

        ealierSchedule =
                new ScheduleBuilder().withStartTime(today.withHour(9)).withEndTime(today.withHour(11)).build();

        laterSchedule =
                new ScheduleBuilder().withStartTime(tomorrow.withHour(11)).withEndTime(tomorrow.withHour(13)).build();

        assertTrue(ealierSchedule.compareTo(laterSchedule) < 0);
        assertTrue(laterSchedule.compareTo(ealierSchedule) > 0);

        // One on, one before

        ealierSchedule =
                new ScheduleBuilder().withStartTime(yesterday.withHour(9)).withEndTime(yesterday.withHour(11)).build();

        laterSchedule =
                new ScheduleBuilder().withStartTime(today.withHour(11)).withEndTime(today.withHour(13)).build();

        assertTrue(ealierSchedule.compareTo(laterSchedule) > 0);
        assertTrue(laterSchedule.compareTo(ealierSchedule) < 0);
    }

    @Test
    public void testIsOnDate() {
        LocalDateTime sameDay = LocalDateTime.of(2023, 1, 1, 0, 0, 0);

        Schedule schedule1 =
            new ScheduleBuilder().withStartTime(sameDay).withEndTime(sameDay.withHour(1)).build();

        assertFalse(schedule1.isOnDate(new Date(LocalDate.of(2022, 1, 1)))); // different year
        assertFalse(schedule1.isOnDate(new Date(LocalDate.of(2023, 2, 1)))); // different month
        assertFalse(schedule1.isOnDate(new Date(LocalDate.of(2023, 1, 2)))); // different day
        assertTrue(schedule1.isOnDate(new Date(LocalDate.of(2023, 1, 1)))); // same date
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
