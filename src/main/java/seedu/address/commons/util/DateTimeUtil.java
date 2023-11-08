package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utilities about date and time
 */
public class DateTimeUtil {
    /**
     * Parse string into LocalDateTime
     * @param str the string to be parsed
     * @return The parsed LocalDateTime
     * @throws DateTimeParseException If the string cannot be parsed into LocalDateTime
     */
    public static LocalDateTime parseString(String str) throws DateTimeParseException {
        requireNonNull(str);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm[:ss]");
        LocalDateTime result = null;
        try {
            result = LocalDateTime.parse(str, formatter1);
        } catch (DateTimeParseException e2) {
            LocalDateTime now = LocalDateTime.now();
            if (str.contains("-")) {
                String appendTime = " 00:00:00";
                result = LocalDateTime.parse(str + appendTime, formatter1);
            } else {
                int month = now.getMonthValue();
                int day = now.getDayOfMonth();
                String appendDate = now.getYear() + "-" + (month <= 9 ? "0" + month : month)
                        + "-" + (day <= 9 ? "0" + day : day) + " ";
                result = LocalDateTime.parse(appendDate + str, formatter1);
            }
        }
        return result;
    }

    /**
     * Get the formatted String representation of the {@code LocalDateTime} object
     *
     * @param time The {@code LocalDateTime} object to be parsed
     * @return The String representation of this {@code LocalDateTime} object, in the format {@code yyyy-MM-dd HH:mm:ss}
     */
    public static String toFormattedString(LocalDateTime time) {
        requireNonNull(time);
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Return {@code true} if {@code timeToCheck} is between {@code intervalStart} and {@code intervalEnd} (inclusive).
     * In another word, returns {@code true} when {@code intervalStart} <= {@code timeToCheck} <= {@code intervalEnd}
     *
     * @param intervalStart The start time for the interval
     * @param intervalEnd The end time for the interval
     * @param timeToCheck The time that is needed for checking
     * @return Whether {@code timeToCheck} is between {@code intervalStart} and {@code intervalEnd} or not
     */
    public static boolean withinTimeInterval(LocalDateTime intervalStart,
                                      LocalDateTime intervalEnd, LocalDateTime timeToCheck) {
        requireAllNonNull(intervalStart, intervalEnd, timeToCheck);
        assert intervalStart.equals(intervalEnd) || intervalStart.isBefore(intervalEnd);
        return timeToCheck.equals(intervalStart)
                || timeToCheck.equals(intervalEnd)
                || (timeToCheck.isAfter(intervalStart) && timeToCheck.isBefore(intervalEnd));
    }

    /**
     * Return {@code true} if the time interval {@code intervalAStart}~{@code intervalAEnd}
     * overlaps with the time interval {@code intervalBStart}~{@code intervalBEnd} (inclusive of start and end time).
     * Start time is inclusive, end time is exclusive
     *
     * @param intervalAStart The start time for the first interval
     * @param intervalAEnd The end time for the first interval
     * @param intervalBStart The start time for the second interval
     * @param intervalBEnd The end time for the second interval
     * @return Whether the two time intervals overlap or not
     */
    public static boolean timeIntervalsOverlap(LocalDateTime intervalAStart, LocalDateTime intervalAEnd,
                                        LocalDateTime intervalBStart, LocalDateTime intervalBEnd) {
        requireAllNonNull(intervalAStart, intervalAEnd, intervalBStart, intervalBEnd);
        return withinTimeInterval(intervalAStart, intervalAEnd, intervalBStart)
                || withinTimeInterval(intervalAStart, intervalAEnd, intervalBEnd)
                || withinTimeInterval(intervalBStart, intervalBEnd, intervalAStart)
                || withinTimeInterval(intervalBStart, intervalBEnd, intervalAEnd);
    }

}
