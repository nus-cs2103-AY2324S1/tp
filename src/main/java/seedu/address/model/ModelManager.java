package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final int UNDO_LIMIT = 5; // Set the limit for undo operations

    private final BookingsBook bookingsBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Booking> filteredBookings;
    private List<List<Booking>> deletedBookings = new ArrayList<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     *
     * @param addressBook ReadOnlyBookingsBook containing booking data.
     * @param userPrefs   ReadOnlyUserPrefs containing user preferences.
     */
    public ModelManager(ReadOnlyBookingsBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.bookingsBook = new BookingsBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBookings = new FilteredList<>(this.bookingsBook.getPersonList());

    }

    /**
     * Constructs a ModelManager with default BookingsBook and UserPrefs.
     */
    public ModelManager() {
        this(new BookingsBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    /**
     * Sets user preferences to the provided {@code ReadOnlyUserPrefs}.
     *
     * @param userPrefs ReadOnlyUserPrefs to be set.
     */
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    /**
     * Returns the user preferences.
     *
     * @return UserPrefs object containing user preferences.
     */
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    /**
     * Returns the GUI settings.
     *
     * @return GuiSettings object representing the GUI settings.
     */
    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getBookingBookFilePath() {
        return userPrefs.getBookingBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setBookingBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setBookingsBook(ReadOnlyBookingsBook bookingsBook) {
        this.bookingsBook.resetData(bookingsBook);
    }

    @Override
    public ReadOnlyBookingsBook getBookingsBook() {
        return bookingsBook;
    }

    @Override
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return bookingsBook.hasBooking(booking);
    }

    @Override
    public void deleteBooking(Booking target) {
        bookingsBook.removePerson(target);
    }

    @Override
    public void addBooking(Booking booking) {
        bookingsBook.addBooking(booking);
        updateFilteredBookingList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);

        bookingsBook.setBooking(target, editedBooking);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return filteredBookings;
    }

    @Override
    public void updateFilteredBookingList(Predicate<Booking> predicate) {
        requireNonNull(predicate);
        filteredBookings.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return bookingsBook.equals(otherModelManager.bookingsBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredBookings.equals(otherModelManager.filteredBookings);
    }

    @Override
    public void addToDeletedBookings(List<Booking> deleteList) {
        if (deletedBookings.size() >= UNDO_LIMIT) {
            deletedBookings.remove(0); // Remove the oldest entry if the limit is reached
        }
        deletedBookings.add(deleteList);
    }

    @Override
    public List<Booking> undoDeletion() {
        if (!deletedBookings.isEmpty()) {
            List<Booking> lastDeletion = deletedBookings.remove(deletedBookings.size() - 1);
            for (Booking booking : lastDeletion) {
                addBooking(booking); // Add deleted bookings back to the system
            }
            return lastDeletion;
        } else {
            return new ArrayList<>(); // Throw exception if there are no deletions to undo
        }
    }
}
