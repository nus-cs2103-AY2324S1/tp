package seedu.address.logic.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents a predicate (boolean-valued function) of a Map representing a person,
 * meant for use in filtering a list of persons.
 * Capable of dealing with arbitrary fields.
 *
 * <p>Adapted with minimal changes from {@link java.util.function.Predicate}.
 */
interface SearchPredicate{

    /**
     * Evaluates this predicate on the given argument,
     * which is expected to be a Map representation of a {@link seedu.address.model.person.Person}.
     *
     * @param p the person's attributes, as a Map.
     * @return {@link FieldRanges} of all {@code null} values if no match,
     *      otherwise contains at least one {@link Range} that specifies the location(s) of the match.
     */
    FieldRanges test(Map<String, String> p);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default SearchPredicate and(SearchPredicate other) {
        Objects.requireNonNull(other);
        return (person) -> {
            FieldRanges ranges = this.test(person);
            if (!FieldRanges.isMatch(ranges)) {
                return null;
            }
            return FieldRanges.union(ranges, other.test(person));
        };
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate.
     */
    default SearchPredicate negate() {
        return (person) -> {
            FieldRanges ranges = this.test(person);
            if (!FieldRanges.isMatch(ranges)) {
                ranges = new FieldRanges();
                ranges.setIsMatch(true);
            } else {
                ranges = null;
            }
            return ranges;
        };
    }

    /**
     * Returns a composed predicate that represents a logical OR of this predicate and another.
     * When evaluating the composed predicate, no short-circuiting occurs;
     * this is due to needing an accurate representation of the matching range.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default SearchPredicate or(SearchPredicate other) {
        Objects.requireNonNull(other);
        return (person) -> FieldRanges.union(this.test(person), other.test(person));
    }

    /**
     * Returns the closure of the current predicate, as a SearchPredicate.
     * For most predicates, this does nothing; however for search parameters with flags attached to fences,
     * this stops applying the flag to joined predicates.
     *
     * <p>Predicates can be joined through {@link #and} and {@link #or}.
     *
     * @return predicate after closure.
     */
    default SearchPredicate close() {
        return this;
    }

    /**
     * Returns a new SearchPredicate, based on a simple predicate.
     * For most predicates, this merely returns the predicate;
     * however for search parameters with flags attached to fences, this applies some flags to the predicate,
     * and returns a new one.
     *
     * <p>Intended to be called on predicates before they are joined. If called after, behaviour is undefined.
     *
     * <p>Predicates can be joined through {@link #and} and {@link #or}.
     *
     * @return new SearchPredicate.
     */
    default SearchPredicate open(SearchPredicate predicate) {
        return predicate;
    }

}
