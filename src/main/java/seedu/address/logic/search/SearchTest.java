package seedu.address.logic.search;

import seedu.address.model.person.Person;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * A layer of abstraction around {@link SearchPredicate} that gives it {@link Predicate} behaviour.
 */
public class SearchTest implements Predicate<Person> {

    private final SearchPredicate predicate;

    SearchTest(SearchPredicate predicate) {
        Objects.requireNonNull(predicate);
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
        Map<String, String> map = p.getFieldsAndAttributes();
        return FieldRanges.isMatch(this.predicate.test(map));
    }

}
