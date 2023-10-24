package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.parser.ParserUtil;

import javax.xml.stream.FactoryConfigurationError;

public class TimeInterval {

    public static final String MESSAGE_CONSTRAINTS_SYNTAX = "The format of an interval should be: mon 1200 - tue 1400";

    public static final String MESSAGE_CONSTRAINTS_LOGIC = "Your end time cannot be before your start time ";

    public static final String MESSAGE_CONSTRAINTS_OVERLAP = "No overlap is allowed in your interval. Eg. "
        + "mon 1200 - mon 1600 ;mon 1400 - mon 1800 is not allowed. Write it as mon 1200 - mon 1800";


    public static final String VALIDATION_REGEX = ".* .* - .* .*";
    private final Time start;
    private final Time end;

    /**
     * TimeInterval constructor.
     * @param start The start time of the interval.
     * @param end The end time of the interval.
     */
    public TimeInterval(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Compares timeInterval with another Time interval using the start time.
     * @param other The other timeInterval.
     * @return The int depending on if it is less than/more than/equal to the other timeInterval.
     */
    public int compareToStartTime(TimeInterval other) {
        requireNonNull(other);
        return this.start.compareTo(other.start);
    }

    /**
     * Returns true if the timeInterval overlaps with one another.
     * @param intervals ArrayList of TimeIntervals.
     * @return Returns true if the timeInterval overlaps with one another.
     */
    public static boolean isTimeIntervalOverlap(ArrayList<TimeInterval> intervals) {
        intervals.sort(TimeInterval::compareToStartTime);
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i).end.compareTo(intervals.get(i + 1).start) > -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the interval string is in right format.
     * @param timeInterval The timeInterval to be checked.
     * @return Returns true if the interval string is in right format.
     */
    public static boolean isValidTimeIntervalSyntax(String timeInterval) {
        return timeInterval.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if start time is less than end time.
     * @param start Start time.
     * @param end End time.
     * @return Returns true if start time is less than end time.
     */
    public static boolean isValidTimeIntervalLogic(Time start, Time end) {
        return start.compareTo(end) <= -1;
    }

    /**
     * Gets the start time of the interval
     * @return Start time
     */
    public Time getStart() {
        return this.start;
    }

    /**
     * Gets the end time of the interval
     * @return end time
     */
    public Time getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return start.toString() + " - " + end.toString() + " ";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TimeInterval)) {
            return false;
        } else if (object == this) {
            return true;
        } else {
            TimeInterval otherTime = (TimeInterval) object;
            return this.start.equals(otherTime.start) && this.end.equals(otherTime.end);
        }
    }
}
