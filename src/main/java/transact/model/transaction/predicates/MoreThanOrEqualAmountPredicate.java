package transact.model.transaction.predicates;

import java.util.function.Predicate;

import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;

/**
 * Tests that a {@code Transaction}'s {@code Amount} is more than or equal to the amount given.
 */
public class MoreThanOrEqualAmountPredicate implements Predicate<Transaction> {

    private final Amount amount;

    public MoreThanOrEqualAmountPredicate(Amount amount) {
        this.amount = amount;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getAmount().getValue().compareTo(amount.getValue()) >= 0;
    }
}
