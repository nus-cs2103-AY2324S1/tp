package seedu.address.storage.events;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyEventsBook;

/**
 * Represents a storage for {@link seedu.address.model.EventsBook}.
 */
public interface EventsBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEventsBookFilePath();

    /**
     * Returns EventsBook data as a {@link seedu.address.model.ReadOnlyEventsBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyEventsBook> readEventsBook() throws DataLoadingException;

    /**
     * @see #getEventsBookFilePath() ()
     */
    Optional<ReadOnlyEventsBook> readEventsBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyEventsBook} to the storage.
     * @param eventsBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEventsBook(ReadOnlyEventsBook eventsBook) throws IOException;

    /**
     * @see #saveEventsBook(ReadOnlyEventsBook)
     */
    void saveEventsBook(ReadOnlyEventsBook eventsBook, Path filePath) throws IOException;

}
