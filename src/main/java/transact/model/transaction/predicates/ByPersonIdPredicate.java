package transact.model.transaction.predicates;

import java.util.function.Predicate;

import transact.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code PersonId} is equal to the personId given.
 */
public class ByPersonIdPredicate implements Predicate<Transaction> {

    private final Integer personId;

    public ByPersonIdPredicate(Integer personId) {
        this.personId = personId;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getPersonId().equals(personId);
    }
}
