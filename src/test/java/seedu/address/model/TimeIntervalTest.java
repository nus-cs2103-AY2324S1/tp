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
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalOneNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalTwoNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalThreeNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFourNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFiveNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalSixNoOverlap);
        assertFalse(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    //all time interval overlaps
    @Test
    public void isTimeIntervalOverlap_OverlapsA_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalOneOverlapA);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalTwoOverlapA);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalThreeOverlapA);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFourOverlapA);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFiveOverlapA);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_OneOverlapsA_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalOneOverlapA);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalThreeNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFourNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalTwoOverlapA);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFiveNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalSixNoOverlap);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_EqualOverlapsA_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalOneOverlapA);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFiveNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalSixNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalThreeOverlapA);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_InsideOutsideOverlapsB_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalOneOverlapB);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFourNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFiveNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalTwoOverlapB);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalSixNoOverlap);
        assertTrue(TimeInterval.isTimeIntervalOverlap(timeIntervalArrayList));
    }

    @Test
    public void isTimeIntervalOverlap_DiffDaysOverlapsC_returnTrue() {
        ArrayList<TimeInterval> timeIntervalArrayList = new ArrayList<>();
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalOneOverlapC);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFourNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalFiveNoOverlap);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalTwoOverlapC);
        timeIntervalArrayList.add(TypicalTimeIntervals.timeIntervalSixNoOverlap);
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
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2,00)).build();
        Time end =  new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(2,00)).build();
        assertTrue(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_validInputB_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.TUESDAY, LocalTime.of(23,59)).build();
        Time end =  new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(00,00)).build();
        assertTrue(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEqual_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2,00)).build();
        Time end =  new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2,00)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEndBeforeStart_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(12,00)).build();
        Time end =  new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(2,00)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEndBeforeStartA_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(12,00)).build();
        Time end =  new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(11,59)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }

    @Test
    public void isValidTimeIntervalLogic_inValidInputEndBeforeStartB_returnFalse() {
        Time start = new TimeBuilder().withDayAndHour(DayOfWeek.SUNDAY, LocalTime.of(12,00)).build();
        Time end =  new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(00,00)).build();
        assertFalse(TimeInterval.isValidTimeIntervalLogic(start, end));
    }













}
