package transact.testutil;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import transact.commons.core.GuiSettings;
import transact.model.Model;
import transact.model.ReadOnlyAddressBook;
import transact.model.ReadOnlyTransactionBook;
import transact.model.ReadOnlyUserPrefs;
import transact.model.person.Person;
import transact.model.person.PersonId;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getTransactionBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTransactionBookFilePath(Path transactionBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExportFilePath(Path path) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getExportFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTransactionBook(ReadOnlyTransactionBook transactionBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public Person deletePerson(PersonId targetId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(PersonId targetId, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Person getPerson(Integer personId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableMap<PersonId, Person> getPersonMap() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTransaction(TransactionId transactionId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Transaction deleteTransaction(TransactionId transactionId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTransaction(Transaction transaction) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTransaction(TransactionId transactionId, Transaction editedTransaction) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTransactionBook getTransactionBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTransactionList(Comparator<Transaction> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Transaction getTransaction(TransactionId transactionId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableMap<TransactionId, Transaction> getTransactionMap() {
        throw new AssertionError("This method should not be called.");
    }
}
