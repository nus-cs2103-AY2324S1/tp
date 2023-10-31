package seedu.address.model.timeslots;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.timeslots.exceptions.DuplicateTimeslotException;
import seedu.address.model.timeslots.exceptions.TimeSlotNotFoundException;

/**
 * A UniqueTimeSlotList class that is used for JavaFX FrontEnd
 */
public class UniqueTimeSlotList implements Iterable<Timeslot> {

    private final ObservableList<Timeslot> internalList = FXCollections.observableArrayList();
    private final ObservableList<Timeslot> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Timeslot as the given argument.
     */
    public boolean contains(Timeslot toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTimeSlot);
    }

    /**
     * Adds a Timeslot to the list if it is not already present.
     * @param toAdd Timeslot instance we are adding to the list
     */
    public void add(Timeslot toAdd) {
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            internalList.add(toAdd);
            FXCollections.sort(internalList, Comparator.comparingInt(Timeslot::getHour));
        }
    }


    /**
     * Removes the equivalent Timeslot from the list.
     * The Timeslot must exist in the list.
     */
    public void remove(Timeslot toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TimeSlotNotFoundException();
        }
    }

    /**
     * Sets the UniqueTimeSlotList from current to the replacement given
     * @param replacement UniqueTimeSlotList we want to set current List to
     */
    public void setTimeslots(UniqueTimeSlotList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code timeslot}.
     * {@code timeslot} must not contain duplicate Timeslot.
     */
    public void setTimeslots(List<Timeslot> timeSlotsList) {
        requireAllNonNull(timeSlotsList);
        if (!timeSlotsAreUnique(timeSlotsList)) {
            throw new DuplicateTimeslotException();
        }

        internalList.setAll(timeSlotsList);
    }

    /**
     * Adds to the contents of this list with {@code timeslot}.
     * {@code timeslot} must not contain duplicate Timeslot.
     */
    public void addAll(List<Timeslot> timeslotsList) {
        requireAllNonNull(timeslotsList);
        if (!timeSlotsAreUnique(timeslotsList)) {
            throw new DuplicateTimeslotException();
        }

        internalList.addAll(timeslotsList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Timeslot> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void setTimeslotsList(List<Timeslot> timeslotsList) {
        requireNonNull(timeslotsList);
        if (!timeSlotsAreUnique(timeslotsList)) {
            throw new DuplicateTimeslotException();
        }
    }

    @Override
    public Iterator<Timeslot> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTimeSlotList)) {
            return false;
        }

        UniqueTimeSlotList otherUniqueTimeSlotList = (UniqueTimeSlotList) other;
        return internalList.equals(otherUniqueTimeSlotList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns the size of the UniqueTimeSlotList
     * @return Size of the internalList instance
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns true if {@code timeslot} contains only unique Timeslot.
     */
    private boolean timeSlotsAreUnique(List<Timeslot> timeslotsList) {
        for (int i = 0; i < timeslotsList.size() - 1; i++) {
            for (int j = i + 1; j < timeslotsList.size(); j++) {
                if (timeslotsList.get(i).isSameTimeSlot(timeslotsList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
