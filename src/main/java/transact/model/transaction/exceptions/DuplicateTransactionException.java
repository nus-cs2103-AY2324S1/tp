package transact.model.transaction.exceptions;

public class DuplicateTransactionException extends RuntimeException{
    public DuplicateTransactionException() {
        super("Operation would result in duplicate transaction");
    }
}
