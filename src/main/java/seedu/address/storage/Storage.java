package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.task.ReadOnlyTaskManager;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, CalendarStorage, TaskManagerStorage, UserPrefsStorage {

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
    Optional<ReadOnlyCalendar> readCalendar() throws DataLoadingException;

    @Override
    void saveCalendar(ReadOnlyCalendar calendar) throws IOException;

    @Override
    Optional<ReadOnlyTaskManager> readTaskManager() throws DataLoadingException;

    @Override
    void saveTaskManager(ReadOnlyTaskManager taskManager) throws IOException;


}
