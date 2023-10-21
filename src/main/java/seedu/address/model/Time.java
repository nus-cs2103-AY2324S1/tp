package seedu.address.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.exceptions.CommandException;

public class Time {

    DayOfWeek day;
    LocalTime hour;

    public Time(DayOfWeek day, LocalTime hour) {
        this.day = day;
        this.hour = hour;
    }

    public Time(String timeString) {
        if (isValidTime(timeString)) {
            String dayString = timeString.substring(0, timeString.indexOf(" "));
            String time = timeString.substring(timeString.indexOf(" ") + 1);
            this.day = decodeDay(dayString);
            this.hour = LocalTime.parse(time.substring(0, 2) + ":" + time.substring(2, 4)); //throw parse exception
        } else {
            //throw new CommandException("Invalid day or time");
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Message_ONE";

    public static boolean isValidTime(String timeString) {
        return true;
    }

    @Override
    public String toString() {
        return day.toString().substring(0 ,3) + " " + hour.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    private DayOfWeek decodeDay(String day) {
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
