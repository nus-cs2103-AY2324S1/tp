package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;

/**
 * Represents a storage for {@link seedu.address.model.LocalCourseCatalogue}.
 */
public interface LocalCourseCatalogueStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLocalCourseCatalogueFilePath();

    /**
     * Returns LocalCourseCatalogue data as a {@link seedu.address.model.ReadOnlyLocalCourseCatalogue}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue() throws DataLoadingException;

    /**
     * @see #getLocalCourseCatalogueFilePath()
     */
    Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyLocalCourseCatalogue} to the storage.
     *
     * @param localCourseCatalogue cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue) throws IOException;

    /**
     * @see #saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue)
     */
    void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue, Path filePath) throws IOException;

}
