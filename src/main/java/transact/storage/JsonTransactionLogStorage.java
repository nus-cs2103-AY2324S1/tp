package transact.storage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import transact.commons.core.LogsCenter;
import transact.commons.exceptions.DataLoadingException;
import transact.commons.exceptions.IllegalValueException;
import transact.commons.util.FileUtil;
import transact.commons.util.JsonUtil;
import transact.model.ReadOnlyTransactionLog;

import static java.util.Objects.requireNonNull;

/**
 * A class to access TransactionLog data stored as a json file on the hard disk.
 */
public class JsonTransactionLogStorage implements TransactionLogStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTransactionLogStorage.class);

    private Path filePath;

    public JsonTransactionLogStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTransactionLogFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTransactionLog> readTransactionLog() throws DataLoadingException {
        return readTransactionLog(filePath);
    }

    /**
     * Similar to {@link #readTransactionLog()}.
     *
     * @param filePath
     *            location of the data. Cannot be null.
     * @throws DataLoadingException
     *             if loading the data from storage failed.
     */
    public Optional<ReadOnlyTransactionLog> readTransactionLog(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableTransactionLog> jsonTransactionLog = JsonUtil.readJsonFile(
                filePath, JsonSerializableTransactionLog.class);
        if (!jsonTransactionLog.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTransactionLog.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveTransactionLog(ReadOnlyTransactionLog transactionLog) throws IOException {
        saveTransactionLog(transactionLog, filePath);
    }

    /**
     * Similar to {@link #saveTransactionLog(ReadOnlyTransactionLog)}.
     *
     * @param filePath
     *            location of the data. Cannot be null.
     */
    public void saveTransactionLog(ReadOnlyTransactionLog transactionLog, Path filePath) throws IOException {
        requireNonNull(transactionLog);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTransactionLog(transactionLog), filePath);
    }
}

