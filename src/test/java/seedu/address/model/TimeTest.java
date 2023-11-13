package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalTimeIntervals;



public class TimeTest {

    @Test
    public void is_validTime_true() {
        String timeStringInvalid = "I am not valid";
        String timeStringValid = "mon 1200";
        assertFalse(Time.isValidTime(timeStringInvalid));
        assertTrue(Time.isValidTime(timeStringValid));
    }

    @Test
    public void getDuration_true() {
        Time time = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getStart(); // MON 2200
        Time timeSecond = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getEnd(); // MON 2300
        assertTrue(time.getDurationInMin() == 2760);
        assertTrue(timeSecond.getDurationInMin() == 2820);
    }

    @Test
    public void compareTo_true() {
        Time time = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getStart(); // MON 2200
        Time timeSecond = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getEnd(); // MON 2300
        assertTrue(time.compareTo(timeSecond) < 0);
    }


    @Test
    public void toString_true() {
        Time time = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getStart(); // MON 2200
        assertTrue(time.toString().equals("MON 2200"));
    }

    @Test
    public void equals_true() {
        Time time = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getStart(); // MON 2200
        assertTrue(time.equals(time));
    }

    @Test
    public void decodeDay_true() {
        String mon = "mon";
        String tue = "TUE";
        String wed = "wEd";
        String thu = "THUR";
        String fri = "FRIDAY";
        String sat = "TURDAY";
        String sun = "SuN";

        assertTrue(Time.decodeDay(mon).equals(DayOfWeek.MONDAY));
        assertTrue(Time.decodeDay(tue).equals(DayOfWeek.TUESDAY));
        assertTrue(Time.decodeDay(wed).equals(DayOfWeek.WEDNESDAY));
        assertTrue(Time.decodeDay(thu).equals(DayOfWeek.THURSDAY));
        assertTrue(Time.decodeDay(fri).equals(DayOfWeek.FRIDAY));
        assertTrue(Time.decodeDay(sat).equals(DayOfWeek.SATURDAY));
        assertTrue(Time.decodeDay(sun).equals(DayOfWeek.SUNDAY));
    }

    @Test
    public void getDay_true() {
        Time time = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getStart(); // MON 2200
        assertTrue(time.getDay().equals(DayOfWeek.MONDAY));

    }

    @Test
    public void getTime_true() {
        Time time = TypicalTimeIntervals.TIME_INTERVAL_FOUR_NO_OVERLAP.getStart(); // MON 2200
        assertTrue(time.getTime().equals(LocalTime.of(22, 00)));

    }




}
