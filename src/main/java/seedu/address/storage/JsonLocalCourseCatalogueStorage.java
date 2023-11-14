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
import seedu.address.model.ReadOnlyLocalCourseCatalogue;

/**
 * A class to access LocalCourseCatalogue data stored as a json file on the hard disk.
 */
public class JsonLocalCourseCatalogueStorage implements LocalCourseCatalogueStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLocalCourseCatalogueStorage.class);

    // Default filePath from root if filePath is not passed into method
    private Path filePath;

    public JsonLocalCourseCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getLocalCourseCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue() throws DataLoadingException {
        return readLocalCourseCatalogue(filePath);
    }

    /**
     * Similar to {@link #readLocalCourseCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableLocalCourseCatalogue> jsonLocalCourseCatalogue =
                JsonUtil.readJsonFile(filePath, JsonSerializableLocalCourseCatalogue.class);
        if (!jsonLocalCourseCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLocalCourseCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue) throws IOException {
        saveLocalCourseCatalogue(localCourseCatalogue, filePath);
    }

    /**
     * Similar to {@link #saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue,
                                         Path filePath) throws IOException {
        requireNonNull(localCourseCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLocalCourseCatalogue(localCourseCatalogue), filePath);
    }

}

