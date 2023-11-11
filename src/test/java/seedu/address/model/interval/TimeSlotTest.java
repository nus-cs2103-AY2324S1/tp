package seedu.address.model.interval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TimeSlotTest {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @Test
    public void equals() throws ParseException {
        //checks for same values and different values
        TimeSlot timeSlot1 = new TimeSlot(dateFormat.parse("15:00"), dateFormat.parse("16:00"));
        TimeSlot timeSlot2 = new TimeSlot(dateFormat.parse("15:00"), dateFormat.parse("16:00"));
        TimeSlot timeSlot3 = new TimeSlot(dateFormat.parse("15:00"), dateFormat.parse("17:00"));

        //check equal
        assertEquals(timeSlot1, timeSlot2);

        //check not equal
        assertNotEquals(timeSlot1, timeSlot3);
    }

    @Test
    public void equals2() throws ParseException {
        //checks for same instance.
        TimeSlot timeSlot = new TimeSlot(dateFormat.parse("15:00"), dateFormat.parse("16:00"));
        assertEquals(timeSlot, timeSlot);
    }

    @Test
    public void equals3() throws ParseException {
        //checks for nulls
        TimeSlot timeSlot = new TimeSlot(dateFormat.parse("15:00"), dateFormat.parse("16:00"));
        assertNotEquals(timeSlot, null);
    }


    @Test
    public void parseIntervals() throws ParseException {
        List<String> stringList = new ArrayList<>();
        stringList.add("10:00 - 12:00");
        stringList.add("14:00 - 16:00");
        stringList.add("18:00 - 20:00");

        List<TimeSlot> result = TimeSlot.parseIntervals(stringList);
        List<TimeSlot> expected = new ArrayList<>();
        expected.add(new TimeSlot(dateFormat.parse("10:00"), dateFormat.parse("12:00")));
        expected.add(new TimeSlot(dateFormat.parse("14:00"), dateFormat.parse("16:00")));
        expected.add(new TimeSlot(dateFormat.parse("18:00"), dateFormat.parse("20:00")));

        List<TimeSlot> expectedWrong = new ArrayList<>();
        expectedWrong.add(new TimeSlot(dateFormat.parse("10:00"), dateFormat.parse("14:00")));

        assertEquals(expected, result);
        assertNotEquals(expectedWrong, result);
    }

    @Test
    public void findAvailableTime() throws ParseException {
        // check when interval end is bigger than last end in timeslot

        Interval interval = new Interval(new IntervalDay("Mon"), new Duration("30"), new IntervalBegin("0800"),
                new IntervalEnd("2200"));

        List<TimeSlot> timeslots = new ArrayList<>();
        timeslots.add(new TimeSlot(dateFormat.parse("12:00"), dateFormat.parse("14:00")));
        timeslots.add(new TimeSlot(dateFormat.parse("15:00"), dateFormat.parse("18:00")));

        List<TimeSlot> result = TimeSlot.findAvailableTime(timeslots, interval);
        List<TimeSlot> expected = new ArrayList<>();
        expected.add(new TimeSlot(dateFormat.parse("08:00"), dateFormat.parse("12:00")));
        expected.add(new TimeSlot(dateFormat.parse("14:00"), dateFormat.parse("15:00")));
        expected.add(new TimeSlot(dateFormat.parse("18:00"), dateFormat.parse("22:00")));

        assertEquals(expected, result);

        Interval interval2 = new Interval(new IntervalDay("Mon"), new Duration("120"), new IntervalBegin("0800"),
                new IntervalEnd("1900"));
        List<TimeSlot> result2 = TimeSlot.findAvailableTime(timeslots, interval2);
        List<TimeSlot> expected2 = new ArrayList<>();
        expected2.add(new TimeSlot(dateFormat.parse("08:00"), dateFormat.parse("12:00")));
        assertEquals(result2, expected2);
    }

    @Test
    public void findAvailableTime2() throws ParseException {
        //check when interval end is smaller than last end in timeslots.
        List<TimeSlot> timeslots3 = new ArrayList<>();
        timeslots3.add(new TimeSlot(dateFormat.parse("20:00"), dateFormat.parse("21:00")));

        Interval interval3 = new Interval(new IntervalDay("Mon"), new Duration("120"), new IntervalBegin("0800"),
                new IntervalEnd("1000"));

        List<TimeSlot> expected3 = new ArrayList<>();
        expected3.add(new TimeSlot(dateFormat.parse("08:00"), dateFormat.parse("10:00")));
        List<TimeSlot> result3 = TimeSlot.findAvailableTime(timeslots3, interval3);
        assertEquals(result3, expected3);
    }

    @Test
    public void findAvailableTime3() throws ParseException {
        //checks when duration is smaller than given the begin and end of intervals
        List<TimeSlot> timeslots4 = new ArrayList<>();
        timeslots4.add(new TimeSlot(dateFormat.parse("20:00"), dateFormat.parse("21:00")));

        Interval interval4 = new Interval(new IntervalDay("Mon"), new Duration("180"), new IntervalBegin("0800"),
                new IntervalEnd("1000"));
        List<TimeSlot> expected4 = new ArrayList<>();
        List<TimeSlot> result4 = TimeSlot.findAvailableTime(timeslots4, interval4);
        assertEquals(result4, expected4);
    }

    @Test
    public void printAvailableTime() throws ParseException {
        //when zero results
        List<TimeSlot> timeslots5 = new ArrayList<>();
        timeslots5.add(new TimeSlot(dateFormat.parse("20:00"), dateFormat.parse("21:00")));

        Interval interval5 = new Interval(new IntervalDay("Mon"), new Duration("180"), new IntervalBegin("0800"),
                new IntervalEnd("1000"));
        List<TimeSlot> result5 = TimeSlot.findAvailableTime(timeslots5, interval5);
        String printedResult = TimeSlot.printResults(result5);
        String expected = "There are no available timeslots.";
        assertEquals(printedResult, expected);
    }

    @Test
    public void printAvailableTime2() throws ParseException {
        //when have results
        List<TimeSlot> timeslots6 = new ArrayList<>();
        timeslots6.add(new TimeSlot(dateFormat.parse("14:00"), dateFormat.parse("16:00")));

        Interval interval6 = new Interval(new IntervalDay("Mon"), new Duration("120"), new IntervalBegin("0800"),
                new IntervalEnd("2200"));
        List<TimeSlot> result6 = TimeSlot.findAvailableTime(timeslots6, interval6);
        String printedResult = TimeSlot.printResults(result6);
        String expectedResult = "Free from 08:00 - 14:00\n" + "Free from 16:00 - 22:00\n";
        assertEquals(printedResult, expectedResult);
    }

}
