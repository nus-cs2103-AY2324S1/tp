package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as json files on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    /**
     * Constructs a JsonAddressBookStorage.
     * @param filePath the file path.
     */
    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }
    public Path getFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath)
            throws DataLoadingException {
        requireNonNull(filePath);
        Optional<JsonSerializableAddressBook> jsonBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (jsonBook.isEmpty()) {
            return Optional.empty();
        }
        AddressBook addressBook;
        try {
            addressBook = jsonBook.get().toModelType();
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
        return Optional.of(addressBook);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the file data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireAllNonNull(addressBook, filePath);
        FileUtil.createIfMissing(filePath);

        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }
}
