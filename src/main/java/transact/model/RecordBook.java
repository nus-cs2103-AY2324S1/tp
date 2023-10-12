package transact.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import transact.commons.util.ToStringBuilder;
import transact.model.transaction.Transaction;

/**
 * Wraps transaction data at the record-book level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class RecordBook implements ReadOnlyRecordBook {

    private final UniqueEntryList<Transaction> transactions;

    {
        transactions = new UniqueEntryList<>();
    }

    public RecordBook() {
    }

    /**
     * Creates a RecordBook using the Transactions in the {@code toBeCopied}
     */
    public RecordBook(ReadOnlyRecordBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the transaction list with {@code transactions}.
     * {@code transactions} must not contain duplicate transactions.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions.setEntries(transactions);
    }

    /**
     * Resets the existing data of this {@code RecordBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRecordBook newData) {
        requireNonNull(newData);

        setTransactions(newData.getTransactionList());
    }

    //// transaction-level operations

    /**
     * Returns true if a transaction with the same identity as {@code transaction}
     * exists in the record book.
     */
    public boolean hasTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return transactions.contains(transaction);
    }

    /**
     * Adds a transaction to the record book.
     * The transaction must not already exist in the record book.
     */
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    /**
     * Replaces the given transaction {@code target} in the list with
     * {@code editedTransaction}.
     * {@code target} must exist in the record book.
     * The transaction identity of {@code editedTransaction} must not be the
     * same as another existing transaction in the record book.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireNonNull(editedTransaction);

        transactions.setEntry(target, editedTransaction);
    }

    /**
     * Removes {@code key} from this {@code RecordBook}.
     * {@code key} must exist in the record book.
     */
    public void removeTransaction(Transaction key) {
        transactions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactions", transactions)
                .toString();
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecordBook)) {
            return false;
        }

        RecordBook otherRecordBook = (RecordBook) other;
        return transactions.equals(otherRecordBook.transactions);
    }

    @Override
    public int hashCode() {
        return transactions.hashCode();
    }
}
