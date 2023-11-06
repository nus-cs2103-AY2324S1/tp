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
    public static final LocalTime WORK_START = LocalTime.of(9, 0);
    public static final LocalTime WORK_END = LocalTime.of(17, 0);

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
     * Checks whether two LocalDateTimes are the same.
     *
     * @author Tan Kerway
     *
     */
    public boolean isSameDay(Time otherTime) {
        return this.time.toLocalDate().equals(otherTime.getDate());
    }

    /**
     * Checks whether the given LocalDateTime so happens to be today.
     *
     * @author Tan Kerway
     */
    public boolean isToday() {
        return isSameDay(new Time(LocalDateTime.now()));
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
     * Returns true if this Time instance is after the given time.
     */
    public boolean isAfter(Time otherTime) {
        return this.time.isAfter(otherTime.time);
    }

    /**
     * Returns true if this Time instance is between the given time,
     * exclusive of the start and end time.
     */
    public boolean isBetween(Time start, Time end) {
        return isAfter(start) && isBefore(end);
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
     * Returns true if the time field's datetime is before the current datetime.
     */
    public boolean isPast() {
        return time.isBefore(LocalDateTime.now());
    }

    /**
     * Returns true if the time field's date is before the current date.
     * Only compares date without the time.
     */
    public boolean isPastDate() {
        return time.toLocalDate().isBefore(LocalDate.now());
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
