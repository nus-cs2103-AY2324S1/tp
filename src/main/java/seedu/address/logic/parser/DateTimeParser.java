package seedu.address.logic.parser;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Duration;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;

/**
 * Implements class that handles parsing of time input.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class DateTimeParser {
    public static final String INVALID_DATETIME_FORMAT = "Invalid date-time format!";
    public static final String INVALID_DATETIME_DURATION = "Invalid date-time duration! "
            + "End time cannot be before start time";
    private static final LocalTime MIDNIGHT = LocalTime.of(0, 0);
    private static final String INVALID_COMBINATION = "Invalid combination of start and end time";

    private static LocalDate getToday() {
        return LocalDate.now();
    }

    private static LocalTime getTimeNow() {
        LocalTime timeNow = LocalTime.now();
        return LocalTime.of(timeNow.getHour(), timeNow.getMinute());
    }

    private static LocalDateTime getNow() {
        LocalTime timeNow = getTimeNow();
        LocalDate dateNow = getToday();
        return LocalDateTime.of(dateNow, timeNow);
    }

    /**
     * Parses number format single-word string into time.
     */
    private static LocalTime parseOneElementNumberTimeFormat(String timeInput) {
        if (timeInput.contains(" ")) {
            return null; //one element format should not contain space
        }

        String[] formatsWithoutEnglish = {"HHmm", "HH:mm", "HH.mm", "ha", "h:mma", "h.mma"};
        String[] formatsWithEnglish = {"ha", "h:mma", "h.mma"};

        for (String format : formatsWithoutEnglish) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
                LocalTime time = LocalTime.parse(timeInput, dtf);
                return time;
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        for (String format : formatsWithEnglish) {
            try {
                DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(format)
                        .toFormatter(Locale.ENGLISH);
                LocalTime time = LocalTime.parse(timeInput, dtf);
                return time;
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        return null;
    }

    /**
     * Parses english format single-word string into time.
     */
    private static LocalTime parseOneElementEnglishTimeFormat(String timeInput) {
        if (timeInput.contains(" ")) {
            return null; // one element format should not contain space
        }

        String lowerCasedTimeInput = timeInput.toLowerCase();

        switch (lowerCasedTimeInput) {
        case "noon":
            return LocalTime.of(12, 0);
        case "midnight":
            return LocalTime.of(0, 0);
        default:
            return null;
        }
    }

    private static LocalTime parseOneElementTime(String timeInput) {
        LocalTime time = parseOneElementNumberTimeFormat(timeInput);
        if (time == null) {
            time = parseOneElementEnglishTimeFormat(timeInput);
        }
        return time;
    }

    /**
     * Parses number format double-word string into time.
     * @param timeInput
     * @return
     */
    private static LocalTime parseTwoElementsNumberTimeFormat(String timeInput) {
        if (timeInput.split(" ").length != 2) {
            return null;
        }

        String[] formats = {"h a", "h:mm a", "h.mm a"};
        for (String format : formats) {
            try {
                DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(format)
                        .toFormatter(Locale.ENGLISH);
                LocalTime time = LocalTime.parse(timeInput, dtf);
                return time;
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        return null;
    }

    private static LocalTime parseTwoElementsTime(String timeInput) {
        return parseTwoElementsNumberTimeFormat(timeInput);
    }

    private static LocalDateTime parseThreeElementsEnglishDateTimeFormat(String timeInput) {
        String lowerCasedDateInput = timeInput.toLowerCase();
        String[] tokenisedString = lowerCasedDateInput.split(" ");
        if (tokenisedString.length != 3) {
            return null;
        } else if (!tokenisedString[0].equals("in")) {
            return null;
        } else if (!StringUtil.isNonZeroUnsignedInteger(tokenisedString[1])) {
            return null;
        }

        int toAdd = Integer.parseInt(tokenisedString[1]);
        switch (tokenisedString[2]) {
        case "min":
        case "mins":
        case "minute":
        case "minutes":
            return getNow().plusMinutes(toAdd);
        case "hr":
        case "hrs":
        case "hour":
        case "hours":
            return getNow().plusHours(toAdd);
        default:
            return null;
        }
    }

    private static LocalDate parseOneElementNumberDateFormat(String dateInput) {
        if (dateInput.contains(" ")) {
            return null; // should contain one element only
        }

        String[] formatsWithYear = {"d-M-yyyy", "d-M-yy", "d/M/yyyy", "d/M/yy", "yyyy-M-d", "yy-M-d"};
        String[] formatsWithoutYear = {"d/M", "d-M"};

        if (dateInput.length() > 3) {
            for (String format : formatsWithYear) {
                try {
                    DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .appendPattern(format)
                            .toFormatter(Locale.ENGLISH);
                    LocalDate date = LocalDate.parse(dateInput, dtf);
                    return date;
                } catch (DateTimeException e) {
                    // try other formats
                }
            }
        } else {
            for (String format : formatsWithoutYear) {
                try {
                    DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .appendPattern(format)
                            .parseDefaulting(ChronoField.YEAR, getToday().getYear())
                            .toFormatter(Locale.ENGLISH);
                    LocalDate date = LocalDate.parse(dateInput, dtf);
                    return date;
                } catch (DateTimeException e) {
                    // try other formats
                }
            }
        }
        return null;
    }

    private static LocalDate parseOneElementEnglishDateFormat(String dateInput) {
        if (dateInput.contains(" ")) {
            return null; //should contain one element only
        }

        String lowerCasedDateInput = dateInput.toLowerCase();

        switch (lowerCasedDateInput) {
        case "tmr":
        case "tomorrow":
            return getToday().plusDays(1);
        case "today":
        case "tdy":
            return getToday();
        case "yesterday":
        case "ytd":
            return getToday().minusDays(1);
        default:
            return null;
        }
    }

    private static LocalDate parseOneElementDate(String dateInput) {
        LocalDate date = parseOneElementNumberDateFormat(dateInput);
        if (date == null) {
            date = parseOneElementEnglishDateFormat(dateInput);
        }
        return date;
    }

    private static LocalDate parseTwoElementsNumberDateFormat(String dateInput) {
        if (dateInput.split(" ").length != 2) {
            return null;
        }
        String[] formatsWithoutYear = {"MMM d", "d MMM"};

        for (String format : formatsWithoutYear) {
            try {
                DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(format)
                        .parseDefaulting(ChronoField.YEAR, getToday().getYear())
                        .toFormatter(Locale.ENGLISH);
                LocalDate date = LocalDate.parse(dateInput, dtf);
                return date;
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        return null;
    }

    private static LocalDate parseTwoElementsEnglishDateFormat(String dateInput) {
        String lowerCasedDateInput = dateInput.toLowerCase();
        String[] tokenisedString = lowerCasedDateInput.split(" ");
        if (tokenisedString.length != 2) {
            return null;
        } else if (!tokenisedString[0].equals("next")) {
            return null;
        }

        switch (tokenisedString[1]) {
        case "day":
            return getToday().plusDays(1);
        case "week":
            return getToday().plusWeeks(1);
        case "month":
            return getToday().plusMonths(1);
        case "year":
            return getToday().plusYears(1);
        default:
        }
        String[] daysOfTheWeekFormats = {"EEEE", "E"};
        for (String format : daysOfTheWeekFormats) {
            try {
                DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(format)
                        .toFormatter(Locale.ENGLISH);
                TemporalAccessor accessor = dtf.parse(tokenisedString[1]);
                DayOfWeek next = DayOfWeek.from(accessor);
                DayOfWeek today = getToday().getDayOfWeek();
                int diff = today.compareTo(next);
                return next.getValue() <= today.getValue() ? getToday().plusDays(7 - diff)
                        : getToday().plusDays(-diff);
            } catch (DateTimeException e) {
                // try next format
            }
        }
        return null;
    }

    private static LocalDate parseTwoElementsDate(String dateInput) {
        LocalDate date = parseTwoElementsNumberDateFormat(dateInput);
        if (date == null) {
            date = parseTwoElementsEnglishDateFormat(dateInput);
        }
        return date;
    }

    private static LocalDate parseThreeElementsNumberDateFormat(String dateInput) {
        if (dateInput.split(" ").length != 3) {
            return null;
        }
        String[] formats = {"MMM d yyyy", "d MMM yyyy", "MMM d yy", "d MMM yy"};

        for (String format : formats) {
            try {
                DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(format)
                        .toFormatter(Locale.ENGLISH);
                LocalDate date = LocalDate.parse(dateInput, dtf);
                return date;
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        return null;
    }

    private static LocalDate parseThreeElementsEnglishDateFormat(String dateInput) {
        String lowerCasedDateInput = dateInput.toLowerCase();
        String[] tokenisedString = lowerCasedDateInput.split(" ");
        if (tokenisedString.length != 3) {
            return null;
        } else if (!tokenisedString[0].equals("in")) {
            return null;
        } else if (!StringUtil.isNonZeroUnsignedInteger(tokenisedString[1])) {
            return null;
        }

        int toAdd = Integer.parseInt(tokenisedString[1]);
        switch (tokenisedString[2]) {
        case "day":
        case "days":
            return getToday().plusDays(toAdd);
        case "week":
        case "weeks":
            return getToday().plusWeeks(toAdd);
        case "month":
        case "months":
            return getToday().plusMonths(toAdd);
        case "year":
        case "years":
            return getToday().plusYears(toAdd);
        default:
            return null;
        }
    }

    private static LocalDate parseThreeElementsDate(String dateInput) {
        LocalDate date = parseThreeElementsNumberDateFormat(dateInput);
        if (date == null) {
            date = parseThreeElementsEnglishDateFormat(dateInput);
        }
        return date;
    }

    private static LocalDateTime parseThreeElementsDateTime(String dateTimeInput) {
        return parseThreeElementsEnglishDateTimeFormat(dateTimeInput);
    }

    private static LocalDateTime parseNowDateTimeFormats(String dateTimeInput) {
        String lowerCasedDateTimeInput = dateTimeInput.toLowerCase();
        String[] tokenisedString = lowerCasedDateTimeInput.split(" ");

        if (!dateTimeInput.contains("now")) {
            return null;
        } else if (tokenisedString.length != 1 && tokenisedString.length != 4) {
            return null;
        } else if (!StringUtil.isNonZeroUnsignedInteger(tokenisedString[0]) && tokenisedString.length == 4) {
            return null;
        }

        String[] format = {"from", "now"};
        if (tokenisedString.length == 1) {
            return getNow();
        } else if (format[0].equals(tokenisedString[2]) && format[1].equals(tokenisedString[3])) {
            int toAdd = Integer.parseInt(tokenisedString[0]);
            switch (tokenisedString[1]) {
            case "min":
            case "mins":
            case "minute":
            case "minutes":
                return getNow().plusMinutes(toAdd);
            case "hr":
            case "hrs":
            case "hour":
            case "hours":
                return getNow().plusHours(toAdd);
            case "day":
            case "days":
                return getNow().plusDays(toAdd);
            case "week":
            case "weeks":
                return getNow().plusWeeks(toAdd);
            case "month":
            case "months":
                return getNow().plusMonths(toAdd);
            case "year":
            case "years":
                return getNow().plusYears(toAdd);
            default:
                return null;
            }
        }
        return null;
    }

    /**
     * Returns a Date-Time in {@code LocalDateTime} type for instances by taking in a String input.
     * @param input Use string input.
     * @throws ParseException thrown if input is of invalid format.
     */
    public static LocalDateTime parseDateTimeInstance(String input) throws ParseException {
        LocalDate date;
        LocalTime time;
        if (input.contains("now")) {
            LocalDateTime dt = parseNowDateTimeFormats(input);
            if (dt == null) {
                throw new ParseException(INVALID_DATETIME_FORMAT);
            }
            return dt;
        }

        String[] tokenisedString = input.split(" ");
        int numberOfEle = tokenisedString.length;
        if (tokenisedString.length > 5) {
            throw new ParseException(INVALID_DATETIME_FORMAT);
        }

        switch (numberOfEle) {
        case 1:
            return parseOneElement(tokenisedString);
        case 2:
            return parseTwoElements(tokenisedString);
        case 3:
            return parseThreeElements(tokenisedString);
        case 4:
            return parseFourElements(tokenisedString);
        case 5:
            return parseFiveElements(tokenisedString);
        default:
            throw new ParseException(INVALID_DATETIME_FORMAT);
        }
    }

    private static LocalDateTime parseOneElement(String[] tokenisedString) throws ParseException {
        LocalDate date = parseOneElementDate(tokenisedString[0]);
        LocalTime time = parseOneElementTime(tokenisedString[0]);
        if (date != null) {
            return LocalDateTime.of(date, MIDNIGHT);
        } else if (time != null) {
            date = getNextDateOfTime(time);
            return LocalDateTime.of(date, time);
        }
        throw new ParseException(INVALID_DATETIME_FORMAT);
    }

    private static LocalDateTime parseTwoElements(String[] tokenisedString) throws ParseException {
        LocalDate date;
        LocalTime time = parseOneElementTime(tokenisedString[1]);
        if (time != null) {
            date = parseOneElementDate(tokenisedString[0]);
            if (date != null) {
                return LocalDateTime.of(date, time);
            }
            throw new ParseException(INVALID_DATETIME_FORMAT);
        }

        date = parseTwoElementsDate(StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 1));
        time = parseTwoElementsTime(StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 1));
        if (date != null) {
            return LocalDateTime.of(date, MIDNIGHT);
        } else if (time != null) {
            date = getNextDateOfTime(time);
            return LocalDateTime.of(date, time);
        }
        throw new ParseException(INVALID_DATETIME_FORMAT);
    }

    private static LocalDateTime parseThreeElements(String[] tokenisedString) throws ParseException {
        LocalDate date;
        LocalTime time = parseOneElementTime(tokenisedString[2]);
        if (time != null) {
            date = parseTwoElementsDate(StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 1));
            return date == null ? null : LocalDateTime.of(date, time);
        }

        time = parseTwoElementsTime(StringUtil.concatStringArrayWithSpace(tokenisedString, 1, 2));
        if (time != null) {
            date = parseOneElementDate(tokenisedString[0]);
            if (date != null) {
                return LocalDateTime.of(date, time);
            }
            throw new ParseException(INVALID_DATETIME_FORMAT);
        }

        date = parseThreeElementsDate(StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 2));
        LocalDateTime dateTime = parseThreeElementsDateTime(
                StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 2));
        if (date != null) {
            return LocalDateTime.of(date, MIDNIGHT);
        } else if (dateTime != null) {
            return dateTime;
        }
        throw new ParseException(INVALID_DATETIME_FORMAT);
    }

    private static LocalDateTime parseFourElements(String[] tokenisedString) throws ParseException {
        LocalDate date;
        LocalTime time = parseOneElementTime(tokenisedString[3]);
        if (time != null) {
            date = parseThreeElementsDate(StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 2));
            if (date != null) {
                return LocalDateTime.of(date, time);
            }
            throw new ParseException(INVALID_DATETIME_FORMAT);
        }
        time = parseTwoElementsTime(StringUtil.concatStringArrayWithSpace(tokenisedString, 2, 3));
        date = parseTwoElementsDate(StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 1));
        if (date == null || time == null) {
            throw new ParseException(INVALID_DATETIME_FORMAT);
        } else {
            return LocalDateTime.of(date, time);
        }
    }

    private static LocalDateTime parseFiveElements(String[] tokenisedString) throws ParseException {
        LocalTime time = parseTwoElementsTime(StringUtil.concatStringArrayWithSpace(tokenisedString, 3, 4));
        LocalDate date = parseThreeElementsDate(StringUtil.concatStringArrayWithSpace(tokenisedString, 0, 2));
        if (date == null || time == null) {
            throw new ParseException(INVALID_DATETIME_FORMAT);
        } else {
            return LocalDateTime.of(date, time);
        }
    }

    /**
     * Returns a Date-time array in {@code LocalDateTime} type for a duration taking into account start and end inputs.
     * @param startInput start of the event by user.
     * @param endInput end of the event by user.
     * @throws ParseException for invalid date-time formats or invalid durations.
     */
    public static Duration parseDateTimeDuration(String startInput, String endInput)
            throws ParseException {
        startInput = startInput.trim();
        endInput = endInput.trim();
        if (!isValidDateTimeFormat(startInput) || !isValidDateTimeFormat(endInput)) {
            throw new ParseException(INVALID_DATETIME_FORMAT);
        } else if (isValidDateFormat(startInput) && isValidTimeFormat(endInput)) {
            throw new ParseException(INVALID_COMBINATION);
        } else if (isValidTimeFormat(startInput) && isValidDateFormat(endInput)) {
            throw new ParseException(INVALID_COMBINATION);
        }

        TimeStart timeStart;
        TimeEnd timeEnd;

        if (isValidDateFormat(startInput) && isValidDateFormat(endInput)) {
            timeStart = new TimeStart(parseDateTimeInstance(startInput));
            timeEnd = new TimeEnd(parseDateTimeInstance(endInput).with(MIDNIGHT.minusMinutes(1)));
        } else if (isValidTimeFormat(startInput) && isValidTimeFormat(endInput)) {
            timeEnd = new TimeEnd(parseDateTimeInstance(endInput));
            timeStart = new TimeStart(parseDateTimeInstance(startInput).with(timeEnd.getTime().toLocalDate()));
        } else if (isValidTimeFormat(endInput)) {
            timeStart = new TimeStart(parseDateTimeInstance(startInput));
            timeEnd = new TimeEnd(parseDateTimeInstance(endInput).with(timeStart.getTime().toLocalDate()));
        } else if (isValidDateFormat(endInput)) {
            timeStart = new TimeStart(parseDateTimeInstance(startInput));
            timeEnd = new TimeEnd(parseDateTimeInstance(endInput).with(MIDNIGHT.minusMinutes(1)));
        } else {
            timeStart = new TimeStart(parseDateTimeInstance(startInput));
            timeEnd = new TimeEnd(parseDateTimeInstance(endInput));
        }

        if (timeEnd.isAfter(timeStart)) {
            return new Duration(timeStart, timeEnd);
        } else {
            throw new ParseException(INVALID_DATETIME_DURATION);
        }
    }

    private static boolean isValidTimeFormat(String timeInput) {
        String[] tokenisedString = timeInput.split(" ");
        int len = tokenisedString.length;

        switch (len) {
        case 1:
            return parseOneElementTime(timeInput) != null;
        case 2:
            return parseTwoElementsTime(timeInput) != null;
        default:
            return false;
        }
    }

    private static boolean isValidDateFormat(String dateInput) {
        String[] tokenisedString = dateInput.split(" ");
        int len = tokenisedString.length;

        switch (len) {
        case 1:
            return parseOneElementDate(dateInput) != null;
        case 2:
            return parseTwoElementsDate(dateInput) != null;
        case 3:
            return parseThreeElementsDate(dateInput) != null;
        default:
            return false;
        }
    }

    /**
     * Checks if {@code dateTime} string is a valid date time format.
     */
    public static boolean isValidDateTimeFormat(String dateTime) {
        try {
            parseDateTimeInstance(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Gets the next occurrence of the date for a specified time.
     *
     * @param time specified time to check.
     * @return Date of next occurrence of time.
     */
    private static LocalDate getNextDateOfTime(LocalTime time) {
        if (time.isBefore(getTimeNow())) {
            return getToday().plusDays(1);
        }
        return getToday();
    }
}
