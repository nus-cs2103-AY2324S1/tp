package seedu.address.logic.search;

import java.util.Map;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * A layer of abstraction around {@link SearchMatcher} that gives it {@link Predicate} behaviour.
 */
public class SearchPredicate implements Predicate<Person> {

    private final SearchMatcher predicate;

    SearchPredicate(SearchMatcher predicate) {
        this.predicate = predicate;
    }

    /**
     * Applies the predicate to the provided person.
     *
     * @param p person to apply a predicate to.
     * @return {@code true} if the person matches the predicate,
     *          {@code false} otherwise.
     */
    @Override
    public boolean test(Person p) {
        if (predicate == null) {
            return true;
        }
        Map<String, String> map = p.getFieldsAndAttributes();
        return FieldRanges.isMatch(this.predicate.test(map));
    }

}
