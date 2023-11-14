package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventsBook;
import seedu.address.model.ReadOnlyFinancesBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.address.AddressBookStorage;
import seedu.address.storage.events.EventsBookStorage;
import seedu.address.storage.finance.FinancesStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class BookStorageManager implements BookStorage {

    private static final Logger logger = LogsCenter.getLogger(BookStorageManager.class);
    private AddressBookStorage addressBookStorage;
    private EventsBookStorage eventsBookStorage;
    private FinancesStorage financesBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code BookStorageManager} with the given {@code AddressBookStorage}, {@code UserPrefStorage},
     * {@code EventsBookStorage} and {@code FinanceStorage}.
     */
    public BookStorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                              EventsBookStorage eventsBookStorage, FinancesStorage financeStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.eventsBookStorage = eventsBookStorage;
        this.financesBookStorage = financeStorage;
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


    // ================ EventsBook methods ==============================

    @Override
    public Path getEventsBookFilePath() {
        return eventsBookStorage.getEventsBookFilePath();
    }

    @Override
    public Optional<ReadOnlyEventsBook> readEventsBook() throws DataLoadingException {
        return readEventsBook(eventsBookStorage.getEventsBookFilePath());
    }

    @Override
    public Optional<ReadOnlyEventsBook> readEventsBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return eventsBookStorage.readEventsBook(filePath);
    }

    @Override
    public void saveEventsBook(ReadOnlyEventsBook eventsBook) throws IOException {
        saveEventsBook(eventsBook, eventsBookStorage.getEventsBookFilePath());
    }

    @Override
    public void saveEventsBook(ReadOnlyEventsBook eventsBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        eventsBookStorage.saveEventsBook(eventsBook, filePath);
    }

    // ================ FinancesBook methods ==============================

    @Override
    public Path getFinancesBookFilePath() {
        return financesBookStorage.getFinancesBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFinancesBook> readFinancesBook() throws DataLoadingException {
        return readFinancesBook(financesBookStorage.getFinancesBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFinancesBook> readFinancesBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return financesBookStorage.readFinancesBook(filePath);
    }

    @Override
    public void saveFinancesBook(ReadOnlyFinancesBook financesBook) throws IOException {
        saveFinancesBook(financesBook, financesBookStorage.getFinancesBookFilePath());
    }

    @Override
    public void saveFinancesBook(ReadOnlyFinancesBook financesBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        financesBookStorage.saveFinancesBook(financesBook, filePath);
    }
}
