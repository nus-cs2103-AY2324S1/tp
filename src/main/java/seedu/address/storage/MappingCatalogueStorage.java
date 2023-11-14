package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyMappingCatalogue;

/**
 * Represents a storage for {@link seedu.address.model.MappingCatalogue}.
 */
public interface MappingCatalogueStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMappingCatalogueFilePath();

    /**
     * Returns MappingCatalogue data as a {@link seedu.address.model.ReadOnlyMappingCatalogue}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyMappingCatalogue> readMappingCatalogue() throws DataLoadingException;

    /**
     * @see #getMappingCatalogueFilePath()
     */
    Optional<ReadOnlyMappingCatalogue> readMappingCatalogue(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyMappingCatalogue} to the storage.
     *
     * @param mappingCatalogue cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue) throws IOException;

    /**
     * @see #saveMappingCatalogue(ReadOnlyMappingCatalogue)
     */
    void saveMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue, Path filePath) throws IOException;

}
