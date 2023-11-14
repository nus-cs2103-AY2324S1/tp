package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;

/**
 * Represents a storage for {@link seedu.address.model.PartnerCourseCatalogue}.
 */
public interface PartnerCourseCatalogueStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getPartnerCourseCatalogueFilePath();

    /**
     * Returns PartnerCourseCatalogue data as a {@link seedu.address.model.ReadOnlyPartnerCourseCatalogue}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue() throws DataLoadingException;

    Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyPartnerCourseCatalogue} to the storage.
     *
     * @param partnerCourseCatalogue cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue) throws IOException;

    /**
     * @see #savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue)
     */
    void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue, Path filePath)
            throws IOException;

}
