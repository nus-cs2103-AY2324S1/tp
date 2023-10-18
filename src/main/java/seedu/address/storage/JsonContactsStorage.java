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
import seedu.address.model.ContactList;

/**
 * A class to access ContactsManager data stored as a json file on the hard disk.
 */
public class JsonContactsStorage implements ContactsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonContactsStorage.class);

    private Path filePath;

    public JsonContactsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getConTextFilePath() {
        return filePath;
    }

    @Override
    public Optional<ContactList> readContactsManager() throws DataLoadingException {
        return readContactsManager(filePath);
    }

    /**
     * Similar to {@link #readContactsManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ContactList> readContactsManager(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableContacts> jsonConText = JsonUtil.readJsonFile(
                filePath, JsonSerializableContacts.class);
        if (!jsonConText.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonConText.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveContactsManager(ContactList contactList) throws IOException {
        saveContactsManager(contactList, filePath);
    }

    /**
     * Similar to {@link #saveContactsManager(ContactList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveContactsManager(ContactList contactList, Path filePath) throws IOException {
        requireNonNull(contactList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableContacts(contactList), filePath);
    }

}
