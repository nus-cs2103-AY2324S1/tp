package seedu.address.logic.search;

import java.util.Map;

public class NotSearchMatcher extends SearchMatcher {

    private final SearchMatcher prev;

    NotSearchMatcher(SearchMatcher prev) {
        this.prev = prev;
    }

    @Override
    FieldRanges test(Map<String, String> p) {
        FieldRanges ranges = prev.test(p);
        if (!FieldRanges.isMatch(ranges)) {
            ranges = new FieldRanges();
            ranges.setIsMatch(true);
        } else {
            ranges = null;
        }
        return ranges;
    }
}
