package seedu.address.logic.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import seedu.address.model.Time;

/**
 * This class encapsulates the methods that are part of the TimeParser API.
 *
 * @author Tan Kerway
 */
public class TimeParser {
    protected static final LocalDateTime DEFAULT_DATE = LocalDateTime.of(1970, 1, 1, 0, 0);
    protected static final String[][] DATE_FORMATS = new String[][] {
            // time with all required information: year, month, day of month, time (formatID == 0)
            {
            "d MMM yyyy HHmm",
            "d MMM yyyy h'.'mma",
            "d MMM yyyy ha",
            "dd-MM-y HHmm",
            "dd-MM-y h'.'mma",
            "dd-MM-y ha",
            "d/M/y HHmm",
            "d/M/y hh'.'mma",
            "d/M/y ha"
            },
            // time with these information: month, day of month, time (formatID == 1)
            {
            "dd MMM HHmm",
            "dd MMM hh'.'mma",
            "dd MMM ha",
            "d/M HHmm",
            "d/M hh'.'mma",
            "d/M ha"
            },
            // correct format but string is missing the time (formatID == 3)
            {
            "E",
            "y-MM-dd",
            "d/M/yy",
            "d/M/yyyy",
            "d/M/y",
            "MMM dd",
            "dd MMM",
            "d/M"
            },
    };

    protected static final String[][] DATE_ONLY_FORMATS = new String[][] {
            // time with all required information: year, month, day of month (formatID == 0)
            {
            "d MMM yyyy",
            "dd-MM-y",
            "d/M/y"
            },
            // time with these information: month, day of month, time (formatID == 1)
            {
            "dd MMM",
            "d/M"
            },
    };

    protected static final String MISSING_TIME_ERROR_MESSAGE = "Please enter an interview time!";

    /**
     * Converts the string date into a LocalDatetime object. Only accepts valid time Strings
     * that have time as their suffix.
     *
     * @author Tan Kerway
     * @param time the String that contains the data for the date
     * @return datetime object that represents the string
     * @throws seedu.address.logic.parser.exceptions.ParseException when the time String is not valid
     */
    public static Time parseDate(String time, boolean dateOnly)
            throws seedu.address.logic.parser.exceptions.ParseException {
        String[][] formatList = dateOnly ? DATE_ONLY_FORMATS : DATE_FORMATS;
        for (int currentFormatID = 0; currentFormatID < formatList.length; currentFormatID++) {
            // find a date format string that matches the user pattern
            for (String formatString : formatList[currentFormatID]) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
                    simpleDateFormat.setLenient(false);
                    LocalDateTime temp = simpleDateFormat
                            .parse(time)
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    Time res = new Time(addMissingDateFields(temp, currentFormatID, dateOnly));
                    if (res.getDate().getYear() > 9999) {
                        throw new seedu.address.logic.parser.exceptions.ParseException("Please specify a valid date!");
                    }
                    return res;
                } catch (ParseException ignored) {
                    String s = "";
                }
            }
        }
        throw new seedu.address.logic.parser.exceptions.ParseException("Please specify a valid date!");
    }

    /**
     * Appends missing date fields depending on what the user typed in.
     * @author Tan Kerway
     * @param temp the LocalDateTime object to modify
     * @param currentFormatID the given type of date object
     * @return a LocalDateTime object that has all the required information
     */
    private static LocalDateTime addMissingDateFields(LocalDateTime temp, int currentFormatID, boolean dateOnly)
            throws seedu.address.logic.parser.exceptions.ParseException {
        switch (currentFormatID) {
        case 0: // case where user entered a year, month, day of month, and time
            break;
        case 1: // case where the user entered a month, day of month, and time
            temp = addYear(temp);
            break;
        default: // case where the user did not enter an interview time
            // inform the user that their input is missing an interview time
            if (!dateOnly) {
                throw new seedu.address.logic.parser.exceptions.ParseException(MISSING_TIME_ERROR_MESSAGE);
            }
            // exit normally if the user does not need a time
            break;
        }
        return temp;
    }

    /**
     * Adds the year to the date object.
     *
     * @author Tan Kerway
     * @param time the LocalDateTime object that represents start dates, or deadlines
     * @return the modified LocalDateTime object
     */
    private static LocalDateTime addYear(LocalDateTime time) {
        assert time != null : "time should be not null";
        LocalDateTime now = LocalDateTime.now();
        int yearsElapsed = now.getYear() - DEFAULT_DATE.getYear();
        // update the year by adding to the current year
        // why: this method is called when the input date does not have a year
        LocalDateTime res = time.plusYears(yearsElapsed);
        while (now.isAfter(res)) {
            res = res.plusYears(1);
        }
        return res;
    }

    /**
     * Adds the day to the date object.
     *
     * @author Tan Kerway
     * @param time the LocalDateTime object that represents start dates, or deadlines
     * @return the modified LocalDateTime object
     */
    private static LocalDateTime addDay(LocalDateTime time)
            throws seedu.address.logic.parser.exceptions.ParseException {
        assert time != null : "time should be not null";
        LocalDateTime now = LocalDateTime.now();
        int defaultDay = DEFAULT_DATE.getDayOfWeek().getValue();
        int timeDay = time.getDayOfWeek().getValue();
        int daysElapsed = timeDay >= defaultDay ? timeDay - defaultDay : 7 - defaultDay + timeDay;
        // update the current date by the number of days
        LocalDateTime res = now.plusDays(daysElapsed);
        // guard clause: the user did not specify an interview time
        if (time.getHour() == 0 && time.getMinute() == 0) {
            throw new seedu.address.logic.parser.exceptions.ParseException(MISSING_TIME_ERROR_MESSAGE);
        }
        return res.withHour(time.getHour()).withMinute(time.getMinute());
    }

    /**
     * Formats the time String.
     *
     * @author Tan Kerway
     * @param time the given LocalDateTime object to format
     * @return the string form of the LocalDateTime object
     */
    public static String formatDate(LocalDateTime time) {
        assert time != null : "time should be not null";
        return time.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
