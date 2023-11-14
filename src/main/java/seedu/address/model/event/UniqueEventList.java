package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.event.exceptions.EventOverlapException;

/**
 * A list of events that enforces uniqueness between its elements, does not allow overlaps and does not allow nulls.
 * An event can be added only if it does not already exist in the list,
 * and it does not overlap with all currently existed events by time
 * Supports a minimal set of list operations.
 *
 */
public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an event to the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateEventException();
        }
        Event overlappingEvent = this.getOverlappingEvent(toAdd);
        if (overlappingEvent != null) {
            throw new EventOverlapException(toAdd, overlappingEvent);
        }
        internalList.add(toAdd);
    }


    /**
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code events}.
     * {@code persons} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }

        internalList.setAll(events);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueEventList)) {
            return false;
        }

        UniqueEventList otherUniquePersonList = (UniqueEventList) other;
        return internalList.equals(otherUniquePersonList.internalList);
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
     * Returns true if {@code events} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).equals(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return an event in the list that is overlapping with {@code newEvent}.
     * If no overlapping event is found, return {@code null}
     * {@code newEvent} must not exists in the list.
     *
     * @param newEvent The event for checking overlapping
     * @return The overlapping event or {@code null}.
     */
    private Event getOverlappingEvent(Event newEvent) {
        requireNonNull(newEvent);
        assert !this.contains(newEvent);
        LocalDateTime newEventStartTime = newEvent.getStartTime();
        LocalDateTime newEventEndTime = newEvent.getEndTime();

        assert newEventStartTime != null : "Start time should not be null";
        assert newEventEndTime != null : "End time should not be null";

        for (Event e : this.internalList) {
            LocalDateTime startTime = e.getStartTime();
            LocalDateTime endTime = e.getEndTime();

            assert startTime != null : "Start time should not be null";
            assert endTime != null : "End time should not be null";

            if (DateTimeUtil.timeIntervalsOverlap(newEventStartTime, newEventEndTime, startTime, endTime)) {
                return e;
            }
        }
        return null;
    }
}
