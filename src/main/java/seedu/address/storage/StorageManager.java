package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.BiDirectionalMap;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Person;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private ScheduleStorage scheduleListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage},
     * {@code ScheduleListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                          ScheduleStorage scheduleListStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.scheduleListStorage = scheduleListStorage;
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

    // ================ ScheduleStorage methods ==============================

    @Override
    public Path getScheduleListFilePath() {
        return scheduleListStorage.getScheduleListFilePath();
    }

    @Override
    public Optional<ReadOnlySchedule> readScheduleList() throws DataLoadingException {
        return readScheduleList(scheduleListStorage.getScheduleListFilePath());
    }

    @Override
    public Optional<ReadOnlySchedule> readScheduleList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleListStorage.readScheduleList(filePath);
    }

    @Override
    public void saveScheduleList(ReadOnlySchedule scheduleList) throws IOException {
        saveScheduleList(scheduleList, scheduleListStorage.getScheduleListFilePath());
    }

    @Override
    public void saveScheduleList(ReadOnlySchedule scheduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleListStorage.saveScheduleList(scheduleList, filePath);
    }

    public BiDirectionalMap<Person, Lesson> getPersonLessonMap() throws DataLoadingException {
        try {
            return BiDirectionalMap.readFrom(Paths.get("data", "personLessonMap.json"));
        } catch (ParseException e) {
            throw new DataLoadingException(e);
        }
    }
    public void savePersonLessonMap(BiDirectionalMap<Person, Lesson> personLessonMap) throws IOException {
        personLessonMap.saveTo(Paths.get("data", "personLessonMap.json"));
    }
}
