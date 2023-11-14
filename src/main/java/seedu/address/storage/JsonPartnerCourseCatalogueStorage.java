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
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;

/**
 * A class to access PartnerCourseCatalogue data stored as a json file on the hard disk.
 */
public class JsonPartnerCourseCatalogueStorage implements PartnerCourseCatalogueStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPartnerCourseCatalogueStorage.class);

    private Path filePath;

    public JsonPartnerCourseCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getPartnerCourseCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue() throws DataLoadingException {
        return readPartnerCourseCatalogue(filePath);
    }

    /**
     * Similar to {@link #readPartnerCourseCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue(Path filePath)
            throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializablePartnerCourseCatalogue> jsonPartnerCourseCatalogue =
                JsonUtil.readJsonFile(filePath, JsonSerializablePartnerCourseCatalogue.class);
        if (!jsonPartnerCourseCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPartnerCourseCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue) throws IOException {
        savePartnerCourseCatalogue(partnerCourseCatalogue, filePath);
    }

    /**
     * Similar to {@link #savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue,
                                         Path filePath) throws IOException {
        requireNonNull(partnerCourseCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePartnerCourseCatalogue(partnerCourseCatalogue), filePath);
    }
}
