package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyConsultationList;
import seedu.address.model.ReadOnlyGradedTestList;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage,
        TaskListStorage, SessionListStorage, ConsultationListStorage, GradedTestListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Optional<ReadOnlySessionList> readSessionList() throws DataLoadingException;

    @Override
    void saveSessionList(ReadOnlySessionList sessionList) throws IOException;

    @Override
    Optional<ReadOnlyConsultationList> readConsultationList() throws DataLoadingException;

    @Override
    void saveConsultationList(ReadOnlyConsultationList consultationList) throws IOException;

    @Override
    Optional<ReadOnlyTaskList> readTaskList() throws DataLoadingException;

    @Override
    void saveTaskList(ReadOnlyTaskList taskList) throws IOException;

    @Override
    Optional<ReadOnlyGradedTestList> readGradedTestList() throws DataLoadingException;

    @Override
    void saveGradedTestList(ReadOnlyGradedTestList gradedTestList, Path filePath) throws IOException;

}
