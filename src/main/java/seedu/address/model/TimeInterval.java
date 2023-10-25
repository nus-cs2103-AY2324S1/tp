package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.parser.ParserUtil;

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
     * Check whether an interval overlaps another interval in any way
     * Interval overlapping at start and end points not considered as overlap does not form interval
     * @param t
     * @return
     */
    public boolean isTimeIntervalOverlapWithTimeInterval(TimeInterval t) {
        // interval1.start < interval2.end (if interval1.start = interval2.end, is not overlapping)
        // and interval1.end > interval2.start
        // ||
        // interval1.end > interval2.start (interval1.start = interval2.end means not overlapping)
        // and interval1.start < interval2.end

        boolean firstCase = this.start.compareTo(t.end) < 0 && this.end.compareTo(t.start) > 0;
        boolean secondCase = this.start.compareTo(t.end) > 0 && this.start.compareTo(t.end) < 0;
        boolean overLap = firstCase || secondCase;
        return overLap;
    }


    /**
     * Check if Intervals are equal in terms of time representation and not exact object comparison
     * @param otherInterval that we are comparing to
     * @return boolean whether otherInterval is equal in terms of time representation
     */
    public boolean equalStartAndEnd(TimeInterval otherInterval) {
        return this.start.compareTo(otherInterval.start) == 0 && this.end.compareTo(otherInterval.end) == 0;
    }

    public static TimeInterval getMaxStart(TimeInterval first, TimeInterval second) {
        // if both have the same MaxStart then by default return first
        return first.getStart().compareTo(second.getStart()) >= 0 ? first : second;
    }

    public static TimeInterval getMinEnd(TimeInterval first, TimeInterval second) {
        // if both have the same MinEnd then by default return first
        return first.getEnd().compareTo(second.getEnd()) <= 0 ? first : second;
    }

    /**
     * Create new time interval with MaxStart and MinEnd
     * @param otherInterval
     * @return
     */
    public TimeInterval getIntersect(TimeInterval otherInterval) {
        return new TimeInterval(this.getStart(), otherInterval.getEnd());
    }

    /**
     * Check if time interval can accomodate for a given duration
     * Whether duration can fit in interval
     * @param duration of meeting in consideration
     * @return boolean whether meeting is permissible
     */
    public boolean allows(Duration duration) {
        int durationInMin = duration.getDurationInMin();
        // assume start < end always hold, property of timeInterval
        int durationStart = this.start.getDurationInMin();
        int durationEnd = this.end.getDurationInMin();
        boolean allows = durationEnd - durationStart >= durationInMin;
        return allows;
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
}
