package seedu.address.storage.finance;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyFinancesBook;

/**
 * Represents a storage for {@link seedu.address.model.FinancesBook}.
 */
public interface FinancesStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getFinancesBookFilePath();

    /**
     * Returns EventsBook data as a {@link seedu.address.model.ReadOnlyFinancesBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyFinancesBook> readFinancesBook() throws DataLoadingException;

    /**
     * @see #getFinancesBookFilePath() ()
     */
    Optional<ReadOnlyFinancesBook> readFinancesBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyFinancesBook} to the storage.
     * @param financesBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFinancesBook(ReadOnlyFinancesBook financesBook) throws IOException;

    /**
     * @see #saveFinancesBook(ReadOnlyFinancesBook)
     */
    void saveFinancesBook(ReadOnlyFinancesBook financesBook, Path filePath) throws IOException;
}
