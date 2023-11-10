package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import seedu.address.model.Time;

public class TimeBuilder {

	public static final DayOfWeek DEFAULTDAY = DayOfWeek.MONDAY;
	public static final LocalTime DEFAULTHOUR = LocalTime.of(14, 0); // 2pm
	private DayOfWeek day;
	private LocalTime hour;


	public TimeBuilder() {
		this.day = DEFAULTDAY;
		this.hour = DEFAULTHOUR;
	}

	public TimeBuilder withDayAndHour(DayOfWeek day, LocalTime hour) {
		this.day = day;
		this.hour = hour;
		return this;
	}

	public Time build() {
		return new Time(day, hour);
	}

}
