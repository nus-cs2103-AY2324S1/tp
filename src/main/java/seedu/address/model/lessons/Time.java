package seedu.address.model.lessons;

import static java.lang.String.format;
import static seedu.address.logic.parser.RegularExpressionUtil.ONE_TO_TWO_DIGITS;
import static seedu.address.logic.parser.RegularExpressionUtil.TWO_DIGITS;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.TypeParsingUtil;
import seedu.address.logic.parser.exceptions.InvalidInputException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;


/**
 * Represents a Time in the address book.
 */
public class Time extends ListEntryField {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("h:mm a");
    public static final Time DEFAULT_TIME = new Time();
    private LocalTime time;
    public Time(LocalTime time) {
        this.time = time;
    }
    /**
     * Constructs a {@code Time} from input of format "hh:mm".
     */
    public Time(String str) throws ParseException {
        this.time = parseTime(str);
    }
    private Time() {
    }
    public static Time of(String str) throws ParseException {
        return new Time(str);
    }

    /**
     * Serializes a time into a string.
     */
    public static Time deserialize(String str) {
        return new Time(LocalTime.parse(str, FORMATTER));
    }

    /**
     * Parses the time from the input string, which can be in the following formats: hh:mm
     * @param input the input string where the time is to be parsed from
     * @return the time parsed
     * @throws ParseException if the input is not a valid time
     */
    private static LocalTime parseTime(String input) throws ParseException {
        String hourMinPattern = format("%s:%s", ONE_TO_TWO_DIGITS, TWO_DIGITS);
        Pattern p = Pattern.compile(hourMinPattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            int hour = TypeParsingUtil.parseNum(m.group(1));
            int min = TypeParsingUtil.parseNum(m.group(2));
            if (hour > 23 || min > 59) {
                throw new InvalidInputException(input + " is not a valid time");
            }
            return LocalTime.of(hour, min);
        } else {
            throw new InvalidInputException(input + " is not a valid time");
        }
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Time)) {
            return false;
        }
        Time otherTime = (Time) other;
        if (this == DEFAULT_TIME || other == DEFAULT_TIME) {
            return false;
        }
        return this.time.equals(otherTime.time);
    }
    /**
     * Returns a clone of this time.
     */
    public Time clone() {
        if (this == DEFAULT_TIME) {
            return DEFAULT_TIME;
        }
        return new Time(this.time);
    }

    /**
     * Returns true if this time is before the other time.
     */
    public boolean isBefore(Time other) {
        if (this == DEFAULT_TIME || other == DEFAULT_TIME) {
            return other == DEFAULT_TIME;
        }
        return this.time.isBefore(other.time);
    }
    /**
     * Returns true if this time is after the other time.
     */
    public boolean isAfter(Time other) {
        return other.isBefore(this);
    }

    /**
     * returning a negative integer if this is less than other, 0 if this is equal to other.
     */
    public int compareTo(Time other) {
        if (this == DEFAULT_TIME || other == DEFAULT_TIME) {
            return other == DEFAULT_TIME ? -1 : 1;
        }
        return this.time.compareTo(other.time);
    }
    /**
     * Returns the String representation of the time.
     */
    public String toString() {
        if (this == DEFAULT_TIME) {
            return "To be added";
        }
        return time.format(FORMATTER);
    }
}
