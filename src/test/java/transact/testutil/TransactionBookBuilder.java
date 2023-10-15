package transact.testutil;

import transact.model.TransactionBook;
import transact.model.transaction.Transaction;

/**
 * A utility class to help with building TransactionBook objects.
 * Example usage: <br>
 * {@code TransactionBook ab = new TransactionBookBuilder().withTransaction(APPLES, BANANAS).build();}
 */
public class TransactionBookBuilder {

    private TransactionBook transactionBook;

    public TransactionBookBuilder() {
        transactionBook = new TransactionBook();
    }

    public TransactionBookBuilder(TransactionBook transactionBook) {
        this.transactionBook = transactionBook;
    }

    /**
     * Adds a new {@code Transaction} to the {@code TransactionBook} that we are building.
     */
    public TransactionBookBuilder withTransaction(Transaction transaction) {
        transactionBook.addTransaction(transaction);
        return this;
    }

    public TransactionBook build() {
        return transactionBook;
    }
}
