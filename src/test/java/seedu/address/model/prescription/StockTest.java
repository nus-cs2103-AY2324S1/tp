package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StockTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Stock(null));
    }

    @Test
    public void constructor_invalidStock_throwsIllegalArgumentException() {
        String invalidStock = "";
        assertThrows(IllegalArgumentException.class, () -> new Stock(invalidStock));
    }

    @Test
    public void isValidStock() {
        // null stock
        assertThrows(NullPointerException.class, () -> Stock.isValidStockFormat(null));

        // invalid stock
        assertFalse(Stock.isValidStockFormat("")); // empty string
        assertFalse(Stock.isValidStockFormat(" ")); // spaces only
        assertFalse(Stock.isValidStockFormat("^")); // non-alphanumeric characters
        assertFalse(Stock.isValidStockFormat("a")); // alphabets

        // valid stock
        assertTrue(Stock.isValidStockFormat("1")); // single digit
        assertTrue(Stock.isValidStockFormat("12")); // double digit
        assertTrue(Stock.isValidStockFormat("02")); // trailing zero
    }

    @Test
    public void equals() {
        Stock stock = new Stock("100");

        // same values -> returns true
        assertTrue(stock.equals(new Stock("100")));

        // same object -> returns true
        assertTrue(stock.equals(stock));

        // null -> returns false
        assertFalse(stock.equals(null));

        // different types -> returns false
        assertFalse(stock.equals(5.0f));

        // different values -> returns false
        assertFalse(stock.equals(new Stock("200")));
    }
}
