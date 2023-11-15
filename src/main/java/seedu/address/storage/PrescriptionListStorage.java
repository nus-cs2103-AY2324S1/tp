package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPrescriptionList;

/**
 * Represents a storage for {@link seedu.address.model.PrescriptionList}.
 */
public interface PrescriptionListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPrescriptionListFilePath();

    /**
     * Returns PrescriptionList data as a {@link ReadOnlyPrescriptionList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyPrescriptionList> readPrescriptionList() throws DataLoadingException;

    /**
     * @see #getPrescriptionListFilePath()
     */
    Optional<ReadOnlyPrescriptionList> readPrescriptionList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyPrescriptionList} to the storage.
     * @param prescriptionList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList) throws IOException;

    /**
     * @see #savePrescriptionList(ReadOnlyPrescriptionList)
     */
    void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList, Path filePath) throws IOException;

}
