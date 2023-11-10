package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.model.Time;
import seedu.address.model.TimeInterval;


public class TimeIntervalBuilder {

	public static final Time DEFAULT_START_TIME = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY,
		LocalTime.of(14, 0)).build(); // 2pm

	public static final Time DEFAULT_END_TIME = new TimeBuilder().withDayAndHour(DayOfWeek.MONDAY,
		LocalTime.of(16, 30)).build(); // 430pm
	private Time start;
	private Time end;
	public TimeIntervalBuilder() {
		start = DEFAULT_START_TIME;
		end = DEFAULT_END_TIME;
	}

	public TimeIntervalBuilder withStartTimeAndEndTime(Time start, Time end) {
		this.start = start;
		this.end = end;
		return this;
	}

	public TimeInterval build() {
		return new TimeInterval(start, end);
	}

}
