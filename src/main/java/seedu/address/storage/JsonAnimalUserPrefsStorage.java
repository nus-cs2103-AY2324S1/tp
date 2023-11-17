package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AnimalReadOnlyUserPrefs;
import seedu.address.model.AnimalUserPrefs;

/**
 * Represents a storage for {@link AnimalUserPrefs}. Responsible for managing the read/write of {@link AnimalUserPrefs}
 * to local storage.
 */
public class JsonAnimalUserPrefsStorage implements AnimalUserPrefsStorage {
    private final Path filePath;

    /**
     * Constructs a {@link JsonAnimalUserPrefsStorage} that will read/write to the given filePath.
     *
     * @param filePath the filePath to read from/write to.
     */
    public JsonAnimalUserPrefsStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the AnimalUserPrefs data file.
     */
    @Override
    public Path getAnimalUserPrefsFilePath() {
        return filePath;
    }

    /**
     * Returns AnimalUserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    @Override
    public Optional<AnimalUserPrefs> readAnimalUserPrefs() throws DataLoadingException {
        return readAnimalUserPrefs(filePath);
    }

    /**
     * Returns AnimalUserPrefs data from the specified storage file.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param prefsFilePath the storage file to read from.
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    @Override
    public Optional<AnimalUserPrefs> readAnimalUserPrefs(Path prefsFilePath) throws DataLoadingException {
        return JsonUtil.readJsonFile(prefsFilePath, AnimalUserPrefs.class);
    }

    /**
     * Saves the given {@link AnimalReadOnlyUserPrefs} to the storage.
     *
     * @param animalUserPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAnimalUserPrefs(AnimalReadOnlyUserPrefs animalUserPrefs) throws IOException {
        JsonUtil.saveJsonFile(animalUserPrefs, filePath);
    }
}
