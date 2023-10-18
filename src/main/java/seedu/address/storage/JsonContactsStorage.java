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
 * A class to access ConText data stored as a json file on the hard disk.
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
    public Optional<ContactList> readConText() throws DataLoadingException {
        return readConText(filePath);
    }

    /**
     * Similar to {@link #readConText()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ContactList> readConText(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableConText> jsonConText = JsonUtil.readJsonFile(
                filePath, JsonSerializableConText.class);
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
    public void saveConText(ContactList contactList) throws IOException {
        saveConText(contactList, filePath);
    }

    /**
     * Similar to {@link #saveConText(ContactList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveConText(ContactList contactList, Path filePath) throws IOException {
        requireNonNull(contactList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableConText(contactList), filePath);
    }

}
