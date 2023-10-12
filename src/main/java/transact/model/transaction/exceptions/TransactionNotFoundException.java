package transact.model.transaction.exceptions;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("The Transaction is Not Found");
    }
}
