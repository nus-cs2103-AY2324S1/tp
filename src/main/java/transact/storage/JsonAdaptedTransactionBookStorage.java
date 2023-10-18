package transact.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import transact.commons.core.LogsCenter;
import transact.commons.exceptions.DataLoadingException;
import transact.commons.exceptions.IllegalValueException;
import transact.commons.util.FileUtil;
import transact.commons.util.JsonUtil;
import transact.model.ReadOnlyTransactionBook;

/**
 * A class to access TransactionBook data stored as a json file on the hard disk.
 */
public class JsonAdaptedTransactionBookStorage implements TransactionBookStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedTransactionBookStorage.class);

    private Path filePath;

    public JsonAdaptedTransactionBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTransactionBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTransactionBook> readTransactionBook() throws DataLoadingException {
        return readTransactionBook(filePath);
    }

    /**
     * Similar to {@link #readTransactionBook()}.
     *
     * @param filePath
     *            location of the data. Cannot be null.
     * @throws DataLoadingException
     *             if loading the data from storage failed.
     */
    public Optional<ReadOnlyTransactionBook> readTransactionBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableTransactionBook> jsonTransactionBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTransactionBook.class);
        if (!jsonTransactionBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTransactionBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveTransactionBook(ReadOnlyTransactionBook transactionBook) throws IOException {
        saveTransactionBook(transactionBook, filePath);
    }

    /**
     * Similar to {@link #saveTransactionBook(ReadOnlyTransactionBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTransactionBook(ReadOnlyTransactionBook transactionBook, Path filePath) throws IOException {
        requireNonNull(transactionBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTransactionBook(transactionBook), filePath);
    }
}
