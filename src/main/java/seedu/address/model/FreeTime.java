package seedu.address.model;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FreeTime implements Iterable<TimeInterval> {

    private final ObservableList<TimeInterval> internalList = FXCollections.observableArrayList();
    private final ObservableList<TimeInterval> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);

    public void addTime(TimeInterval timeInterval) {
        internalList.add(timeInterval);
    }

    public void addTime (ArrayList<TimeInterval> timeIntervals) {
        internalList.addAll(timeIntervals);
    }

    @Override
    public Iterator<TimeInterval> iterator() {
        return this.internalList.iterator();
    }
}
