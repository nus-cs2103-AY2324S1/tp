package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.model.schedule.exceptions.ScheduleNotFoundException;

/**
 * A sorted list of schedules that enforces uniqueness between its elements and does not allow nulls.
 * A Schedule is considered unique by comparing using {@code Schedule#equals(Object)}. As such, adding and
 * updating of schedules uses Schedule#equals(Object) for equality so as to ensure that the schedule being added
 * or updated is unique in terms of identity in the UniqueScheduleList. The removal of a schedule uses
 * Schedule#equals(Object) so as to ensure that the schedule with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Schedule#equals(Object)
 * @see Schedule#compareTo(Schedule)
 */
public class UniqueScheduleList implements Iterable<Schedule> {
    private final ObservableList<Schedule> internalList = FXCollections.observableArrayList();
    private final ObservableList<Schedule> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent schedule as the given argument.
     */
    public boolean contains(Schedule toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isDuplicate);
    }

    /**
     * Adds a schedule to the list.
     * The schedule must not already exist in the list.
     */
    public void add(Schedule toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateScheduleException();
        }
        internalList.add(toAdd);
        sort();
    }

    /**
     * Replaces the schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in the list.
     * The {@code editedSchedule} must not be the same as another existing schedule in the list.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireAllNonNull(target, editedSchedule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ScheduleNotFoundException();
        }

        if (!target.isDuplicate(editedSchedule) && contains(editedSchedule)) {
            throw new DuplicateScheduleException();
        }

        internalList.set(index, editedSchedule);
    }

    /**
     * Removes the equivalent schedule from the list.
     * The schedule must exist in the list.
     * {@code toRemove} is the schedule to remove from the list.
     */
    public void remove(Schedule toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ScheduleNotFoundException();
        }
    }

    /**
     * Replaces the list with {@code replacement}.
     * {@code replacement} must be a UniqueScheduleList.
     */
    public void setSchedules(UniqueScheduleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code schedules}.
     * {@code schedules} must not contain duplicate schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        requireAllNonNull(schedules);
        if (!schedulesAreUnique(schedules)) {
            throw new DuplicateScheduleException();
        }

        internalList.setAll(schedules);
    }

    /**
     * Sorts the {@code internalList} of schedules based on {@link Schedule#compareTo(Schedule)} method.
     */
    public void sort() {
        FXCollections.sort(internalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Schedule> asUnmodifiableObservableList() {
        sort();
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Schedule> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueScheduleList)) {
            return false;
        }

        UniqueScheduleList otherUniqueScheduleList = (UniqueScheduleList) other;
        return internalList.equals(otherUniqueScheduleList.internalList);
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
     * Returns true if {@code schedules} contains only unique schedules.
     */
    private boolean schedulesAreUnique(List<Schedule> schedules) {
        for (int i = 0; i < schedules.size() - 1; i++) {
            for (int j = i + 1; j < schedules.size(); j++) {
                if (schedules.get(i).isDuplicate(schedules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
