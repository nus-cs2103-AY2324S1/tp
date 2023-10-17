package transact.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import transact.commons.core.GuiSettings;
import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Person {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    /** Person {@code Predicate} that always evaluate to false */
    Predicate<Person> PREDICATE_HIDE_ALL_PERSONS = unused -> false;
    /** Transaction {@code Predicate} that always evaluate to true */
    Predicate<Transaction> PREDICATE_SHOW_ALL_TRANSACTIONS = unused -> true;
    /** Transaction {@code Predicate} that always evaluate to false */
    Predicate<Transaction> PREDICATE_HIDE_ALL_TRANSACTIONS = unused -> false;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the user prefs' transaction book file path.
     */
    Path getTransactionBookFilePath();

    /**
     * Sets the user prefs' transaction book file path.
     */
    void setTransactionBookFilePath(Path transactionBookFilePath);

    /**
     * Replaces transaction book data with the data in {@code transactionBook}.
     */
    void setTransactionBook(ReadOnlyTransactionBook transactionBook);

    /** Returns the TransactionBook */
    ReadOnlyTransactionBook getTransactionBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if a transaction with the same id as {@code transactionId}
     * exists in the transaction book.
     */
    boolean hasTransaction(TransactionId transactionId);

    /**
     * Deletes the given transaction with {@code transactionId}.
     * The transaction must exist in the transaction book.
     */
    Transaction deleteTransaction(TransactionId transactionId);

    /**
     * Adds the given transaction.
     * {@code transaction} must not already exist in the transaction book.
     */
    void addTransaction(Transaction transaction);

    /**
     * Replaces the given transaction {@code target} with {@code editedTransaction}.
     * {@code target} must exist in the transaction book.
     * The person identity of {@code editedTransaction} must not be the same as
     * another
     * existing transaction in the transaction book.
     */
    void setTransaction(TransactionId targetId, Transaction editedTransaction);

    /**
     * Returns the transaction with {@code transactionId}.
     * {@code transactionId} must exist in the transaction book.
     */
    Transaction getTransaction(TransactionId transactionId);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException
     *             if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered transaction list */
    ObservableList<Transaction> getFilteredTransactionList();

    /** Returns an unmodifiable view of the filtered transaction list */
    ObservableMap<TransactionId, Transaction> getTransactionMap();

    /**
     * Updates the filter of the filtered transaction list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException
     *             if {@code predicate} is null.
     */
    void updateFilteredTransactionList(Predicate<Transaction> predicate);
}
