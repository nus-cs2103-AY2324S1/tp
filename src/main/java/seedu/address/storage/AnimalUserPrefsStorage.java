package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AnimalReadOnlyUserPrefs;
import seedu.address.model.AnimalUserPrefs;

/**
 * Responsible for managing the persistence of {@link AnimalUserPrefs}
 */
public interface AnimalUserPrefsStorage {
    /**
     * Returns the file path of the AnimalUserPrefs data file.
     */
    Path getAnimalUserPrefsFilePath();

    /**
     * Returns AnimalUserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    Optional<AnimalUserPrefs> readAnimalUserPrefs() throws DataLoadingException;

    /**
     * Returns AnimalUserPrefs data from the specified storage file.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param prefsFilePath the storage file to read from.
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    Optional<AnimalUserPrefs> readAnimalUserPrefs(Path prefsFilePath) throws DataLoadingException;

    /**
     * Saves the given {@link AnimalReadOnlyUserPrefs} to the storage.
     *
     * @param animalUserPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAnimalUserPrefs(AnimalReadOnlyUserPrefs animalUserPrefs) throws IOException;
}
