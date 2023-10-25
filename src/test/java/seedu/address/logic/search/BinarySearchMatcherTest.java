package seedu.address.logic.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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