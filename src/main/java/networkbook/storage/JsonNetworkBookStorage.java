package networkbook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import networkbook.commons.core.LogsCenter;
import networkbook.commons.exceptions.DataLoadingException;
import networkbook.commons.exceptions.IllegalValueException;
import networkbook.commons.util.FileUtil;
import networkbook.commons.util.JsonUtil;
import networkbook.model.ReadOnlyNetworkBook;

/**
 * A class to access NetworkBook data stored as a json file on the hard disk.
 */
public class JsonNetworkBookStorage implements NetworkBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNetworkBookStorage.class);

    private Path filePath;

    public JsonNetworkBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNetworkBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNetworkBook> readNetworkBook() throws DataLoadingException {
        return readNetworkBook(filePath);
    }

    /**
     * Similar to {@link #readNetworkBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyNetworkBook> readNetworkBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableNetworkBook> jsonNetworkBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableNetworkBook.class);
        if (!jsonNetworkBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNetworkBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveNetworkBook(ReadOnlyNetworkBook networkBook) throws IOException {
        saveNetworkBook(networkBook, filePath);
    }

    /**
     * Similar to {@link #saveNetworkBook(ReadOnlyNetworkBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNetworkBook(ReadOnlyNetworkBook networkBook, Path filePath) throws IOException {
        requireNonNull(networkBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNetworkBook(networkBook), filePath);
    }

}
