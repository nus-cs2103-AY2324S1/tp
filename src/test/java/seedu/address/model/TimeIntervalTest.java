package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TimeBuilder;
import seedu.address.testutil.TypicalTimeIntervals;

public class TimeIntervalTest {

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeInterval(null, null));
    }

    @Test
    public void isTimeIntervalOverlap_noOverlaps_returnFalse() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FIVE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_SIX_NO_OVERLAP);
        assertFalse(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    //all time interval overlaps
    @Test
    public void isTimeIntervalOverlap_overlapsA_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FOUR_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_oneOverlapsA_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FIVE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_SIX_NO_OVERLAP);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_equalOverlapsA_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FIVE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_SIX_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_insideOutsideOverlapsB_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_B);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FIVE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_B);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_SIX_NO_OVERLAP);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_diffDaysOverlapsC_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_C);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_FIVE_NO_OVERLAP);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_C);
        timeIntervalArrayList.add(TypicalTimeIntervals.TIME_INTERVAL_SIX_NO_OVERLAP);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isValidTimeIntervalSyntax_validInput_returnTrue() {
        String input = "mon 1200 - fri 1300";
        assertTrue(TimeInterval.isValidTimeIntervalSyntax(input));
    }

    @Test
    public void isValidTimeIntervalSyntax_inValidInput_returnFalse() {
        String input = "mon 1200 - ";
        assertFalse(TimeInterval.isValidTimeIntervalSyntax(input));
    }

    @Test
    public void isValidTimeIntervalSyntax_inValidInputA_returnFalse() {
        String input = "mon 1200  fri 2200";
        assertFalse(TimeInterval.isValidTimeIntervalSyntax(input));
    }

    @Test
    public void isValidTimeIntervalSyntax_inValidInputB_returnFalse() {
        String input = "mon1200 - fri 2200";
        assertFalse(TimeInterval.isValidTimeIntervalSyntax(input));
    }

    @Test
    public void isValidTimeIntervalSyntax_inValidInputC_returnFalse() {
        String input = "mon 1300 - fri";
        assertFalse(TimeInterval.isValidTimeIntervalSyntax(input));
    }

    @Test
    public void isValidTimeIntervalLogic_validInput_returnTrue() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2, 0)).build();
        Time end = new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(2, 0)).build();
        assertTrue(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_validInputB_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.TUESDAY, LocalTime.of(23, 59)).build();
        Time end = new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(0, 0)).build();
        assertTrue(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEqual_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2, 0)).build();
        Time end = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2, 0)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEndBeforeStart_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(12, 0)).build();
        Time end = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2, 0)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEndBeforeStartA_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(12, 0)).build();
        Time end = new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(11, 59)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEndBeforeStartB_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.SUNDAY, LocalTime.of(12, 0)).build();
        Time end = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(0, 0)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void execute_sameDay_returnTrue() {
        TimeInterval timeInterval = TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A; // MON 1000 - MON 1100
        Duration durationWithin = new Duration(59);
        Duration durationEqual = new Duration(60);
        Duration durationExceed = new Duration(61);
        assertTrue(timeInterval.allows(durationWithin));
        assertTrue(timeInterval.allows(durationEqual));
        assertFalse(timeInterval.allows(durationExceed));
    }


    @Test
    public void execute_differentDay_returnTrue() {
        TimeInterval timeInterval = TypicalTimeIntervals.TIME_INTERVAL_MONTUE_ONE; // MON 1100 - TUE 1400 is 27 Hours
        Duration durationWithin = new Duration(1619);
        Duration durationEqual = new Duration(1620);
        Duration durationExceed = new Duration(1621);
        assertTrue(timeInterval.allows(durationWithin));
        assertTrue(timeInterval.allows(durationEqual));
        assertFalse(timeInterval.allows(durationExceed));

    }




}
