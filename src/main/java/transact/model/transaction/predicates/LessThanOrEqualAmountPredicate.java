package transact.model.transaction.predicates;

import java.util.function.Predicate;

import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;

/**
 * Tests that a {@code Transaction}'s {@code Amount} is less than or equal to the amount given.
 */
public class LessThanOrEqualAmountPredicate implements Predicate<Transaction> {

    private final Amount amount;

    public LessThanOrEqualAmountPredicate(Amount amount) {
        this.amount = amount;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getAmount().getValue().compareTo(amount.getValue()) <= 0;
    }
}
