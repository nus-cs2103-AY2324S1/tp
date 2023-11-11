package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.model.Time;
import seedu.address.model.TimeInterval;

/**
 * A utility class to build time interval objects to be used in tests.
 */
public class TimeIntervalBuilder {

    public static final Time DEFAULT_START_TIME = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY,
            LocalTime.of(14, 0)).build(); // 2pm

    public static final Time DEFAULT_END_TIME = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY,
            LocalTime.of(16, 30)).build(); // 430pm
    private Time start;
    private Time end;

    /**
     * Default constructor for time interval builder object
     */
    public TimeIntervalBuilder() {
        start = DEFAULT_START_TIME;
        end = DEFAULT_END_TIME;
    }

    /**
     * Constructor for time interval builder object with given start and end time
     */
    public TimeIntervalBuilder withStartTimeAndEndTime(Time start, Time end) {
        this.start = start;
        this.end = end;
        return this;
    }

    public TimeInterval build() {
        return new TimeInterval(start, end);
    }

}
