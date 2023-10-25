package seedu.address.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;

public class TimeIntervalList implements Iterable<TimeInterval> {

    private final ObservableList<TimeInterval> internalList = FXCollections.observableArrayList();
    private final ObservableList<TimeInterval> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    public void addTime(TimeInterval timeInterval) {
        internalList.add(timeInterval);
    }

    /**
     * Converts the internal list to streams.
     *
     * @return Internal list into streams.
     */
    public Stream<TimeInterval> toStream() {
        return internalList.stream();
    }

    public void addTime(ArrayList<TimeInterval> timeIntervals) throws CommandException{
        StringBuilder errorCompilation = new StringBuilder();
        errorCompilation.append("There is a clash in these timings:\n");
        for (TimeInterval interval : timeIntervals) {
            if (isTimeIntervalOverlap(interval)) {
                errorCompilation.append(interval + "\n");
            } else {
                internalList.add(interval);
            }
        }

        errorCompilation.append("The other times have been added\n");
        if (errorCompilation.length() > 67) {
            throw new CommandException(errorCompilation.toString());
        }
    }

    public void addAll(TimeIntervalList timeIntervalList) {
        for (TimeInterval timeInterval : timeIntervalList) {
            this.internalList.add(timeInterval);
        }
    }

    public void deleteTime(ArrayList<TimeInterval> timeIntervals) throws CommandException {
        StringBuilder errorCompilation = new StringBuilder();
        errorCompilation.append("These times are not in the list:\n");
        for (TimeInterval time : timeIntervals) {
            if (!internalList.contains(time)) {
                errorCompilation.append(time + "\n");
            } else {
                internalList.remove(time);
            }
        }
        errorCompilation.append("The other times have been deleted\n");

        if (errorCompilation.length() > 67) {
            throw new CommandException(errorCompilation.toString());
        }
    }

    /**
     * Checks whether timeInterval contains the time
     * @param timeInterval The time Interval to check
     * @return Whether time interval is in list
     */
    public boolean hasTime(TimeInterval timeInterval) {
        return internalList.contains(timeInterval);
    }

    /**
     * Removes free time from list
     * @param timeInterval The time interval to remove
     */
    public void removeTime(TimeInterval timeInterval) {
        internalList.remove(timeInterval);
    }

    /**
     * Checks if time interval overlaps with internal list
     * @param interval The time iunterval to check
     * @return Whether time interval overlaps with internal list
     */
    public boolean isTimeIntervalOverlap(TimeInterval interval) {
        for (TimeInterval time : internalList) {
            int startComparison = time.compareStart(interval);
            int endComparison = time.compareEnd(interval);
            boolean noClash = ((startComparison < 0 && endComparison < 0)|| (startComparison > 0 && endComparison > 0));
            if ((startComparison == 0 && endComparison == 0) || !noClash) {
                return true;
            }
        }
        return false;
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
