package transact.model;

import javafx.collections.ObservableList;
import transact.model.transaction.Transaction;

/**
 * Unmodifiable view of a record (transaction) book
 */
public interface ReadOnlyRecordBook {

    /**
     * Returns an unmodifiable view of the transaction list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Transaction> getTransactionList();

}
