package transact.model.transaction.exceptions;

/**
 * Signals that the Transaction does not exist
 */
public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("The Transaction is Not Found");
    }
}
