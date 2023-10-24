package transact.model;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import transact.commons.util.ToStringBuilder;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;

/**
 * Wraps transaction data at the transaction-book level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class TransactionBook implements ReadOnlyTransactionBook {

    private final UniqueEntryHashmap<TransactionId, Transaction> transactions;

    private ObservableList<Transaction> transactionList;

    {
        transactions = new UniqueEntryHashmap<>();
        transactionList = FXCollections.observableArrayList();

        getTransactionMap()
                .addListener((MapChangeListener.Change<? extends TransactionId, ? extends Transaction> change) -> {
                    boolean removed = change.wasRemoved();
                    if (removed != change.wasAdded()) {
                        if (removed) {
                            // no put for existing key
                            // remove pair completely
                            transactionList.remove(change.getValueRemoved());
                        } else {
                            // add new entry
                            transactionList.add(change.getValueAdded());
                        }
                    } else {
                        // replace existing entry
                        Transaction entry = change.getValueAdded();

                        for (int i = 0; i < transactionList.size(); i++) {
                            if (transactionList.get(i).getTransactionId() == entry.getTransactionId()) {
                                transactionList.set(i, entry);
                            }
                        }
                    }
                });
    }

    public TransactionBook() {
    }

    /**
     * Creates a TransactionBook using the Transactions in the {@code toBeCopied}
     */
    public TransactionBook(ReadOnlyTransactionBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the transaction list with {@code transactions}.
     * {@code transactions} must not contain duplicate transactions.
     */
    public void setTransactions(Map<TransactionId, Transaction> transactions) {
        this.transactions.setEntries(transactions);
    }

    /**
     * Resets the existing data of this {@code TransactionBook} with
     * {@code newData}.
     */
    public void resetData(ReadOnlyTransactionBook newData) {
        requireNonNull(newData);

        setTransactions(newData.getTransactionMap());
    }

    //// transaction-level operations

    /**
     * Returns true if a transaction with the same identity as {@code transaction}
     * exists in the transaction book.
     */
    public boolean hasTransaction(TransactionId transactionId) {
        requireNonNull(transactionId);
        return transactions.contains(transactionId);
    }

    /**
     * Adds a transaction to the transaction book.
     * The transaction must not already exist in the transaction book.
     */
    public void addTransaction(Transaction t) {
        transactions.add(t.getTransactionId(), t);
    }

    /**
     * Replaces the given transaction {@code target} in the list with
     * {@code editedTransaction}.
     * {@code target} must exist in the transaction book.
     * The transaction identity of {@code editedTransaction} must not be the
     * same as another existing transaction in the transaction book.
     */
    public void setTransaction(TransactionId id, Transaction editedTransaction) {
        requireNonNull(editedTransaction);

        transactions.setEntry(id, editedTransaction);
    }

    /**
     * Removes {@code key} from this {@code TransactionBook}.
     * {@code key} must exist in the transaction book.
     */
    public Transaction removeTransaction(TransactionId key) {
        return transactions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactions", transactions)
                .toString();
    }

    @Override
    public ObservableMap<TransactionId, Transaction> getTransactionMap() {
        return transactions.asUnmodifiableObservableMap();
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionBook)) {
            return false;
        }

        TransactionBook otherTransactionBook = (TransactionBook) other;
        return transactions.equals(otherTransactionBook.transactions);
    }

    @Override
    public int hashCode() {
        return transactions.hashCode();
    }
}
