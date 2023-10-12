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
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Map)}.
 *
 * <p>Adapted with minimal changes from {@link java.util.function.Predicate}.
 */
@FunctionalInterface
public abstract interface SearchPredicate{

    /**
     * Attached to a String representation of the value of a field,
     * represents the start and end of a SearchPredicate match on that field.
     * Intended for use by {@link #test(Map)}, to enable certain features such as {@link OrderedSearchPredicate}
     * or enabling/disabling overlaps.
     */
    class Range {
        private int start;
        private int end;

        private Range(int start, int end) throws InstantiationException {
            if (start >= end) {
                throw new InstantiationException("Range parameters illegal");
            }
            this.start = start;
            this.end = end;
        }

        static boolean isRangeOverlap(Range a, Range b) {
            if (a == null || b == null) {
                return false;
            }
            return isBefore(a,b) || isAfter(a,b);
        }

        static boolean isBefore(Range a, Range b) {
            if (a == null || b == null) {
                return true;
            }
            return a.end < b.start;
        }

        static boolean isAfter(Range a, Range b) {
            if (a == null || b == null) {
                return true;
            }
            return a.start > b.end;
        }

        static Range union(Range a, Range b) {
            if (a == null) {
                return b;
            } else if (b == null) {
                return a;
            }
            Range range = null;
            try {
                range = new Range(min(a.start, b.start), max(a.end, b.end));
            } catch (InstantiationException wontHappenTrust) { //assuming two valid range objects
                ;
            }
            return range;
        }
    }

    /**
     * Used to represent a {@link SearchPredicate} match,
     * determines the fields and corresponding sections of the fields where a match occurs.
     */
    class FieldRanges extends HashMap<String, Range> {

        private boolean isMatch = false;

        public FieldRanges(Map<String, String> person) {
            if (person == null) {
                return;
            }
            for (String key : person.keySet()) {
                this.put(key, null);
            }
        }

        @Override
        public Range put(String key, Range value) {
            if (value == null) {
                return null;
            }
            isMatch = true;
            return super.put(key, value);
        }

        public void setIsMatch(boolean isMatch) {
            this.isMatch = isMatch;
        }

        public static FieldRanges union(FieldRanges a, FieldRanges b) {
            FieldRanges ranges = new FieldRanges(null);
            for (String key : a.keySet()) {
                ranges.put(key, Range.union(a.get(key), b.get(key)));
            }
            return ranges;
        }

        public static boolean isMatch(FieldRanges ranges) {
            if (ranges == null) {
                return false;
            }
            return ranges.isMatch;
        }
    }

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
     * predicate
     */
    default SearchPredicate negate() {
        return (person) -> {
            FieldRanges ranges = this.test(person);
            if (!FieldRanges.isMatch(ranges)) {
                ranges = new FieldRanges(person);
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

}
