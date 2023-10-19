package seedu.lovebook.model.person;

import java.util.function.Predicate;

import seedu.lovebook.model.Model;


public class randomPredicate implements Predicate<Date> {
    private final Date random;

    public randomPredicate(Date random) {
        this.random = random;
    }

    @Override
    public boolean test(Date date) {
        return date.equals(random);
    }
}
