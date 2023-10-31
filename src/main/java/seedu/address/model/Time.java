package seedu.address.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;

/**
 * Class which stores methods related to time, but are not related
 * to parsing dates.
 *
 * @author Tan Kerway
 */
public class Time implements Comparable<Time> {
    /*
     * Class-level Time constants
     */
    private static final LocalTime WORK_START = LocalTime.of(9, 0);
    private static final LocalTime WORK_END = LocalTime.of(17, 0);

    /*
     * Instance-level Time fields
     */
    private final LocalDateTime time;

    /**
     * Constructs a Time instance based on the input LocalDateTime.
     *
     * @author Tan Kerway
     * @param time the LocalDateTime to represent with the Time instance
     */
    public Time(LocalDateTime time) {
        this.time = time;
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
     * Compiles a list of free times that the user has. Each element is a 2-element list where the
     * first element is the start of the free time block, and the second element is the end of the
     * free time block. Only places interviews that are within a 9-5 workday. Assumes that the given
     * interview list has no clashes. Also assumes that the start time of any scheduled interviews are less
     * than or equals to their corresponding end time.
     *
     * @author Tan Kerway
     * @param day the day that the user inputs
     * @param interviewList the list of interviews that the user has
     * @return a list of free time blocks that the user has on a given day
     */
    public static List<List<LocalDateTime>> listPocketsOfTimeOnGivenDay(
            LocalDateTime day,
            UniqueInterviewList interviewList) {
        // filter the interviews that fall on the given day, and sort in ascending
        // chronological order
        List<Interview> interviewsOnGivenDay =
                listInterviewsOnGivenDay(day, interviewList);
        UniqueInterviewList temp = new UniqueInterviewList();
        temp.setInterviews(interviewsOnGivenDay);
        List<Interview> interviewsOnGivenDaySorted = sortInterviewsInChronologicalAscendingOrder(temp);
        List<List<LocalDateTime>> res = new ArrayList<>();
        LocalDateTime startOfWorkDay = LocalDateTime.of(
                day.getYear(),
                day.getMonthValue(),
                day.getDayOfMonth(),
                9,
                0);
        LocalDateTime endOfWorkDay = LocalDateTime.of(
                day.getYear(),
                day.getMonthValue(),
                day.getDayOfMonth(),
                17,
                0);
        // track the previous end time of the interview
        LocalDateTime prevEnd = startOfWorkDay.plusDays(0);

        // find free time in 24h window
        for (Interview interview : interviewsOnGivenDaySorted) {
            // get the start time and end time
            LocalDateTime currentInterviewStartTime = interview.getInterviewStartTime();
            LocalDateTime currentInterviewEndTime = interview.getInterviewEndTime();
            // case 1: the workday is completely overlapped by the interview
            if (currentInterviewStartTime.isBefore(startOfWorkDay)
                    && currentInterviewEndTime.isAfter(endOfWorkDay)) {
                prevEnd = currentInterviewEndTime.plusDays(0);
                break;
            }
            // case 2: the workday is outside and before the workday
            if (currentInterviewEndTime.isBefore(startOfWorkDay)) {
                continue;
            }
            // case 3: the workday is outside and after the workday
            if (currentInterviewStartTime.isAfter(endOfWorkDay)) {
                break;
            }
            // case 4: the interview's start point is before the start of workday
            // and the interview's end point is after the start of the workday
            if (currentInterviewStartTime.isBefore(startOfWorkDay)
                    && currentInterviewEndTime.isAfter(startOfWorkDay)) {
                prevEnd = currentInterviewEndTime.plusDays(0);
                continue;
            }
            // get the current block of free time by taking the end of the last interval
            // and the start of the current interval
            List<LocalDateTime> currentFreeTime = new ArrayList<>();
            currentFreeTime.add(prevEnd);
            currentFreeTime.add(currentInterviewStartTime);
            res.add(currentFreeTime);
            prevEnd = currentInterviewEndTime;
        }

        // add stray free time, if any
        if (prevEnd.isBefore(endOfWorkDay)) {
            List<LocalDateTime> strayFreeTime = new ArrayList<>();
            strayFreeTime.add(prevEnd);
            strayFreeTime.add(endOfWorkDay);
            res.add(strayFreeTime);
        }
        return res;
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
     * Compiles a list of interviews that the user has on a given day
     *
     * @author Tan Kerway
     *
     */
    public static List<Interview> listInterviewsOnGivenDay(LocalDateTime day, UniqueInterviewList interviews) {
        int todayDay = day.getDayOfMonth();
        int todayMonth = day.getMonthValue();
        int todayYear = day.getYear();
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
     * Compares the other given time to this instance.
     *
     * @author Tan Kerway
     * @param otherTime the other time to compare with this instance
     */
    @Override
    public int compareTo(Time otherTime) {
        return this.time.compareTo(otherTime.time);
    }

    /**
     * Checks whether this instance is before the given time.
     *
     * @author Tan Kerway
     * @param otherTime the other time to compare to
     * @return true if this instance is before the otherTime, false otherwise
     */
    public boolean isBefore(Time otherTime) {
        return this.time.isBefore(otherTime.time);
    }

    /**
     * Checks whether this instance is after the given time.
     *
     * @author Tan Kerway
     * @param otherTime the other time to compare to
     * @return true if this instance is after the otherTime, false otherwise
     */
    public boolean isAfter(Time otherTime) {
        return this.time.isBefore(otherTime.time);
    }

    /**
     * Returns true if startTime and endTime are within working hours,
     * which is defined to be between 0900 and 1700.
     *
     * @author Tan Jing Jie, Tan Kerway
     * @return true if within the working hours, false otherwise
     */
    public boolean isWithinWorkingHours() {
        LocalTime timeFields = this.time.toLocalTime();
        return (timeFields.isAfter(WORK_START) || timeFields.equals(WORK_START))
                && (timeFields.isBefore(WORK_END) || timeFields.equals(WORK_END));
    }

    /**
     * Returns the time associated with the current Time instance.
     *
     * @author Tan Kerway
     * @return a LocalTime object instance containing the time of the current instance
     */
    public LocalTime getTime() {
        return this.time.toLocalTime();
    }

    /**
     * Returns the date associated with the current Time instance.
     *
     * @author Tan Kerway
     * @return a LocalDate object instance containing the date of the current instance
     */
    public LocalDate getDate() {
        return this.time.toLocalDate();
    }

    /**
     * Returns the date and time associated with the current Time instance.
     *
     * @author Tan Kerway
     * @return a copy of the LocalTime object instance containing the date and time of the current instance
     */
    public LocalDateTime getDateAndTime() {
        return this.time.plusDays(0);
    }

    /**
     * Checks if the given Object instance is equals to
     * this Time instance.
     *
     * @author Tan Kerway
     * @param otherObject the other object to compare to
     * @return true if this Time instance is equals, and false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        // guard clause: the given object does not have a run time type of Time
        if (!(otherObject instanceof Time)) {
            return false;
        }
        // else, we know the given object is an instance of time,
        // hence, it is safe to cast to Time
        return this.time.equals(((Time) otherObject).time);
    }

    /**
     * Returns the hashcode of the current Time instance. Effectively returns
     * the hashcode of the time field.
     *
     * @author Tan Kerway
     * @return the hashcode of the current Time instance
     */
    @Override
    public int hashCode() {
        return this.time.hashCode();
    }

    /**
     * Returns the String representation of the Time instance. Effectively returns the String
     * representation of the String since the Time class is merely a wrapper class for Time.
     *
     * @author Tan Kerway
     * @return the String representation of the Time object
     */
    @Override
    public String toString() {
        return this.time.toString();
    }
}
