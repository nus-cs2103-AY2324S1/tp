package seedu.address.logic.search;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BinarySearchMatcherTest {

    @Test
    public void test_setFlag() {
        SingleTextSearchMatcher a = new SingleTextSearchMatcher(null);
        SingleTextSearchMatcher b = new SingleTextSearchMatcher(null);
        assertFalse(a.isFlagApplied(SearchMatcher.Flag.CASE_SENSITIVITY));
        assertFalse(b.isFlagApplied(SearchMatcher.Flag.CASE_SENSITIVITY));
        a.and(b).setFlag(SearchMatcher.Flag.CASE_SENSITIVITY, true);
        assertTrue(a.isFlagApplied(SearchMatcher.Flag.CASE_SENSITIVITY));
        assertTrue(b.isFlagApplied(SearchMatcher.Flag.CASE_SENSITIVITY));
    }

}
