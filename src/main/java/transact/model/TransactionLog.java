package transact.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import transact.commons.util.ToStringBuilder;
import transact.model.transaction.Transaction;
import transact.model.transaction.UniqueTransactionList;

/**
 * Wraps all data at the transaction log level.
 */
public class TransactionLog implements ReadOnlyTransactionLog {

    private final UniqueTransactionList transactions;

    {
        transactions = new UniqueTransactionList();
    }

    public TransactionLog() {
    }

    /**
     * Creates a TransactionLog using the transactions in the {@code toBeCopied}.
     */
    public TransactionLog(ReadOnlyTransactionLog toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the transaction list with {@code transactions}.
     * {@code transactions} must not contain duplicate transactions.
     */
    public void setTransactions(ObservableList<Transaction> transactions) {
        this.transactions.setTransactions(transactions);
    }

    /**
     * Resets the existing data of this {@code TransactionLog} with {@code newData}.
     */
    public void resetData(ReadOnlyTransactionLog newData) {
        requireNonNull(newData);
        setTransactions(newData.getTransactionList());
    }

    //// transaction-level operations

    /**
     * Returns true if a transaction with the same identity as {@code transaction}
     * exists in the transaction log.
     */
    public boolean hasTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return transactions.contains(transaction);
    }

    /**
     * Adds a transaction to the transaction log.
     * The transaction must not already exist in the transaction log.
     */
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    /**
     * Replaces the given transaction {@code target} in the list with
     * {@code editedTransaction}.
     * {@code target} must exist in the transaction log.
     * The transaction identity of {@code editedTransaction} must not be the same as
     * another
     * existing transaction in the transaction log.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireNonNull(editedTransaction);
        transactions.setTransaction(target, editedTransaction);
    }

    /**
     * Removes {@code key} from this {@code TransactionLog}.
     * {@code key} must exist in the transaction log.
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

        if (!(other instanceof TransactionLog)) {
            return false;
        }

        TransactionLog otherTransactionLog = (TransactionLog) other;
        return transactions.equals(otherTransactionLog.transactions);
    }

    @Override
    public int hashCode() {
        return transactions.hashCode();
    }
}
