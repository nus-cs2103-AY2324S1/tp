package seedu.address.logic.search;

import java.util.Map;

/**
 * Represents the combination of two other SearchMatchers in a logical "and" manner.
 * Evaluates lazily.
 */
public class AndSearchMatcher extends BinarySearchMatcher {

    private static final char SYMBOL = '&';

    AndSearchMatcher(SearchMatcher a, SearchMatcher b) {
        super(a, b, SYMBOL);
    }

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
}
