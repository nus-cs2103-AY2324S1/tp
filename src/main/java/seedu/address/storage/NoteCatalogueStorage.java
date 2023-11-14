package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyNoteCatalogue;

/**
 * Represents a storage for {@link seedu.address.model.NoteCatalogue}.
 */
public interface NoteCatalogueStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getNoteCatalogueFilePath();

    /**
     * Returns NoteCatalogue data as a {@link seedu.address.model.ReadOnlyNoteCatalogue}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyNoteCatalogue> readNoteCatalogue() throws DataLoadingException;

    /**
     * @see #getNoteCatalogueFilePath()
     */
    Optional<ReadOnlyNoteCatalogue> readNoteCatalogue(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyNoteCatalogue} to the storage.
     *
     * @param noteCatalogue cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue) throws IOException;

    /**
     * @see #saveNoteCatalogue(ReadOnlyNoteCatalogue)
     */
    void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue, Path filePath) throws IOException;

}
