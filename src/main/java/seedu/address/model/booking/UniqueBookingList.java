package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.exceptions.BookingNotFoundException;
import seedu.address.model.booking.exceptions.DuplicateBookingException;
import seedu.address.model.booking.exceptions.UniqueBookingListNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object)
 * * to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Booking#isSameBooking(Booking)
 */
public class UniqueBookingList implements Iterable<Booking> {

    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent booking as the given argument.
     *
     * @param toCheck The booking to check.
     * @return True if the booking is found in the list, false otherwise.
     * @throws BookingNotFoundException if the provided booking is null.
     */
    public boolean contains(Booking toCheck) {
        if (toCheck == null) {
            throw new BookingNotFoundException();
        }
        return internalList.stream().anyMatch(toCheck::isSameBooking);
    }

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list.
     *
     * @param toAdd The booking to add.
     * @throws BookingNotFoundException if the provided booking is null.
     * @throws DuplicateBookingException if the booking already exists in the list.
     */
    public void add(Booking toAdd) {
        if (toAdd == null) {
            throw new BookingNotFoundException();
        }
        if (contains(toAdd)) {
            throw new DuplicateBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in the list, and both the target and edited booking must not be null.
     * The booking identity of {@code editedBooking} must not be the same as another existing booking in the list.
     *
     * @param target The original booking to be replaced.
     * @param editedBooking The new booking to replace the original booking.
     * @throws BookingNotFoundException if the provided target or edited booking is null, or if the target booking is
     *                                  not found in the list.
     * @throws DuplicateBookingException if the edited booking already exists in the list with the same identity as
     *                                   another booking.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        if (target == null || editedBooking == null) {
            throw new BookingNotFoundException();
        }

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
     * Removes the equivalent booking from the list.
     * The booking must not be null, and it must exist in the list.
     *
     * @param toRemove The booking to be removed from the list.
     * @throws BookingNotFoundException if the provided booking is null, or if the booking is not found in the list.
     */
    public void remove(Booking toRemove) {
        if (toRemove == null) {
            throw new BookingNotFoundException();
        }
        if (!internalList.remove(toRemove)) {
            throw new BookingNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with the bookings from the provided {@code UniqueBookingList} replacement.
     * The replacement list must not be null.
     *
     * @param replacement The UniqueBookingList containing the bookings to replace the current list.
     * @throws UniqueBookingListNotFoundException if the provided replacement is null.
     */
    public void setBookings(UniqueBookingList replacement) {
        if (replacement == null) {
            throw new UniqueBookingListNotFoundException();
        }
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with the provided list of bookings.
     * The provided list must not contain duplicate bookings.
     *
     * @param bookings The list of bookings to set in the internal list.
     * @throws NullPointerException if the provided list of bookings is null.
     * @throws DuplicateBookingException if the list contains duplicate bookings.
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
     *
     * @return An unmodifiable view of the internal list.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns an iterator over the bookings in this list.
     *
     * @return An iterator over the bookings in this list.
     */
    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    /**
     * Checks if this UniqueBookingList is equal to another object.
     * Two UniqueBookingList objects are considered equal if they have the same internal list.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Returns the hash code for this UniqueBookingList.
     *
     * @return The hash code for this UniqueBookingList.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if the provided list of bookings contains only unique bookings.
     *
     * @param bookings The list of bookings to check for uniqueness.
     * @return True if the list contains only unique bookings, false otherwise.
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
