package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import seedu.address.model.TimeInterval;

public class TypicalTimeIntervals {

    public static TimeInterval timeIntervalOneNoOverlap = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(10, 00)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 00)).build()).build();

    public static TimeInterval timeIntervalTwoNoOverlap = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 00)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(13, 00)).build()).build();

    public static TimeInterval timeIntervalThreeNoOverlap = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(14, 00)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(15, 00)).build()).build();


    public static TimeInterval timeIntervalOneOverlapA = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(10, 00)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 00)).build()).build();

    public static TimeInterval timeIntervalTwoOverlapA = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(10, 30)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 30)).build()).build();

    public static TimeInterval timeIntervalThreeOverlapA = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 00)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 00)).build()).build();

    public static TimeInterval timeIntervalFourOverlapA = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 35)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 30)).build()).build();

    public static TimeInterval timeIntervalFiveOverlapA = new TimeIntervalBuilder()
        .withStartTimeAndEndTime(new TimeBuilder()
        .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 00)).build(),
        new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(13, 00)).build()).build();





}
