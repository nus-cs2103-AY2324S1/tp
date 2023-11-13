package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;

/**
 * A list of meetings that enforces uniqueness between its elements and does not allow nulls.
 * A meeting is considered unique by comparing using {@code Meeting#isSameMeeting(Meeting)}.
 * As such, adding and updating of meetings uses Meeting#isSameMeeting(Meeting) for equality
 * so as to ensure that the meeting being added or updated is unique in terms of identity in
 * the UniqueMeetingList. However, the removal of a meeting uses Meeting#equals(Object) so
 * as to ensure that the meeting with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Meeting#isSameMeeting(Meeting)
 */
public class UniqueMeetingList implements Iterable<Meeting> {

    private final Comparator<Meeting> sortByTimeComparator = Comparator.comparing(Meeting::getStart);
    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList;

    /**
     * Creates a UniqueMeetingList object that is sorted by start date.
     */
    public UniqueMeetingList() {
        FXCollections.sort(internalList, sortByTimeComparator);
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Returns true if the list contains an equivalent Meeting as the given argument.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMeeting);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not already exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        internalList.add(toAdd);
        FXCollections.sort(internalList, sortByTimeComparator);
    }

    /**
     * Replaces the meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing person in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.isSameMeeting(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicateMeetingException();
        }

        internalList.set(index, editedMeeting);
        FXCollections.sort(internalList, sortByTimeComparator);
    }

    /**
     * Updates the given attendee {@code targetAttendee} with {@code editedAttendee} for all meetings.
     * @param targetAttendee The Attendee name to be replaced from all meetings.
     * @param editedAttendee The Attendee name to replace the {@code targetAttendee}.
     */
    public void updateAttendee(String targetAttendee, String editedAttendee) {
        internalList.forEach(meeting -> meeting.updateAttendee(targetAttendee, editedAttendee));
    }

    /**
     * Deletes the given attendee {@code targetAttendee} from all meetings.
     * @param targetAttendee The attendee name to be deleted.
     */
    public void deleteAttendee(String targetAttendee) {
        internalList.forEach(meeting -> meeting.deleteAttendee(targetAttendee));
    }

    /**
     * Removes the equivalent meeting from the list.
     * The meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
        FXCollections.sort(internalList, sortByTimeComparator);
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        FXCollections.sort(internalList, sortByTimeComparator);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateMeetingException();
        }

        internalList.setAll(meetings);
        FXCollections.sort(internalList, sortByTimeComparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueMeetingList)) {
            return false;
        }

        UniqueMeetingList otherUniqueMeetingList = (UniqueMeetingList) other;
        return internalList.equals(otherUniqueMeetingList.internalList);
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
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean meetingsAreUnique(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).isSameMeeting(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
