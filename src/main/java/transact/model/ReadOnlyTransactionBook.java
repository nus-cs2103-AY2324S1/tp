package transact.model;

import javafx.collections.ObservableList;
import transact.model.transaction.Transaction;

/**
 * Unmodifiable view of a transaction book
 */
public interface ReadOnlyTransactionBook {

    /**
     * Returns an unmodifiable view of the transaction list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Transaction> getTransactionList();

}
