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
import seedu.address.model.ReadOnlyMappingCatalogue;

/**
 * A class to access MappingCatalogue data stored as a json file on the hard disk.
 */
public class JsonMappingCatalogueStorage implements MappingCatalogueStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMappingCatalogueStorage.class);

    // Default filePath from root if filePath is not passed into method
    private Path filePath;

    public JsonMappingCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getMappingCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMappingCatalogue> readMappingCatalogue() throws DataLoadingException {
        return readMappingCatalogue(filePath);
    }

    /**
     * Similar to {@link #readMappingCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyMappingCatalogue> readMappingCatalogue(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableMappingCatalogue> jsonMappingCatalogue =
                JsonUtil.readJsonFile(filePath, JsonSerializableMappingCatalogue.class);
        if (!jsonMappingCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMappingCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue) throws IOException {
        saveMappingCatalogue(mappingCatalogue, filePath);
    }

    /**
     * Similar to {@link #saveMappingCatalogue(ReadOnlyMappingCatalogue)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue,
                                     Path filePath) throws IOException {
        requireNonNull(mappingCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMappingCatalogue(mappingCatalogue), filePath);
    }

}

