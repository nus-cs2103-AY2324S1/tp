package transact.model.transaction.predicates;

import java.util.function.Predicate;

import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionType;

/**
 * Tests that a {@code Transaction}'s {@code TransactionType} is equal to the transaction type given.
 */
public class TransactionTypePredicate implements Predicate<Transaction> {
    private final TransactionType transactionType;

    public TransactionTypePredicate(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getTransactionType().equals(transactionType);
    }
}
