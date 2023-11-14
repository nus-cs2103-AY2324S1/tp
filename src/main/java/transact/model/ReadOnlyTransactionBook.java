package transact.model;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;

/**
 * Unmodifiable view of a transaction book
 */
public interface ReadOnlyTransactionBook {

    /**
     * Returns an unmodifiable view of the transaction map.
     */
    ObservableMap<TransactionId, Transaction> getTransactionMap();

    /**
     * Returns an unmodifiable view of the transaction list converted from the map.
     */
    ObservableList<Transaction> getTransactionList();

}
