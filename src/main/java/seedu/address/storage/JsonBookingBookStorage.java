package seedu.address.storage;

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
import seedu.address.model.ReadOnlyBookingsBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonBookingBookStorage implements BookingBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBookingBookStorage.class);

    private Path filePath;

    public JsonBookingBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBookingBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBookingsBook> readBookingBook() throws DataLoadingException {
        return readBookingBook(filePath);
    }

    /**
     * Similar to {@link #readBookingBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyBookingsBook> readBookingBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableBookingBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBookingBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveBookingBook(ReadOnlyBookingsBook addressBook) throws IOException {
        saveBookingBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveBookingBook(ReadOnlyBookingsBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBookingBook(ReadOnlyBookingsBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookingBook(addressBook), filePath);
    }

}
