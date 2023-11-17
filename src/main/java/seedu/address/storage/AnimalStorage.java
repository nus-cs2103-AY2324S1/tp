package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AnimalReadOnlyUserPrefs;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.ReadOnlyAnimalCatalog;

/**
 * The AnimalStorage interface defines the operations that manage the reading from and writing to
 * different storage files, acting as a facade that unifies and coordinates the storage interaction.
 *
 * This interface integrates various storage responsibilities ({@link AnimalCatalogStorage},
 * {@link AnimalUserPrefsStorage}), exposing methods to handle user preferences, and animal catalog data persistence
 * with clear segregation for ease of understanding, and which also improves separation of concern for increased
 * maintainability, and looser coupling.
 *
 * NOTE: Technically there is no need to override the methods in this interface. Overriding the methods here is done
 * solely for readability by providing a unified view of all the inherited methods.
 */
public interface AnimalStorage extends AnimalCatalogStorage, AnimalUserPrefsStorage {
    // ----------------------------- AnimalUserPrefsStorage methods ---------------------------------------
    @Override
    Path getAnimalUserPrefsFilePath();

    @Override
    Optional<AnimalUserPrefs> readAnimalUserPrefs() throws DataLoadingException;

    @Override
    Optional<AnimalUserPrefs> readAnimalUserPrefs(Path prefsFilePath) throws DataLoadingException;

    @Override
    void saveAnimalUserPrefs(AnimalReadOnlyUserPrefs animalReadOnlyUserPrefs) throws IOException;

    // ----------------------------- AnimalCatalogStorage methods ---------------------------------------
    @Override
    Path getAnimalCatalogFilePath();

    @Override
    Optional<ReadOnlyAnimalCatalog> readAnimalCatalog() throws DataLoadingException;

    @Override
    void saveAnimalCatalog(ReadOnlyAnimalCatalog readOnlyAnimalCatalog) throws IOException;
}
