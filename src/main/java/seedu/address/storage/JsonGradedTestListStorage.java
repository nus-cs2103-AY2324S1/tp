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
import seedu.address.model.ReadOnlyGradedTestList;

/**
 * A class to access GradedTestList data stored as a json file on the hard disk.
 */
public class JsonGradedTestListStorage implements GradedTestListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonGradedTestListStorage.class);

    private Path filePath;

    public JsonGradedTestListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGradedTestListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGradedTestList> readGradedTestList() throws DataLoadingException {
        return readGradedTestList(filePath);
    }

    /**
     * Similar to {@link #readGradedTestList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyGradedTestList> readGradedTestList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableGradedTestList> jsonGradedTestList = JsonUtil.readJsonFile(
                filePath, JsonSerializableGradedTestList.class);
        if (!jsonGradedTestList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGradedTestList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveGradedTestList(ReadOnlyGradedTestList gradedTestList) throws IOException {
        saveGradedTestList(gradedTestList, filePath);
    }

    /**
     * Similar to {@link #saveGradedTestList(ReadOnlyGradedTestList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGradedTestList(ReadOnlyGradedTestList gradedTestList, Path filePath) throws IOException {
        requireNonNull(gradedTestList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGradedTestList(gradedTestList), filePath);
    }
}
