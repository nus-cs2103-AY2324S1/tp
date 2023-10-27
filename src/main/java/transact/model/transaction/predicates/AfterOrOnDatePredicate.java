package transact.model.transaction.predicates;

import java.util.function.Predicate;

import transact.model.transaction.Transaction;
import transact.model.transaction.info.Date;

/**
 * Tests that a {@code Transaction}'s {@code Date} is after or on the date given.
 */
public class AfterOrOnDatePredicate implements Predicate<Transaction> {

    private final Date date;

    public AfterOrOnDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getDate().getDate().after(date.getDate())
                || transaction.getDate().getDate().equals(date.getDate());
    }
}
