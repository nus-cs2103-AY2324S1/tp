package seedu.address.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;

public class FreeTime implements Iterable<TimeInterval> {

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

    public void addTime(ArrayList<TimeInterval> timeIntervals) {
        internalList.addAll(timeIntervals);
    }

    public void deleteTime(ArrayList<TimeInterval> timeIntervals) {
        for (TimeInterval time : timeIntervals) {
            if (internalList.contains(time)) {
                System.out.println(time);
                internalList.remove(time);
            }
        }
//        internalList.removeAll(timeIntervals);
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

    @Override
    public Iterator<TimeInterval> iterator() {
        return this.internalList.iterator();
    }
}
