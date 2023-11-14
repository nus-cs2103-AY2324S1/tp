package seedu.address.model.venue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.venue.exceptions.DuplicateVenueException;
import seedu.address.model.venue.exceptions.VenueNotFoundException;

/**
 * A list of venues that enforces uniqueness between its elements and does not allow nulls.
 * A venue is considered unique by comparing using {@code Venue#isSameVenue(Venue)}. As such, adding and updating of
 * venues uses Venue#isSameVenue(Venue) for equality so as to ensure that the venue being added or updated is
 * unique in terms of identity in the UniqueVenueList. However, the removal of a venue uses Venue#equals(Object) so
 * as to ensure that the venue with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Venue#isSameVenue(Venue)
 */
public class UniqueVenueList implements Iterable<Venue> {

    private final ObservableList<Venue> internalList = FXCollections.observableArrayList();
    private final ObservableList<Venue> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent venue as the given argument.
     */
    public boolean contains(Venue toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameVenue);
    }

    /**
     * Adds a venue to the list.
     * The venue must not already exist in the list.
     */
    public void add(Venue toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateVenueException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the venue {@code target} in the list with {@code editedVenue}.
     * {@code target} must exist in the list.
     * The venue identity of {@code editedVenue} must not be the same as another existing venue in the list.
     */
    public void setVenue(Venue target, Venue editedVenue) {
        requireAllNonNull(target, editedVenue);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new VenueNotFoundException();
        }

        if (!target.isSameVenue(editedVenue) && contains(editedVenue)) {
            throw new DuplicateVenueException();
        }

        internalList.set(index, editedVenue);
    }

    /**
     * Removes the equivalent venue from the list.
     * The venue must exist in the list.
     */
    public void remove(Venue toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new VenueNotFoundException();
        }
    }

    public void setVenues(UniqueVenueList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code venues}.
     * {@code venues} must not contain duplicate venues.
     */
    public void setVenues(List<Venue> venues) {
        requireAllNonNull(venues);
        if (!venuesAreUnique(venues)) {
            throw new DuplicateVenueException();
        }

        internalList.setAll(venues);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Venue> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Venue> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueVenueList)) {
            return false;
        }

        UniqueVenueList otherUniqueVenueList = (UniqueVenueList) other;
        return internalList.equals(otherUniqueVenueList.internalList);
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
     * Returns true if {@code venues} contains only unique venues.
     */
    private boolean venuesAreUnique(List<Venue> venues) {
        for (int i = 0; i < venues.size() - 1; i++) {
            for (int j = i + 1; j < venues.size(); j++) {
                if (venues.get(i).isSameVenue(venues.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
