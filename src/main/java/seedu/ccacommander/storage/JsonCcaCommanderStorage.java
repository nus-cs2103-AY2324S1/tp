package seedu.ccacommander.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ccacommander.commons.core.LogsCenter;
import seedu.ccacommander.commons.exceptions.DataLoadingException;
import seedu.ccacommander.commons.exceptions.IllegalValueException;
import seedu.ccacommander.commons.util.FileUtil;
import seedu.ccacommander.commons.util.JsonUtil;
import seedu.ccacommander.model.ReadOnlyCcaCommander;

/**
 * A class to access CcaCommander data stored as a json file on the hard disk.
 */
public class JsonCcaCommanderStorage implements CcaCommanderStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCcaCommanderStorage.class);

    private Path filePath;

    public JsonCcaCommanderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCcaCommander> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyCcaCommander> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
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
    public void saveAddressBook(ReadOnlyCcaCommander addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyCcaCommander)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyCcaCommander addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

}
