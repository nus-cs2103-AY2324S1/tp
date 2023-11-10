package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.model.TimeInterval;

/**
 * A utility class containing a list of {@code TimeIntervals} objects to be used in tests.
 */
public class TypicalTimeIntervals {

    public static final TimeInterval TIME_INTERVAL_ONE_NO_OVERLAP = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(10, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_TWO_NO_OVERLAP = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(13, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_THREE_NO_OVERLAP = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(14, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(15, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_FOUR_NO_OVERLAP = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(22, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(23, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_FIVE_NO_OVERLAP = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(21, 05)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(21, 9))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_SIX_NO_OVERLAP = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(21, 12)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(21, 23))
                        .build()).build();


    public static final TimeInterval TIME_INTERVAL_ONE_OVERLAP_A = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(10, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_TWO_OVERLAP_A = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(10, 30)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 30))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_THREE_OVERLAP_A = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_FOUR_OVERLAP_A = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(11, 35)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 30))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_FIVE_OVERLAP_A = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(13, 00))
                        .build()).build();


    public static final TimeInterval TIME_INTERVAL_ONE_OVERLAP_B = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(5, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(12, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_TWO_OVERLAP_B = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(6, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(8, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_ONE_OVERLAP_C = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.MONDAY, LocalTime.of(5, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(12, 00))
                        .build()).build();

    public static final TimeInterval TIME_INTERVAL_TWO_OVERLAP_C = new TimeIntervalBuilder()
                .withStartTimeAndEndTime(new TimeBuilder()
                .withDayAndHour(DayOfWeek.WEDNESDAY, LocalTime.of(6, 00)).build(),
                    new TimeBuilder().withDayAndHour(DayOfWeek.FRIDAY, LocalTime.of(8, 00))
                        .build()).build();


}
