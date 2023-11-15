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
import seedu.address.model.user.ReadOnlyUserData;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonUserDataStorage implements UserDataStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUserDataStorage.class);

    private Path filePath;

    public JsonUserDataStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUserDataFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUserData> readUserData() throws DataLoadingException {
        return readUserData(filePath);
    }

    /**
     * Similar to {@link #readUserData()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyUserData> readUserData(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableUserData> jsonUserData = JsonUtil.readJsonFile(
                filePath, JsonSerializableUserData.class);
        if (!jsonUserData.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUserData.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveUserData(ReadOnlyUserData userData) throws IOException {
        saveUserData(userData, filePath);
    }

    /**
     * Similar to {@link #saveUserData(ReadOnlyUserData)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUserData(ReadOnlyUserData userData, Path filePath) throws IOException {
        requireNonNull(userData);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUserData(userData), filePath);
    }

}

