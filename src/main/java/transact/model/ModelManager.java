package transact.model;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import transact.commons.core.GuiSettings;
import transact.commons.core.LogsCenter;
import transact.model.person.Person;
import transact.model.person.PersonId;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TransactionBook transactionBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Transaction> filteredTransactions;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyTransactionBook transactionBook,
            ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.transactionBook = new TransactionBook(transactionBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTransactions = new FilteredList<>(this.transactionBook.getTransactionList());
    }

    public ModelManager() {
        this(new AddressBook(), new TransactionBook(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getTransactionBookFilePath() {
        return userPrefs.getTransactionBookFilePath();
    }

    @Override
    public void setTransactionBookFilePath(Path transactionBookFilePath) {
        requireNonNull(transactionBookFilePath);
        userPrefs.setTransactionBookFilePath(transactionBookFilePath);
    }

    // =========== AddressBook
    // ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(PersonId personId) {
        requireNonNull(personId);
        return addressBook.hasPerson(personId);
    }

    @Override
    public Person deletePerson(PersonId targetId) {
        return addressBook.removePerson(targetId);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(PersonId targetId, Person editedPerson) {
        requireAllNonNull(targetId, editedPerson);
        addressBook.setPerson(targetId, editedPerson);
    }

    @Override
    public Person getPerson(PersonId targetId) {
        return addressBook.getPersonMap().get(targetId);
    }

    // =========== TransactionBook
    // ================================================================================

    @Override
    public void setTransactionBook(ReadOnlyTransactionBook transactionBook) {
        this.transactionBook.resetData(transactionBook);
    }

    @Override
    public TransactionBook getTransactionBook() {
        return transactionBook;
    }

    @Override
    public boolean hasTransaction(TransactionId transactionId) {
        return transactionBook.hasTransaction(transactionId);
    }

    @Override
    public Transaction deleteTransaction(TransactionId transactionId) {
        return transactionBook.removeTransaction(transactionId);
    }

    @Override
    public Transaction getTransaction(TransactionId transactionId) {
        return transactionBook.getTransactionMap().get(transactionId);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        requireNonNull(transaction);
        transactionBook.addTransaction(transaction);
    }

    public void setTransaction(TransactionId transactionId, Transaction editedTransaction) {
        requireAllNonNull(transactionId, editedTransaction);
        transactionBook.setTransaction(transactionId, editedTransaction);
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of
     * {@code versionedAddressBook}
     */

    @Override
    public ObservableMap<PersonId, Person> getPersonMap() {
        return addressBook.getPersonMap();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    // =========== Filtered Transaction List Accessors
    // ========================================================
    /**
     * Returns an unmodifiable view of the list of {@code Transaction} backed by the
     * internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableMap<TransactionId, Transaction> getTransactionMap() {
        return transactionBook.getTransactionMap();
    }

    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        return filteredTransactions;
    }

    @Override
    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredTransactions.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && transactionBook.equals(otherModelManager.transactionBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredTransactions.equals(otherModelManager.filteredTransactions);
    }
}
