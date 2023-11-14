package seedu.spendnsplit.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.commons.numbers.fraction.BigFraction;

import javafx.collections.ObservableList;
import seedu.spendnsplit.logic.commands.exceptions.CommandException;
import seedu.spendnsplit.logic.parser.CommandAliasMap;
import seedu.spendnsplit.model.person.Name;
import seedu.spendnsplit.model.person.Person;
import seedu.spendnsplit.model.transaction.Timestamp;
import seedu.spendnsplit.model.transaction.Transaction;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Transaction> PREDICATE_SHOW_ALL_TRANSACTIONS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' spendNSplit book file path.
     */
    Path getSpendNSplitBookFilePath();

    /**
     * Sets the user prefs' spendNSplit book file path.
     */
    void setSpendNSplitBookFilePath(Path spendNSplitBookFilePath);

    /**
     * Returns the SpendNSplitBook
     */
    ReadOnlySpendNSplitBook getSpendNSplitBook();

    /**
     * Replaces spendNSplit book data with the data in {@code spendNSplitBook}.
     */
    void setSpendNSplitBook(ReadOnlySpendNSplitBook spendNSplitBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the spendNSplit book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a transaction with the same identity as {@code transaction} exists in the spendNSplit book.
     */
    boolean hasTransaction(Transaction transaction);

    /**
     * Deletes the given person.
     * The person must exist in the spendNSplit book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given transaction.
     * The transaction must exist in the spendNSplit book.
     */
    void deleteTransaction(Transaction target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the spendNSplit book.
     */
    void addPerson(Person person);

    /**
     * Adds the given transaction.
     * {@code transaction} must not already exist in the spendNSplit book.
     */
    void addTransaction(Transaction transaction);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the spendNSplit book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the spendNSplit book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given transaction {@code target} with {@code editedTransaction}.
     * {@code target} must exist in the spendNSplit book.
     * The transaction identity of {@code editedTransaction} must not be the same
     * as another existing transaction in the spendNSplit book.
     */
    void setTransaction(Transaction target, Transaction editedTransaction);

    /**
     * Returns the total balance of all transaction that the person has to pay the user.
     *
     * @param name the name of the person
     */
    BigFraction getBalance(Name name);

    /**
     * Returns the total balance of all transaction before a stated time that the person has to pay the user.
     *
     * @param name the name of the person
     * @param time the time before which transactions are accounted for
     */
    BigFraction getBalance(Name name, Timestamp time);

    /**
     * Gets all names in the model.
     */
    Set<Name> getAllNames();

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the full transaction list.
     */
    ObservableList<Transaction> getFullTransactionList();

    /**
     * Returns an unmodifiable view of the filtered transaction list.
     */
    ObservableList<Transaction> getFilteredTransactionList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered transaction list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTransactionList(Predicate<Transaction> predicate);

    /**
     * Gets the command aliases stored in the user preferences.
     */
    CommandAliasMap getCommandMap();

    /**
     * Ties the alias to the command, and returns the previous alias for the command, if any.
     */
    String setCommandAlias(String command, String alias) throws CommandException;

    /**
     * Sorts person list in ascending order of balance.
     */
    void sortPersonAscending();

    /**
     * Sorts person list in descending order of balance.
     */
    void sortPersonDescending();
}
