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
import seedu.address.model.ReadOnlyNoteCatalogue;

/**
 * A class to access NoteCatalogue data stored as a json file on the hard disk.
 */
public class JsonNoteCatalogueStorage implements NoteCatalogueStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonNoteCatalogueStorage.class);

    private Path filePath;

    public JsonNoteCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getNoteCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNoteCatalogue> readNoteCatalogue() throws DataLoadingException {
        return readNoteCatalogue(filePath);
    }

    /**
     * Similar to {@link #readNoteCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyNoteCatalogue> readNoteCatalogue(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableNoteCatalogue> jsonNoteCatalogue =
                JsonUtil.readJsonFile(filePath, JsonSerializableNoteCatalogue.class);
        if (!jsonNoteCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNoteCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue) throws IOException {
        saveNoteCatalogue(noteCatalogue, filePath);
    }

    /**
     * Similar to {@link #saveNoteCatalogue(ReadOnlyNoteCatalogue)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue, Path filePath) throws IOException {
        requireNonNull(noteCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNoteCatalogue(noteCatalogue), filePath);
    }
}
