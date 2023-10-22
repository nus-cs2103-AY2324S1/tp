package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.attendance.exceptions.DuplicateAttendanceException;
import seedu.address.model.attendance.exceptions.AttendanceNotFoundException;

/**
 * A list of attendance that enforces uniqueness between its elements and does not allow nulls.
 * An attendance is considered unique by comparing using {@code Attendance#equals(Object)}.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueAttendanceList implements Iterable<Attendance> {

    private final ObservableList<Attendance> internalList = FXCollections.observableArrayList();
    private final ObservableList<Attendance> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent attendance as the given argument.
     */
    public boolean contains(Attendance attendanceToCheck) {
        requireNonNull(attendanceToCheck);
        return internalList.stream().anyMatch(attendanceToCheck::equals);
    }

    /**
     * Adds an attendance to the list.
     * The attendance must not already exist in the list.
     */
    public void createAttendance(Attendance attendanceToCreate) {
        requireNonNull(attendanceToCreate);
        if (contains(attendanceToCreate)) {
            throw new DuplicateAttendanceException();
        }
        internalList.add(attendanceToCreate);
    }

    /**
     * Replaces the attendance {@code target} in the list with {@code editedAttendance}.
     * {@code target} must exist in the list.
     * The {@code editedAttendance} must not be the same as another attendance existing  in the list.
     */
    public void setAttendance(Attendance targetAttendance, Attendance editedAttendance) {
        requireAllNonNull(targetAttendance, editedAttendance);

        int index = internalList.indexOf(targetAttendance);
        if (index == -1) {
            throw new AttendanceNotFoundException();
        }

        if (!targetAttendance.equals(editedAttendance) && contains(editedAttendance)) {
            throw new DuplicateAttendanceException();
        }

        internalList.set(index, editedAttendance);
    }

    /**
     * Removes the equivalent attendance from the list.
     * The attendance must exist in the list.
     */
    public void remove(Attendance toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AttendanceNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     */
    public void setAttendances(UniqueAttendanceList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code attendances}.
     * {@code attendances} must not contain duplicate attendances.
     */
    public void setAttendances(List<Attendance> attendances) {
        requireAllNonNull(attendances);
        if (!attendancesAreUnique(attendances)) {
            throw new DuplicateAttendanceException();
        }

        internalList.setAll(attendances);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Attendance> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Attendance> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueAttendanceList)) {
            return false;
        }

        UniqueAttendanceList otherUniqueAttendanceList = (UniqueAttendanceList) other;
        return internalList.equals(otherUniqueAttendanceList.internalList);
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
     * Returns true if {@code attendances} contains only unique attendances.
     */
    private boolean attendancesAreUnique(List<Attendance> attendances) {
        for (int i = 0; i < attendances.size() - 1; i++) {
            for (int j = i + 1; j < attendances.size(); j++) {
                if (attendances.get(i).equals(attendances.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
