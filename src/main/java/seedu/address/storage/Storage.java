package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the StoragePrescription component
 */
public interface Storage extends PrescriptionListStorage, CompletedPrescriptionListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPrescriptionListFilePath();

    @Override
    Optional<ReadOnlyPrescriptionList> readPrescriptionList() throws DataLoadingException;

    @Override
    void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList) throws IOException;

    @Override
    Path getCompletedPrescriptionListFilePath();

    @Override
    Optional<ReadOnlyPrescriptionList> readCompletedPrescriptionList() throws DataLoadingException;

    @Override
    void saveCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList) throws IOException;

}
