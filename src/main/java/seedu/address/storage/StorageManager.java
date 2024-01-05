package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyConsultationList;
import seedu.address.model.ReadOnlyGradedTestList;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private TaskListStorage taskListStorage;
    private GradedTestListStorage gradedTestListStorage;
    private SessionListStorage sessionListStorage;
    private ConsultationListStorage consultationListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code UserPrefStorage},
     * {@code TaskListStorage}, {@code SessionListStorage} and {@code ConsultationListStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage, TaskListStorage taskListStorage,
                          SessionListStorage sessionListStorage, ConsultationListStorage consultationListStorage,
                          GradedTestListStorage gradedTestListStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.taskListStorage = taskListStorage;
        this.sessionListStorage = sessionListStorage;
        this.consultationListStorage = consultationListStorage;
        this.gradedTestListStorage = gradedTestListStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ SessionList methods ===========================

    @Override
    public Path getSessionListFilePath() {
        return sessionListStorage.getSessionListFilePath();
    }

    @Override
    public Optional<ReadOnlySessionList> readSessionList() throws DataLoadingException {
        return readSessionList(sessionListStorage.getSessionListFilePath());
    }

    @Override
    public Optional<ReadOnlySessionList> readSessionList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return sessionListStorage.readSessionList(filePath);
    }

    @Override
    public void saveSessionList(ReadOnlySessionList sessionList) throws IOException {
        saveSessionList(sessionList, sessionListStorage.getSessionListFilePath());
    }

    @Override
    public void saveSessionList(ReadOnlySessionList sessionList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        sessionListStorage.saveSessionList(sessionList, filePath);
    }

    // ================ ConsultationList methods ===========================

    @Override
    public Path getConsultationListFilePath() {
        return consultationListStorage.getConsultationListFilePath();
    }

    @Override
    public Optional<ReadOnlyConsultationList> readConsultationList() throws DataLoadingException {
        return readConsultationList(consultationListStorage.getConsultationListFilePath());
    }

    @Override
    public Optional<ReadOnlyConsultationList> readConsultationList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return consultationListStorage.readConsultationList(filePath);
    }

    @Override
    public void saveConsultationList(ReadOnlyConsultationList consultationList) throws IOException {
        saveConsultationList(consultationList, consultationListStorage.getConsultationListFilePath());
    }

    @Override
    public void saveConsultationList(ReadOnlyConsultationList consultationList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        consultationListStorage.saveConsultationList(consultationList, filePath);
    }

    // ================ TaskList methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList() throws DataLoadingException {
        return readTaskList(taskListStorage.getTaskListFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskListStorage.readTaskList(filePath);
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList) throws IOException {
        saveTaskList(taskList, taskListStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskListStorage.saveTaskList(taskList, filePath);
    }

    // ================ GradedTest methods ==============================
    @Override
    public Path getGradedTestListFilePath() {
        return gradedTestListStorage.getGradedTestListFilePath();
    }

    @Override
    public Optional<ReadOnlyGradedTestList> readGradedTestList() throws DataLoadingException {
        return readGradedTestList(gradedTestListStorage.getGradedTestListFilePath());
    }

    @Override
    public Optional<ReadOnlyGradedTestList> readGradedTestList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return gradedTestListStorage.readGradedTestList(filePath);
    }

    @Override
    public void saveGradedTestList(ReadOnlyGradedTestList gradedTestList) throws IOException {
        saveGradedTestList(gradedTestList, gradedTestListStorage.getGradedTestListFilePath());
    }

    @Override
    public void saveGradedTestList(ReadOnlyGradedTestList gradedTestList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        gradedTestListStorage.saveGradedTestList(gradedTestList, filePath);
    }

}
