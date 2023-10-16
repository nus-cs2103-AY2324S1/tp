package seedu.lovebook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.commons.util.FileUtil;
import seedu.lovebook.commons.util.JsonUtil;
import seedu.lovebook.model.ReadOnlyDatePrefs;

/**
 * A class to access DatePrefs data stored as a json file on the hard disk.
 */
public class JsonLoveBookDatePrefs implements DatePrefsStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonLoveBookDatePrefs.class);

    private Path filePath;

    public JsonLoveBookDatePrefs(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDatePrefsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDatePrefs> readDatePrefs() throws DataLoadingException {
        return readDatePrefs(filePath);
    }

    /**
     * Similar to {@link #readDatePrefs()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyDatePrefs> readDatePrefs(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableDatePrefs> jsonDatePrefs = JsonUtil.readJsonFile(
                filePath, JsonSerializableDatePrefs.class);
        if (!jsonDatePrefs.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDatePrefs.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveDatePrefs(ReadOnlyDatePrefs prefs) throws IOException {
        saveDatePrefs(prefs, filePath);
    }

    /**
     * Similar to {@link #saveDatePrefs(ReadOnlyDatePrefs, Path)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDatePrefs(ReadOnlyDatePrefs prefs, Path filePath) throws IOException {
        requireNonNull(prefs);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDatePrefs(prefs), filePath);
    }
}
