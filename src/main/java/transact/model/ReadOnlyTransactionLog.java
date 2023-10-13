package transact.model;

import javafx.collections.ObservableList;
import transact.model.transaction.Transaction;

/**
 * Unmodifiable view of a transaction log
 */
public interface ReadOnlyTransactionLog {

    /**
     * Returns an unmodifiable view of the transactions list.
     * This list will not contain any duplicate transactions.
     */
    ObservableList<Transaction> getTransactionList();

}

