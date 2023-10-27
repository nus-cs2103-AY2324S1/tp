package transact.model.transaction.predicates;

import java.util.function.Predicate;

import transact.model.transaction.Transaction;
import transact.model.transaction.info.Date;

/**
 * Tests that a {@code Transaction}'s {@code Date} is before or on the date given.
 */
public class BeforeOrOnDatePredicate implements Predicate<Transaction> {

    private final Date date;

    public BeforeOrOnDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Transaction transaction) {
        return transaction.getDate().getDate().before(date.getDate())
                || transaction.getDate().getDate().equals(date.getDate());
    }
}
