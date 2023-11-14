package seedu.address.storage.finance;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyFinancesBook;

/**
 * A class to access Finance data stored as a json file on the hard disk.
 */
public class JsonFinanceStorage implements FinancesStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonFinanceStorage.class);

    private Path filePath;

    public JsonFinanceStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getFinancesBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFinancesBook> readFinancesBook() throws DataLoadingException {
        return readFinancesBook(filePath);
    }

    /**
     * Similar to {@link #readFinancesBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyFinancesBook> readFinancesBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableFinancesBook> jsonFinancesBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableFinancesBook.class);
        if (jsonFinancesBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFinancesBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveFinancesBook(ReadOnlyFinancesBook financesBook) throws IOException {
        saveFinancesBook(financesBook, filePath);
    }

    /**
     * Similar to {@link #saveFinancesBook(ReadOnlyFinancesBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveFinancesBook(ReadOnlyFinancesBook financesBook, Path filePath) throws IOException {
        requireNonNull(financesBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFinancesBook(financesBook), filePath);
    }
}
