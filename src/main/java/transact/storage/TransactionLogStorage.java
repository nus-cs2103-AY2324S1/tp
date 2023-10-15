package transact.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyTransactionLog;

/**
 * Represents a storage for transaction logs.
 */
public interface TransactionLogStorage {

    /**
     * Returns the file path of the transaction log data file.
     */
    Path getTransactionLogFilePath();

    /**
     * Returns transaction log data as a {@link ReadOnlyTransactionLog}.
     * Returns {@code Optional.empty()} if the storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyTransactionLog> readTransactionLog() throws DataLoadingException;

    /**
     * @see #getTransactionLogFilePath()
     */
    Optional<ReadOnlyTransactionLog> readTransactionLog(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyTransactionLog} to the storage.
     *
     * @param transactionLog cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTransactionLog(ReadOnlyTransactionLog transactionLog) throws IOException;

    /**
     * @see #saveTransactionLog(ReadOnlyTransactionLog)
     */
    void saveTransactionLog(ReadOnlyTransactionLog transactionLog, Path filePath) throws IOException;
}

