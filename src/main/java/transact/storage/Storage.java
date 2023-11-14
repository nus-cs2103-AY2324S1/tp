package transact.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyAddressBook;
import transact.model.ReadOnlyTransactionBook;
import transact.model.ReadOnlyUserPrefs;
import transact.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, TransactionBookStorage {

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
    Path getTransactionBookFilePath();

    @Override
    Optional<ReadOnlyTransactionBook> readTransactionBook() throws DataLoadingException;

    @Override
    void saveTransactionBook(ReadOnlyTransactionBook transactionBook) throws IOException;

}
