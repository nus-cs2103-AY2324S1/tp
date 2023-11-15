package seedu.address.model.displayable.seller;

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
import static seedu.address.testutil.TypicalSellers.SALICE;
import static seedu.address.testutil.TypicalSellers.SBOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.displayable.SortOrder;

public class SellerComparatorTest {

    @Test
    public void of_validInputs_success() {
        assertNotNull(SellerComparator.of(PREFIX_NAME, new SortOrder("a")));
        assertNotNull(SellerComparator.of(PREFIX_NAME, new SortOrder("d")));

        assertNotNull(SellerComparator.of(PREFIX_ADDRESS, new SortOrder("a")));
        assertNotNull(SellerComparator.of(PREFIX_ADDRESS, new SortOrder("d")));

        assertNotNull(SellerComparator.of(PREFIX_HOUSE_INFO, new SortOrder("a")));
        assertNotNull(SellerComparator.of(PREFIX_HOUSE_INFO, new SortOrder("d")));

        assertNotNull(SellerComparator.of(PREFIX_PRIORITY, new SortOrder("a")));
        assertNotNull(SellerComparator.of(PREFIX_PRIORITY, new SortOrder("d")));

        assertNull(SellerComparator.of());
    }

    @Test
    public void of_invalidInputs_exceptionThrown() {
        // null prefix
        assertThrows(NullPointerException.class, () -> SellerComparator.of(null, new SortOrder("a")));

        // null sort order
        assertThrows(NullPointerException.class, () -> SellerComparator.of(PREFIX_NAME, null));

        // unsupported prefix
        assertThrows(IllegalArgumentException.class, () -> SellerComparator.of(PREFIX_EMAIL, new SortOrder("a")));
    }

    @Test
    public void compare() {
        SellerComparator ascendingNameComparator = SellerComparator.of(PREFIX_NAME, new SortOrder("a"));
        assertNotNull(ascendingNameComparator);

        // same seller
        assertEquals(ascendingNameComparator.compare(SALICE, SALICE), 0);

        // different sellers
        assertNotEquals(ascendingNameComparator.compare(SALICE, SBOB), 0);
    }
}
