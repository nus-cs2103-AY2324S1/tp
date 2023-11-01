package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBookManager;
import seedu.address.model.ReadOnlyAddressBookManager;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<AddressBookManager> readAddressBooks() throws DataLoadingException {
        return readAddressBooks(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<AddressBookManager> readAddressBooks(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<AddressBookManager> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, AddressBookManager.class);

        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        return jsonAddressBook;
    }

    @Override
    public void saveAddressBooks(ReadOnlyAddressBookManager addressBookManager) throws IOException {
        saveAddressBooks(addressBookManager, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(AddressBookManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBooks(ReadOnlyAddressBookManager addressBookManager, Path filePath) throws IOException {
        requireNonNull(addressBookManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(addressBookManager, filePath);
    }

}
