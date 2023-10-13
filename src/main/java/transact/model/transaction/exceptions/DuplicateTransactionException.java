package transact.model.transaction.exceptions;

/**
 * Signals that the operation will result in duplicate Transaction.
 */
public class DuplicateTransactionException extends RuntimeException {
    public DuplicateTransactionException() {
        super("Operation would result in duplicate transaction");
    }
}
