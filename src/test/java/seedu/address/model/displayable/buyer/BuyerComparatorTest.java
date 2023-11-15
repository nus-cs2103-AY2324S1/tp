package seedu.address.model.displayable.buyer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.displayable.SortOrder;

public class BuyerComparatorTest {

    @Test
    public void of_validInputs_success() {
        assertNotNull(BuyerComparator.of(PREFIX_NAME, new SortOrder("a")));
        assertNotNull(BuyerComparator.of(PREFIX_NAME, new SortOrder("d")));

        assertNotNull(BuyerComparator.of(PREFIX_ADDRESS, new SortOrder("a")));
        assertNotNull(BuyerComparator.of(PREFIX_ADDRESS, new SortOrder("d")));

        assertNotNull(BuyerComparator.of(PREFIX_HOUSE_INFO, new SortOrder("a")));
        assertNotNull(BuyerComparator.of(PREFIX_HOUSE_INFO, new SortOrder("d")));

        assertNotNull(BuyerComparator.of(PREFIX_PRIORITY, new SortOrder("a")));
        assertNotNull(BuyerComparator.of(PREFIX_PRIORITY, new SortOrder("d")));

        assertNull(BuyerComparator.of());
    }

    @Test
    public void of_invalidInputs_exceptionThrown() {
        // null prefix
        assertThrows(NullPointerException.class, () -> BuyerComparator.of(null, new SortOrder("a")));

        // null sort order
        assertThrows(NullPointerException.class, () -> BuyerComparator.of(PREFIX_NAME, null));

        // unsupported prefix
        assertThrows(IllegalArgumentException.class, () -> BuyerComparator.of(PREFIX_EMAIL, new SortOrder("a")));
    }

    @Test
    public void compare() {
        BuyerComparator ascendingNameComparator = BuyerComparator.of(PREFIX_NAME, new SortOrder("a"));
        assertNotNull(ascendingNameComparator);

        // same buyer
        assertEquals(ascendingNameComparator.compare(ALICE, ALICE), 0);

        // different buyers
        assertNotEquals(ascendingNameComparator.compare(ALICE, BOB), 0);
    }
}
