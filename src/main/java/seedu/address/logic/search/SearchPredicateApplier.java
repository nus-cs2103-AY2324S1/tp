package seedu.address.logic.search;

import seedu.address.model.person.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SearchPredicateApplier {

    private SearchPredicate predicate;

    public SearchPredicateApplier(SearchPredicate predicate) {
        Objects.requireNonNull(predicate);
        this.predicate = predicate;
    }

    /**
     * Applies {@link java.lang.reflect reflection} to gain access to a list of {@link Person} fields at runtime,
     * meant to
     * @param p
     * @return
     */
    public boolean apply(Person p) {
        Map<String, String> map = p.getFieldsAndAttributes();
        this.predicate.test(map);
    }

}
