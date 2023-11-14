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
import seedu.address.model.ReadOnlyUniversityCatalogue;

/**
 * A class to access UniversityCatalogue data stored as a json file on the hard disk.
 */
public class JsonUniversityCatalogueStorage implements UniversityCatalogueStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniversityCatalogueStorage.class);

    private Path filePath;

    public JsonUniversityCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUniversityCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue() throws DataLoadingException {
        return readUniversityCatalogue(filePath);
    }

    /**
     * Similar to {@link #readUniversityCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableUniversityCatalogue> jsonUniversityCatalogue =
                JsonUtil.readJsonFile(filePath, JsonSerializableUniversityCatalogue.class);
        if (!jsonUniversityCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUniversityCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue) throws IOException {
        saveUniversityCatalogue(universityCatalogue, filePath);
    }

    /**
     * Similar to {@link #saveUniversityCatalogue(ReadOnlyUniversityCatalogue, Path)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue,
                                         Path filePath) throws IOException {
        requireNonNull(universityCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUniversityCatalogue(universityCatalogue), filePath);
    }

}
