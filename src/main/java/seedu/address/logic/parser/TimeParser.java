package seedu.address.logic.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;

/**
 * This class encapsulates the methods that are part of the TimeParser API.
 *
 * @author Tan Kerway
 */
public class TimeParser {
    protected static final LocalDateTime DEFAULT_DATE = LocalDateTime.of(1970, 1, 1, 0, 0);
    protected static final String[][] DATE_FORMATS = new String[][] {
            // TODO: ADD THIS PATTERN: 2024-12-21T19:00
            // time string with day and time (formatID == 0)
            {
            "E HHmm",
            "E h'.'mma",
            "E ha"
            },
            // time with all required information: year, month, day of month, time (formatID == 1)
            {
            "d MMM yyyy h'.'mma",
            "d MMM yyyy HHmm",
            "dd-MM-y HHmm",
            "dd-MM-yyyy HHmm",
            "dd-MM-yyyy HH'.'mma",
            "d M y HH'.'mma",
            "d M y HH:mm",
            "MMM d HH:mm yyyy",
            "d/M/yy HHmm",
            "d/M/yyyy HHmm",
            "d/M/y hh'.'mma",
            "d/M/yyyy hh'.'mma",
            "d/M/y ha"
            },
            // time with these information: month, day of month, time (formatID == 2)
            {
            "MMM dd HH'.'mma",
            "dd MMM HH'.'mma",
            "MMM dd ha",
            "MMM dd hh'.'mma",
            "MMM dd HHmm",
            "dd MMM HHmm",
            "d/M HHmm",
            "MMM dd ha",
            "dd MMM ha",
            "dd MMM hh'.'mma",
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

    private static final String MISSING_TIME_ERROR_MESSAGE = "Please enter an interview time!";
    private static final String PAST_DATE_ERROR_MESSAGE = "Please specify a valid date!";

    /**
     * Converts the string date into a Datetime object.
     *
     * @author Tan Kerway
     * @param time the String that contains the data for the date
     * @return datetime object that represents the string
     */
    public static LocalDateTime parseDate(String time)
            throws seedu.address.logic.parser.exceptions.ParseException {
        for (int currentFormatID = 0; currentFormatID < DATE_FORMATS.length; currentFormatID++) {
            // find a date format string that matches the user pattern
            for (String formatString : DATE_FORMATS[currentFormatID]) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
                    simpleDateFormat.setLenient(false);
                    LocalDateTime temp = simpleDateFormat
                            .parse(time)
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    return addMissingDateFields(temp, currentFormatID);
                } catch (ParseException ignored) {
                    String s = "";
                }
            }
        }
        throw new seedu.address.logic.parser.exceptions.ParseException("Please specify a valid date!");
    }

    /**
     * Returns the interviews which clash with the current interview
     *
     * @author Tan Kerway
     * @param startTime the start time of the interview that the user wants to schedule
     * @param endTime the end time of the interview that the user wants to schedule
     * @param interviews the list of interviews that the user has already
     *                   scheduled
     * @return a list of interviews that clash with the interview to be
     *         scheduled
     */
    public static List<Interview> listInterviewClashes(LocalDateTime startTime,
                                                       LocalDateTime endTime, UniqueInterviewList interviews) {
        List<Interview> res = new ArrayList<>();
        for (Interview currentInterview : interviews) {
            // get the start time of the current interview
            LocalDateTime currentInterviewStartTime = currentInterview.getInterviewStartTime();
            // get the end time of the current interview
            LocalDateTime currentInterviewEndTime = currentInterview.getInterviewEndTime();
            // case 1: the current interview is completely within the interview to be added
            boolean completelyInside = (currentInterviewStartTime.isAfter(startTime)
                    && currentInterviewStartTime.isBefore(endTime))
                    && (currentInterviewEndTime.isAfter(startTime)
                    && currentInterviewEndTime.isBefore(endTime));
            // case 2: the interview to be added is completely within the current interview
            boolean completelyOutside = currentInterviewStartTime.isBefore(startTime)
                    && currentInterviewEndTime.isAfter(endTime);
            // case 3: the current interview end time falls within the interview to be added
            boolean endInside = currentInterviewEndTime.isAfter(startTime)
                    && currentInterviewEndTime.isBefore(endTime);
            // case 4: the current interview start time falls within the interview to be added
            boolean startInside = currentInterviewStartTime.isAfter(startTime)
                    && currentInterviewStartTime.isBefore(endTime);
            if (completelyInside || completelyOutside || endInside || startInside) {
                res.add(currentInterview);
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * Lists out all interviews that have a start time of today
     *
     * @author Tan Kerway
     * @param interviews the list of interviews that the user has
     * @return a list of interviews whose start time is today, as given by LocalDateTime.now()
     */
    public static List<Interview> listInterviewsToday(UniqueInterviewList interviews) {
        // get today's day, month, and year for checking
        LocalDateTime today = LocalDateTime.now();
        int todayDay = today.getDayOfMonth();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        List<Interview> res = new ArrayList<>();
        // loop over all the interviews, and add those that have today as the start time
        for (Interview interview : interviews) {
            LocalDateTime currentInterviewStartTime = interview.getInterviewStartTime();
            int currentInterviewDay = currentInterviewStartTime.getDayOfMonth();
            int currentInterviewMonth = currentInterviewStartTime.getMonthValue();
            int currentInterviewYear = currentInterviewStartTime.getYear();
            if (currentInterviewDay == todayDay
                    && currentInterviewMonth == todayMonth
                    && currentInterviewYear == todayYear) {
                res.add(interview); // add the current interview if its start date is today
            }
        }
        return res;
    }

    /**
     * Appends missing date fields depending on what the user typed in.
     * @author Tan Kerway
     * @param temp the LocalDateTime object to modify
     * @param currentFormatID the given type of date object
     * @return a LocalDateTime object that has all the required information
     */
    private static LocalDateTime addMissingDateFields(LocalDateTime temp, int currentFormatID)
            throws seedu.address.logic.parser.exceptions.ParseException {
        switch (currentFormatID) {
        case 0: // case where user entered a day of the week and time
            temp = addDay(temp);
            break;
        case 1: // case where user entered a year, month, day of month, and time
            break;
        case 2: // case where the user entered a month, day of month, and time
            temp = addYear(temp);
            break;
        default: // case where the user did not enter an interview time
            // inform the user that their input is missing an interview time
            throw new seedu.address.logic.parser.exceptions.ParseException(MISSING_TIME_ERROR_MESSAGE);
        }
        // guard clause: the given date is before today's date even after parsing
        if (temp.isBefore(LocalDateTime.now())) {
            throw new seedu.address.logic.parser.exceptions.ParseException(PAST_DATE_ERROR_MESSAGE);
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
     * Sorts the list of interviews in ascending chronological order.
     *
     * @author Tan Kerway
     *
     */
    public static List<Interview> sortInterviewsInChronologicalAscendingOrder(UniqueInterviewList interviews) {
        List<Interview> res = new ArrayList<>();
        for (Interview interview : interviews) {
            res.add(interview);
        }
        res.sort(Comparator.comparing(Interview::getInterviewStartTime));
        return res;
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
        return time.format(DateTimeFormatter.ofPattern("d/M/yy HHmm"));
    }
}
