package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.booking.Booking;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Booking> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getBookingBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setBookingsBook(ReadOnlyBookingsBook bookingsBook);

    /** Returns the AddressBook */
    ReadOnlyBookingsBook getBookingsBook();

    /**
     * Returns true if a booking with the same identity as {@code booking} exists in the address book.
     */
    boolean hasBooking(Booking booking);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteBooking(Booking target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addBooking(Booking booking);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setBooking(Booking target, Booking editedBooking);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Booking> getFilteredBookingList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookingList(Predicate<Booking> predicate);

    void addToDeletedBookings(List<Booking> booking);
    List<Booking> undoDeletion();
}
