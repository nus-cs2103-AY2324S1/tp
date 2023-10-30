package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.BiDirectionalMap;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.person.Person;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, ScheduleStorage {

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
    Path getScheduleListFilePath();

    @Override
    Optional<ReadOnlySchedule> readScheduleList() throws DataLoadingException;

    @Override
    void saveScheduleList(ReadOnlySchedule scheduleList) throws IOException;

    BiDirectionalMap<Person, Lesson> getPersonLessonMap() throws DataLoadingException;
    void savePersonLessonMap(BiDirectionalMap<Person, Lesson> personLessonMap) throws IOException;

    BiDirectionalMap<Lesson, Task> getLessonTaskMap() throws DataLoadingException;
    void saveLessonTaskMap(BiDirectionalMap<Lesson, Task> lessonTaskMap) throws IOException;

}
