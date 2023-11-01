package transact.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import transact.commons.core.LogsCenter;
import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyAddressBook;
import transact.model.ReadOnlyTransactionBook;
import transact.model.ReadOnlyUserPrefs;
import transact.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TransactionBookStorage transactionBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          TransactionBookStorage transactionBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.transactionBookStorage = transactionBookStorage;
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
        logger.fine("Attempting to read address data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to address data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ TransactionBook methods ==============================
    @Override
    public Path getTransactionBookFilePath() {
        return transactionBookStorage.getTransactionBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTransactionBook> readTransactionBook() throws DataLoadingException {
        return readTransactionBook(transactionBookStorage.getTransactionBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTransactionBook> readTransactionBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read transaction data from file: " + filePath);
        return transactionBookStorage.readTransactionBook(filePath);
    }

    @Override
    public void saveTransactionBook(ReadOnlyTransactionBook transactionBook) throws IOException {
        saveTransactionBook(transactionBook, transactionBookStorage.getTransactionBookFilePath());
    }

    @Override
    public void saveTransactionBook(ReadOnlyTransactionBook transactionBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to transaction data file: " + filePath);
        transactionBookStorage.saveTransactionBook(transactionBook, filePath);
    }

}
