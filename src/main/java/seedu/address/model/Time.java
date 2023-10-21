package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {

    public static final String MESSAGE_CONSTRAINTS = "The format of a time should be: mon 1200, the word is 1st 3 " +
            "letters of a day and the time is in 24 hour format";

    private static final String DAY_REGEX = "^(?i)(mon|tue|wed|thu|fri|sat|sun)";
    private static final String TIME_REGEX = "(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]";
    public static final String VALIDATION_REGEX = DAY_REGEX + " " + TIME_REGEX;

    DayOfWeek day;
    LocalTime hour;

    public Time(DayOfWeek day, LocalTime hour) {
        this.day = day;
        this.hour = hour;
    }

    public static boolean isValidTime(String timeString) {
        return timeString.matches(VALIDATION_REGEX);
    }

    public int compareTo(Time otherTime) {
        requireNonNull(otherTime);
        return this.day.compareTo(otherTime.day) == 0 ? this.hour.compareTo(otherTime.hour)
                : this.day.compareTo(otherTime.day);
    }

    @Override
    public String toString() {
        return day.toString().substring(0 ,3) + " " + hour.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    public static DayOfWeek decodeDay(String day) {
        if (DayOfWeek.MONDAY.toString().toLowerCase().contains(day)) {
            return DayOfWeek.MONDAY;
        } else if (DayOfWeek.TUESDAY.toString().toLowerCase().contains(day)) {
            return DayOfWeek.TUESDAY;
        } else if (DayOfWeek.WEDNESDAY.toString().toLowerCase().contains(day)) {
            return DayOfWeek.WEDNESDAY;
        } else if (DayOfWeek.THURSDAY.toString().toLowerCase().contains(day)) {
            return DayOfWeek.THURSDAY;
        } else if (DayOfWeek.FRIDAY.toString().toLowerCase().contains(day)) {
            return DayOfWeek.FRIDAY;
        } else if (DayOfWeek.SATURDAY.toString().toLowerCase().contains(day)) {
            return DayOfWeek.SATURDAY;
        } else
            return DayOfWeek.SUNDAY;
    }
}
