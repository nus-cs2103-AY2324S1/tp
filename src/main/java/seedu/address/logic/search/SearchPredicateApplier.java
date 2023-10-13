package seedu.address.logic.search;

import seedu.address.model.person.Person;

import java.util.Map;
import java.util.Objects;

public class SearchPredicateApplier {

    private final SearchPredicate predicate;

    public SearchPredicateApplier(SearchPredicate predicate) {
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
    public boolean apply(Person p) {
        Map<String, String> map = p.getFieldsAndAttributes();
        return FieldRanges.isMatch(this.predicate.test(map));
    }

}
