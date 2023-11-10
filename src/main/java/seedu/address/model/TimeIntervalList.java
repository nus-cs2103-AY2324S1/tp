package seedu.address.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Class representing the list of time intervals
 */
public class TimeIntervalList implements Iterable<TimeInterval> {

    private final ObservableList<TimeInterval> internalList = FXCollections.observableArrayList();
    private final ObservableList<TimeInterval> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    public void addTime(TimeInterval timeInterval) {
        internalList.add(timeInterval);
    }

    /**
     * Adds time to the current TimeInterval list
     *
     * @param timeIntervals ArrayList of timeIntervals
     * @return the status after adding the time
     */
    public String addTime(ArrayList<TimeInterval> timeIntervals) {
        boolean isPass = false;
        boolean isFail = false;
        StringBuilder errorMessage = new StringBuilder("There is a clash in these input timings with "
            + "your existing timings:\n");
        StringBuilder passMessage = new StringBuilder("These times have been added:\n");
        for (TimeInterval interval : timeIntervals) {
            if (isTimeIntervalOverlap(interval)) {
                isFail = true;
                errorMessage.append(interval + "\n");
            } else {
                isPass = true;
                internalList.add(interval);
                passMessage.append(interval.toString() + "\n");
            }
        }

        if (isFail && isPass) {
            return errorMessage.append(passMessage).toString();
        } else if (isFail) {
            return errorMessage.toString();
        } else {
            return passMessage.toString();
        }
    }

    /**
     * Converts the internal list to streams.
     *
     * @return Internal list into streams.
     */
    public Stream<TimeInterval> toStream() {
        return internalList.stream();
    }

    /**
     * Adds all the timeInterval in to current timeInterval list
     * @param timeIntervalList TimeIntervalList to be added
     */
    public void addAll(TimeIntervalList timeIntervalList) {
        for (TimeInterval timeInterval : timeIntervalList) {
            this.internalList.add(timeInterval);
        }
    }

    /**
     * Deletes a list of time intervals from the time interval list
     * @param timeIntervals The list of time intervals to be deleted
     * @return The message of time that has and has not been deleted
     * @throws CommandException Throws exception if time interval list does not contain the time when being removed,
     *                          which should not happen as we check using the contains method
     */
    public String deleteTime(ArrayList<TimeInterval> timeIntervals) throws CommandException {
        boolean isPass = false;
        boolean isFail = false;
        StringBuilder errorMessage = new StringBuilder("These times were not in the list:\n");
        StringBuilder passMessage = new StringBuilder("These times have been deleted:\n");
        for (TimeInterval interval : timeIntervals) {
            if (!internalList.contains(interval)) {
                isFail = true;
                errorMessage.append(interval + "\n");
            } else {
                isPass = true;
                internalList.remove(interval);
                passMessage.append(interval.toString() + "\n");
            }
        }

        if (isFail && isPass) {
            return errorMessage.append(passMessage).toString();
        } else if (isFail) {
            return errorMessage.toString();
        } else {
            return passMessage.toString();
        }
    }

    /**
     * Checks whether timeInterval contains the time
     *
     * @param timeInterval The time Interval to check
     * @return Whether time interval is in list
     */
    public boolean hasTime(TimeInterval timeInterval) {
        return internalList.contains(timeInterval);
    }

    /**
     * Removes free time from list
     *
     * @param timeInterval The time interval to remove
     */
    public void removeTime(TimeInterval timeInterval) {
        internalList.remove(timeInterval);
    }

    /**
     * Checks if time interval overlaps with internal list
     *
     * @param interval The time iunterval to check
     * @return Whether time interval overlaps with internal list
     */
    public boolean isTimeIntervalOverlap(TimeInterval interval) {
        for (TimeInterval time : internalList) {
            if (interval.isClash(time)) {
                return true;
            }
        }
        return false;
    }

    /////////////////////

    /**
     * Check whether no time is stored
     *
     * @return boolean representing whether no time is stored
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }


    /**
     * Generate String representing list of intervals
     *
     * @param br     StringBuilder to store  message
     * @param format specify Message format
     */
    public void getMessage(StringBuilder br, String format) {
        int intervalCount = 1;
        for (TimeInterval t : this.internalList) {
            br.append(String.format(format, intervalCount, t.toString()));
            intervalCount++;
        }

    }

    /**
     * Filter a TimeIntervalList to contain only intervals that fit the duration
     *
     * @param duration represent time in minutes
     * @return TimeIntervalList with duration greater than duration specified
     */
    public TimeIntervalList fitDuration(Duration duration) {
        TimeIntervalList personTime = new TimeIntervalList();
        for (TimeInterval interval : internalList) {
            if (interval.allows(duration)) {
                personTime.addTime(interval);
            }
        }
        return personTime;
    }

    /**
     * Finds the overlap between 2 free times and a duration
     * @param otherTime the other free times to compare with
     * @param duration the duration of overlap needed
     * @return a list of times that there is an overlap in time interval
     */
    public TimeIntervalList findOverlap(TimeIntervalList otherTime, Duration duration) {
        // 4 steps, sort by start time, min start min end 2 pointers, get overlap, interval >= duration check
        this.internalList.sort(TimeInterval::compareStart);
        otherTime.internalList.sort(TimeInterval::compareStart);
        TimeIntervalList newFreeTime = new TimeIntervalList();

        int p1 = 0;
        int p2 = 0;

        while (p1 < this.internalList.size() && p2 < otherTime.internalList.size()) {
            TimeInterval firstListInterval = this.internalList.get(p1);
            TimeInterval secondListInterval = otherTime.internalList.get(p2);

            boolean overLap = firstListInterval.isTimeIntervalOverlapWithTimeInterval(secondListInterval);
            // given 2 intervals, return the one with smaller end to increment pointer
            TimeInterval intervalWithMaxStart = TimeInterval.getMaxStart(firstListInterval, secondListInterval);
            TimeInterval intervalWithMinEnd = TimeInterval.getMinEnd(firstListInterval, secondListInterval);
            // store the intersect, 2 steps, get intersect, see if can fit duration
            if (overLap) {
                TimeInterval intersect = intervalWithMaxStart.getIntersect(intervalWithMinEnd);
                if (intersect.allows(duration)) {
                    newFreeTime.addTime(intersect);
                }
            }
            // increment pointers
            if (intervalWithMinEnd.equalStartAndEnd(firstListInterval)) {
                p1++;
            } else {
                p2++;
            }
        }

        return newFreeTime;
    }

    public int size() {
        return this.internalList.size();
    }

    @Override
    public Iterator<TimeInterval> iterator() {
        return this.internalList.iterator();
    }

    @Override
    public String toString() {
        String toString = "";
        for (TimeInterval timeInterval : internalList) {
            toString += "\n" + timeInterval;
        }
        return toString;
    }
}
