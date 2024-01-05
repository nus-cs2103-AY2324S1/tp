package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyConsultationList;

/**
 * Represents a storage for {@link seedu.address.model.ConsultationListBook}.
 */
public interface ConsultationListStorage {
    /**
     * Returns the file path of the data file.
     *
     * @return The file path of the data file.
     */
    Path getConsultationListFilePath();

    /**
     * Reads the consultation list data from the default file path.
     *
     * @return An {@link Optional} containing the read {@link ReadOnlyConsultationList},
     *         or an empty {@link Optional} if no data is found.
     * @throws DataLoadingException If there is an error reading the data from the file.
     */
    Optional<ReadOnlyConsultationList> readConsultationList() throws DataLoadingException;

    /**
     * Reads the consultation list data from the default file path.
     *
     * @return An {@link Optional} containing the read {@link ReadOnlyConsultationList},
     *         or an empty {@link Optional} if no data is found.
     * @throws DataLoadingException If there is an error reading the data from the file.
     */
    Optional<ReadOnlyConsultationList> readConsultationList(Path filePath) throws DataLoadingException;

    /**
     * Saves the consultation list data to the default file path.
     *
     * @param consultationList The {@link ReadOnlyConsultationList} to be saved.
     * @throws IOException If there is an error writing the data to the file.
     */
    void saveConsultationList(ReadOnlyConsultationList consultationList) throws IOException;

    /**
     * Saves the consultation list data to the specified file path.
     *
     * @param consultationList The {@link ReadOnlyConsultationList} to be saved.
     * @param filePath   The file path to which the data should be saved.
     * @throws IOException If there is an error writing the data to the file.
     */
    void saveConsultationList(ReadOnlyConsultationList consultationList, Path filePath) throws IOException;
}
