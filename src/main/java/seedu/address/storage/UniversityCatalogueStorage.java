package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyUniversityCatalogue;

/**
 * Represents a storage for {@link seedu.address.model.UniversityCatalogue}.
 */
public interface UniversityCatalogueStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUniversityCatalogueFilePath();

    /**
     * Returns UniversityCatalogue data as a {@link seedu.address.model.ReadOnlyUniversityCatalogue}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue() throws DataLoadingException;

    /**
     * @see #getUniversityCatalogueFilePath()
     */
    Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyUniversityCatalogue} to the storage.
     *
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue) throws IOException;

    /**
     * @see #saveUniversityCatalogue(ReadOnlyUniversityCatalogue)
     */
    void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue, Path filePath) throws IOException;

}
