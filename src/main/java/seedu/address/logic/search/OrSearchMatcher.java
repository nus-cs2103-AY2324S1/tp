package seedu.address.logic.search;

import java.util.Map;

/**
 * Represents the combination of two other SearchMatchers in a logical "or" manner.
 * Does not evaluate lazily.
 */
public class OrSearchMatcher extends BinarySearchMatcher {

    private static final char SYMBOL = '/';

    OrSearchMatcher(SearchMatcher a, SearchMatcher b) {
        super(a, b, SYMBOL);
    }

    @Override
    FieldRanges test(Map<String, String> p) {
        return FieldRanges.union(a.test(p), b.test(p));
    }
}
