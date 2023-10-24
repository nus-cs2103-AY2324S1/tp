package seedu.address.model.person;

import java.util.function.Predicate;

public class PaidPredicate implements Predicate<Person> {

    private final boolean paid;
    public PaidPredicate(boolean paid) {
        this.paid = paid;
    }
    @Override
    public boolean test(Person person) {
        return !person.getPaid();
    }

}
