package transact.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyTransactionBook;

/**
 * Represents a storage for transaction book.
 */
public interface TransactionBookStorage {

    /**
     * Returns the file path of the transaction log data file.
     */
    Path getTransactionBookFilePath();

    /**
     * Returns transaction log data as a {@link ReadOnlyTransactionBook}.
     * Returns {@code Optional.empty()} if the storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyTransactionBook> readTransactionBook() throws DataLoadingException;

    /**
     * @see #getTransactionBookFilePath()
     */
    Optional<ReadOnlyTransactionBook> readTransactionBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyTransactionBook} to the storage.
     *
     * @param transactionBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTransactionBook(ReadOnlyTransactionBook transactionBook) throws IOException;

    /**
     * @see #saveTransactionBook(ReadOnlyTransactionBook)
     */
    void saveTransactionBook(ReadOnlyTransactionBook transactionBook, Path filePath) throws IOException;
}

