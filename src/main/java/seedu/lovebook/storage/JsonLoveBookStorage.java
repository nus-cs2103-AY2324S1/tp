package seedu.lovebook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.commons.util.FileUtil;
import seedu.lovebook.commons.util.JsonUtil;
import seedu.lovebook.model.ReadOnlyLoveBook;

/**
 * A class to access LoveBook data stored as a json file on the hard disk.
 */
public class JsonLoveBookStorage implements LoveBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLoveBookStorage.class);

    private Path filePath;

    public JsonLoveBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLoveBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLoveBook> readLoveBook() throws DataLoadingException {
        return readLoveBook(filePath);
    }

    /**
     * Similar to {@link #readLoveBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyLoveBook> readLoveBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableLoveBook> jsonLoveBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableLoveBook.class);
        if (!jsonLoveBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLoveBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveLoveBook(ReadOnlyLoveBook loveBook) throws IOException {
        saveLoveBook(loveBook, filePath);
    }

    /**
     * Similar to {@link #saveLoveBook(ReadOnlyLoveBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLoveBook(ReadOnlyLoveBook loveBook, Path filePath) throws IOException {
        requireNonNull(loveBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLoveBook(loveBook), filePath);
    }

}
