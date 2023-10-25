package seedu.address.model.person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Policy Expiry Date}is within a certain period from the current date.
 */
public class RemindPredicate implements Predicate<Person> {

    private static final int VALID_DAYS_STARTS_FROM = 0;
    private static final int DUE_DAYS = 31;

    @Override
    public boolean test(Person person) {
        long dueDays = ChronoUnit.DAYS.between(LocalDate.now(), person.getPolicy().getPolicyExpiryDate().date);
        return dueDays >= VALID_DAYS_STARTS_FROM && dueDays <= DUE_DAYS;
    }
}
