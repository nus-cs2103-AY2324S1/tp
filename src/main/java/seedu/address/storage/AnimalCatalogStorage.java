package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.ReadOnlyAnimalCatalog;


/**
 * Represents a storage for {@link AnimalCatalog}. Responsible for managing the read/write of {@link AnimalCatalog}
 * to local storage.
 */
public interface AnimalCatalogStorage {
    /**
     * Returns the file path of the data file. File path must be specified as an attribute in classes
     * that implement this interface.
     */
    Path getAnimalCatalogFilePath();

    /**
     * Returns AnimalCatalog data as a {@link ReadOnlyAnimalCatalog}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyAnimalCatalog> readAnimalCatalog() throws DataLoadingException;

    /**
     * Returns the AnimalCatalog data stored in the specified storage file as a {@link ReadOnlyAnimalCatalog}.
     *
     * @param filePath the storage file to read from.
     * @throws DataLoadingException if loading the data from storage failed.
     * @see #getAnimalCatalogFilePath()
     */
    Optional<ReadOnlyAnimalCatalog> readAnimalCatalog(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyAnimalCatalog} to the storage.
     *
     * @param animalCatalog cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAnimalCatalog(ReadOnlyAnimalCatalog animalCatalog) throws IOException;

    /**
     * Saves the given {@link ReadOnlyAnimalCatalog} to the specified storage file.
     *
     * @param animalCatalog cannot be null.
     * @param filePath the storage file to be written to.
     * @throws IOException if there was any problem writing to the file.
     * @see #saveAnimalCatalog(ReadOnlyAnimalCatalog)
     */
    void saveAnimalCatalog(ReadOnlyAnimalCatalog animalCatalog, Path filePath) throws IOException;
}
