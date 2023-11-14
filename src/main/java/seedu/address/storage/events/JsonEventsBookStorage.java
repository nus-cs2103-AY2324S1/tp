package seedu.address.storage.events;

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
import seedu.address.model.ReadOnlyEventsBook;

/**
 * A class to access Events data stored as a json file on the hard disk.
 */
public class JsonEventsBookStorage implements EventsBookStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonEventsBookStorage.class);

    private Path filePath;

    public JsonEventsBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEventsBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEventsBook> readEventsBook() throws DataLoadingException {
        return readEventsBook(filePath);
    }

    /**
     * Similar to {@link #readEventsBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyEventsBook> readEventsBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableEventsBook> jsonEventsBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableEventsBook.class);
        if (jsonEventsBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEventsBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveEventsBook(ReadOnlyEventsBook eventsBook) throws IOException {
        saveEventsBook(eventsBook, filePath);
    }

    /**
     * Similar to {@link #saveEventsBook(ReadOnlyEventsBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveEventsBook(ReadOnlyEventsBook eventsBook, Path filePath) throws IOException {
        requireNonNull(eventsBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEventsBook(eventsBook), filePath);
    }
}
