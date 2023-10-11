package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.BookingsBook;
import seedu.address.model.ReadOnlyBookingsBook;

/**
 * Represents a storage for {@link BookingsBook}.
 */
public interface BookingBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBookingBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyBookingsBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyBookingsBook> readBookingBook() throws DataLoadingException;

    /**
     * @see #getBookingBookFilePath()
     */
    Optional<ReadOnlyBookingsBook> readBookingBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyBookingsBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBookingBook(ReadOnlyBookingsBook addressBook) throws IOException;

    /**
     * @see #saveBookingBook(ReadOnlyBookingsBook)
     */
    void saveBookingBook(ReadOnlyBookingsBook addressBook, Path filePath) throws IOException;

}
