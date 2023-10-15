package seedu.address.commons.util;

import java.util.List;
import java.util.function.Predicate;

/**
 * Helper function for combining multiple predicates
 */
public class PredicateUtil {
    /**
     * Combines all the predicates in the list with the AND logic and returns the predicate.
     *
     * @param predicates list of predicates.
     * @param <T> the type of value in the Predicate instance.
     * @return the predicate after performing AND operations on all.
     */
    public static <T> Predicate<T> combinePredicates(List<Predicate<T>> predicates) {
        return predicates.stream().reduce(p -> true, Predicate::and);
    }
}
