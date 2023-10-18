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
import seedu.address.model.ReadOnlyConText;

/**
 * A class to access ConText data stored as a json file on the hard disk.
 */
public class JsonConTextStorage implements ConTextStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonConTextStorage.class);

    private Path filePath;

    public JsonConTextStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getConTextFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyConText> readConText() throws DataLoadingException {
        return readConText(filePath);
    }

    /**
     * Similar to {@link #readConText()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyConText> readConText(Path filePath) throws DataLoadingException {
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
    public void saveConText(ReadOnlyConText conText) throws IOException {
        saveConText(conText, filePath);
    }

    /**
     * Similar to {@link #saveConText(ReadOnlyConText)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveConText(ReadOnlyConText conText, Path filePath) throws IOException {
        requireNonNull(conText);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableConText(conText), filePath);
    }

}
