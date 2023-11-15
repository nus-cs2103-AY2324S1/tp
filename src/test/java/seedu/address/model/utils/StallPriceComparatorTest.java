package seedu.address.model.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMenus.VALID_MENU_3;
import static seedu.address.testutil.TypicalMenus.VALID_MENU_4;

import org.junit.jupiter.api.Test;

import seedu.address.model.stall.Stall;
import seedu.address.model.util.StallPriceComparator;
import seedu.address.testutil.StallBuilder;

public class StallPriceComparatorTest {

    @Test
    public void execute_stall1HigherThanStall2compare_success() {
        StallPriceComparator comparator = new StallPriceComparator();
        Stall stall1 = new StallBuilder().withMenu(VALID_MENU_3).build();
        Stall stall2 = new StallBuilder().withMenu(VALID_MENU_4).build();
        assertTrue(comparator.compare(stall1, stall2) < 0);
    }

    @Test
    public void execute_stall1LowerThanStall2compare_success() {
        StallPriceComparator comparator = new StallPriceComparator();
        Stall stall1 = new StallBuilder().withMenu(VALID_MENU_4).build();
        Stall stall2 = new StallBuilder().withMenu(VALID_MENU_3).build();
        assertTrue(comparator.compare(stall1, stall2) > 0);
    }

    @Test
    public void execute_stall1PriceEqualStall2Pricecompare_success() {
        StallPriceComparator comparator = new StallPriceComparator();
        Stall stall1 = new StallBuilder().withMenu(VALID_MENU_3).build();
        Stall stall2 = new StallBuilder().withMenu(VALID_MENU_3).build();
        assertTrue(comparator.compare(stall1, stall2) == 0);
    }

    @Test
    public void execute_stall1Nullcompare_success() {
        StallPriceComparator comparator = new StallPriceComparator();
        Stall stall1 = new StallBuilder().build();
        Stall stall2 = new StallBuilder().withMenu(VALID_MENU_3).build();
        assertTrue(comparator.compare(stall1, stall2) > 0);
    }

    @Test
    public void execute_stall1And2Nullcompare_success() {
        StallPriceComparator comparator = new StallPriceComparator();
        Stall stall1 = new StallBuilder().build();
        Stall stall2 = new StallBuilder().build();
        assertTrue(comparator.compare(stall1, stall2) == 0);
    }
}
