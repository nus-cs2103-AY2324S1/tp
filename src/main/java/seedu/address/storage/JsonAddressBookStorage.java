package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    /**
     * Constructs a new JsonAddressBookStorage instance with a given filePath.
     *
     * @param filePath The path to the file.
     */
    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path where the AddressBook is stored.
     *
     * @return The file path.
     */
    public Path getAddressBookFilePath() {
        return filePath;
    }

    /**
     * Reads the AddressBook from the default file path.
     *
     * @return An Optional containing the AddressBook if it exists, or empty
     *         otherwise.
     * @throws DataLoadingException if there was an error reading the AddressBook.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Reads the AddressBook from the specified file path.
     *
     * @param filePath The path to the file.
     * @return An Optional containing the AddressBook if it exists, or empty
     *         otherwise.
     * @throws DataLoadingException if there was an error reading the AddressBook.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        if (!FileUtil.isFileExists(filePath)) {
            return Optional.empty();
        }

        try {
            String data = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

            JsonSerializableAddressBook jsonAddressBook = JsonUtil.fromJsonString(
                    data, JsonSerializableAddressBook.class);

            if (jsonAddressBook == null) {
                return Optional.empty();
            }

            return Optional.of(jsonAddressBook.toModelType());

        } catch (Exception e) {
            throw new DataLoadingException(e);
        }
    }

    /**
     * Saves the AddressBook to the default file path.
     *
     * @param addressBook The AddressBook to be saved.
     * @throws IOException if there was an error writing to the file.
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Saves the AddressBook to the specified file path.
     *
     * @param addressBook The AddressBook to be saved.
     * @param filePath    The path to the file.
     * @throws IOException if there was an error writing to the file.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        String jsonData = JsonUtil.toJsonString(new JsonSerializableAddressBook(addressBook));

        Files.write(filePath, jsonData.getBytes(StandardCharsets.UTF_8));
    }

}
