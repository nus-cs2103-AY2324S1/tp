package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.model.Time;

/**
 * Creates time for testing purposes
 */
public class TimeBuilder {
    public static final DayOfWeek DEFAULT_DAY = DayOfWeek.MONDAY;
    public static final LocalTime DEFAULT_HOUR = LocalTime.of(14, 0);
    private DayOfWeek day;
    private LocalTime hour;

    /**
     * Default time builder constructor
     */
    public TimeBuilder() {
        this.day = DEFAULT_DAY;
        this.hour = DEFAULT_HOUR;
    }

    /**
     * Time builder constructor with given day and hour
     *
     * @param day  Day of timeBuilder
     * @param hour Hour of timeBuilder
     * @return timeBuilder object
     */
    public TimeBuilder withDayAndHour(DayOfWeek day, LocalTime hour) {
        this.day = day;
        this.hour = hour;
        return this;
    }

    public Time build() {
        return new Time(day, hour);
    }
}

