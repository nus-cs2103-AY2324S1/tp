package seedu.address.model.person.predicates;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that at least one of a {@code Person}'s fields matches any of the keywords given.
 */
public class CombinedPredicate implements Predicate<Person> {
    public final PersonContainsKeywordsPredicate[] predicates;

    /**
     * Creates a combined "or" predicate from the given collection of predicates. Requires that none of the elements
     * in the given collection is null.
     *
     * @param predicates keyword predicates to check a person against.
     */
    public CombinedPredicate(PersonContainsKeywordsPredicate... predicates) {
        CollectionUtil.requireAllNonNull((Object[]) predicates);
        this.predicates = new PersonContainsKeywordsPredicate[predicates.length];
        for (int i = 0; i < predicates.length; i++) {
            this.predicates[i] = predicates[i];
        }
    }

    @Override
    public boolean test(Person person) {
        boolean result = false;
        for (PersonContainsKeywordsPredicate predicate : this.predicates) {
            result = result || predicate.test(person);
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CombinedPredicate)) {
            return false;
        }

        CombinedPredicate otherNameContainsKeywordsPredicate =
                (CombinedPredicate) other;
        return Arrays.equals(predicates,
                otherNameContainsKeywordsPredicate.predicates);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        for (PersonContainsKeywordsPredicate predicate : this.predicates) {
            toStringBuilder.add(predicate.getClass().getCanonicalName(), predicate.toString());
        }
        return toStringBuilder.toString();
    }
}
