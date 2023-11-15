package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PrescriptionList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PrescriptionListStorage prescriptionListStorage;
    private CompletedPrescriptionListStorage completedPrescriptionListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManagerPrescription} with the given {@code PrescriptionListStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(PrescriptionListStorage prescriptionListStorage,
        CompletedPrescriptionListStorage completedPrescriptionListStorage, UserPrefsStorage userPrefsStorage) {
        this.prescriptionListStorage = prescriptionListStorage;
        this.completedPrescriptionListStorage = completedPrescriptionListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PrescriptionList methods ==============================

    @Override
    public Path getPrescriptionListFilePath() {
        return prescriptionListStorage.getPrescriptionListFilePath();
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readPrescriptionList() throws DataLoadingException {
        return readPrescriptionList(prescriptionListStorage.getPrescriptionListFilePath());
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readPrescriptionList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return prescriptionListStorage.readPrescriptionList(filePath);
    }

    @Override
    public void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList) throws IOException {
        savePrescriptionList(prescriptionList, prescriptionListStorage.getPrescriptionListFilePath());
    }

    @Override
    public void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        prescriptionListStorage.savePrescriptionList(prescriptionList, filePath);
    }

    // ================ CompletedPrescriptionList methods ==============================

    @Override
    public Path getCompletedPrescriptionListFilePath() {
        return completedPrescriptionListStorage.getCompletedPrescriptionListFilePath();
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readCompletedPrescriptionList() throws DataLoadingException {
        return readCompletedPrescriptionList(completedPrescriptionListStorage.getCompletedPrescriptionListFilePath());
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readCompletedPrescriptionList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return completedPrescriptionListStorage.readCompletedPrescriptionList(filePath);
    }

    @Override
    public void saveCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList) throws IOException {
        saveCompletedPrescriptionList(completedPrescriptionList,
                completedPrescriptionListStorage.getCompletedPrescriptionListFilePath());
    }

    @Override
    public void saveCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList,
            Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        completedPrescriptionListStorage.saveCompletedPrescriptionList(completedPrescriptionList, filePath);
    }

}
