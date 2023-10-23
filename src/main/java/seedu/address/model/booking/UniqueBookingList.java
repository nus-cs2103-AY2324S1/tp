package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.exceptions.DuplicateBookingException;
import seedu.address.model.booking.exceptions.BookingNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Booking#isSameBooking(Booking)
 */
public class UniqueBookingList implements Iterable<Booking> {

    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBooking);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        if (!target.isSameBooking(editedBooking) && contains(editedBooking)) {
            throw new DuplicateBookingException();
        }

        internalList.set(index, editedBooking);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Booking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookingNotFoundException();
        }
    }

    public void setBookings(UniqueBookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreUnique(bookings)) {
            throw new DuplicateBookingException();
        }

        internalList.setAll(bookings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueBookingList)) {
            return false;
        }

        UniqueBookingList otherUniqueBookingList = (UniqueBookingList) other;
        return internalList.equals(otherUniqueBookingList.internalList);
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
    private boolean bookingsAreUnique(List<Booking> bookings) {
        for (int i = 0; i < bookings.size() - 1; i++) {
            for (int j = i + 1; j < bookings.size(); j++) {
                if (bookings.get(i).isSameBooking(bookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
