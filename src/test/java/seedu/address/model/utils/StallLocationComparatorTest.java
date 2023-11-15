package seedu.address.model.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.stall.Stall;
import seedu.address.model.util.StallLocationComparator;
import seedu.address.testutil.StallBuilder;

public class StallLocationComparatorTest {

    @Test
    public void execute_stall1HigherThanStall2compare_success() {
        StallLocationComparator comparator = new StallLocationComparator();
        Stall stall1 = new StallBuilder().withLocation("aaaa").build();
        Stall stall2 = new StallBuilder().withLocation("bbbb").build();
        assertTrue(comparator.compare(stall1, stall2) < 0);
    }

    @Test
    public void execute_stall1LowerThanStall2compare_success() {
        StallLocationComparator comparator = new StallLocationComparator();
        Stall stall1 = new StallBuilder().withLocation("bbbb").build();
        Stall stall2 = new StallBuilder().withLocation("aaaa").build();
        assertTrue(comparator.compare(stall1, stall2) > 0);
    }

    @Test
    public void execute_stall1LocationEqualStall2Locationcompare_success() {
        StallLocationComparator comparator = new StallLocationComparator();
        Stall stall1 = new StallBuilder().withLocation("aaaa").build();
        Stall stall2 = new StallBuilder().withLocation("aaaa").build();
        assertTrue(comparator.compare(stall1, stall2) == 0);
    }

}
