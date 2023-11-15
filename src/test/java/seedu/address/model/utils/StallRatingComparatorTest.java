package seedu.address.model.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStalls.DUCK_RICE;
import static seedu.address.testutil.TypicalStalls.NOODLES;

import org.junit.jupiter.api.Test;

import seedu.address.model.stall.Stall;
import seedu.address.model.util.StallRatingComparator;
import seedu.address.testutil.StallBuilder;

public class StallRatingComparatorTest {

    @Test
    public void execute_stall1HigherThanStall2compare_success() {
        StallRatingComparator comparator = new StallRatingComparator();
        Stall stall1 = new StallBuilder(DUCK_RICE).build();
        Stall stall2 = new StallBuilder(NOODLES).build();
        assertTrue(comparator.compare(stall2, stall1) > 0);
    }

    @Test
    public void execute_stall1LowerThanStall2compare_success() {
        StallRatingComparator comparator = new StallRatingComparator();
        Stall stall1 = new StallBuilder(NOODLES).build();
        Stall stall2 = new StallBuilder(DUCK_RICE).build();
        assertTrue(comparator.compare(stall2, stall1) < 0);
    }

    @Test
    public void execute_stall1EqualToStall2compare_success() {
        StallRatingComparator comparator = new StallRatingComparator();
        Stall stall1 = new StallBuilder(NOODLES).build();
        Stall stall2 = new StallBuilder(NOODLES).build();
        assertTrue(comparator.compare(stall1, stall2) == 0);
    }

    @Test
    public void execute_stall1Nullcompare_success() {
        StallRatingComparator comparator = new StallRatingComparator();
        Stall stall1 = new StallBuilder().build();
        Stall stall2 = new StallBuilder(NOODLES).build();
        assertTrue(comparator.compare(stall1, stall2) > 0);
    }

    @Test
    public void execute_stall1And2Nullcompare_success() {
        StallRatingComparator comparator = new StallRatingComparator();
        Stall stall1 = new StallBuilder().build();
        Stall stall2 = new StallBuilder().build();
        assertTrue(comparator.compare(stall1, stall2) == 0);
    }

}
