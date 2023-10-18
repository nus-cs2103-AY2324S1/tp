package seedu.address.logic.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a predicate (boolean-valued function) of a Map representing a person,
 * meant for use in filtering a list of persons.
 * Capable of dealing with arbitrary fields.
 *
 * <p>Adapted from {@link java.util.function.Predicate}, which could not be used because
 * it does not support returning information other than a boolean.
 */
abstract class SearchMatcher {

    enum Flag {
        CASE_SENSITIVITY,
        FULL_WORD_MATCHING_ONLY
    }

    private Map<Flag, Boolean> flags;

    SearchMatcher() {
        initFlags();
    }

    /**
     * Evaluates this predicate on the given argument,
     * which is expected to be a Map representation of a {@link seedu.address.model.person.Person}.
     *
     * @param p the person's attributes, as a Map.
     * @return {@link FieldRanges} of all {@code null} values if no match,
     *      otherwise contains at least one {@link Range} that specifies the location(s) of the match.
     */
    abstract FieldRanges test(Map<String, String> p);

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
    SearchMatcher and(SearchMatcher other) {
        Objects.requireNonNull(other);
        return new BinarySearchMatcher(this, other, '&') {
            @Override
            FieldRanges test(Map<String, String> p) {
                FieldRanges rangeA = a.test(p);
                if (!FieldRanges.isMatch(rangeA)) {
                    return null;
                }
                FieldRanges rangeB = b.test(p);
                if (!FieldRanges.isMatch(rangeB)) {
                    return null;
                }
                return FieldRanges.union(rangeA, rangeB);
            }
        };
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate.
     */
    SearchMatcher negate() {
        SearchMatcher old = this;
        return new SearchMatcher() {
            @Override
            FieldRanges test(Map<String, String> p) {
                FieldRanges ranges = old.test(p);
                if (!FieldRanges.isMatch(ranges)) {
                    ranges = new FieldRanges();
                    ranges.setIsMatch(true);
                } else {
                    ranges = null;
                }
                return ranges;
            }
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
    SearchMatcher or(SearchMatcher other) {
        Objects.requireNonNull(other);
        return new BinarySearchMatcher(this, other, '|') {
            @Override
            FieldRanges test(Map<String, String> p) {
                return FieldRanges.union(a.test(p), b.test(p));
            }
        };
    }

    protected void initFlags() {
        flags = new HashMap<>();
        Arrays.stream(Flag.values()).forEach(flag -> flags.put(flag, false));
    }

    void setFlag(Flag flag, boolean isFlagApplied) {
        flags.put(flag, isFlagApplied);
    }

    boolean isFlagApplied(Flag flag) {
        return flags.get(flag);
    }

}
