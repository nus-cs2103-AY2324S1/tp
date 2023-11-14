package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.customer.Budget;


public class PriceTest {
    private static final float OTHER_TYPE_INVALID_PRICE = 5.0f;
    private static final String NOT_INTEGER_INVALID_PRICE = "one million";
    private static final String ONE_DIGIT_INVALID_PRICE = "1";
    private static final String TWO_DIGIT_INVALID_PRICE = "11";
    private static final String THREE_DIGIT_INVALID_PRICE = "111";
    private static final String FOUR_DIGIT_INVALID_PRICE = "1111";
    private static final String FIVE_DIGIT_INVALID_PRICE = "00000";
    private static final String DECIMAL_INVALID_PRICE = "12.345";
    private static final String WHITESPACE_INVALID_PRICE = "12 345";
    private static final String THIRTEEN_DIGIT_INVALID_PRICE = "1234567891011";
    private static final String ONE_TRILLION_INVALID_PRICE = "1000000000000";
    private static final String START_WITH_ZERO_INVALID_PRICE = "0123456";
    private static final String VALID_PRICE = "100000";
    private static final String INTEGER_VALID_PRICE = Integer.toString(Integer.MAX_VALUE);
    private static final String TWELVE_DIGIT_VALID_PRICE = "123456789101";
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid prices
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice(NOT_INTEGER_INVALID_PRICE)); // not an integer
        assertFalse(Price.isValidPrice(ONE_DIGIT_INVALID_PRICE)); // one digit only
        assertFalse(Price.isValidPrice(TWO_DIGIT_INVALID_PRICE)); // two digit only
        assertFalse(Price.isValidPrice(THREE_DIGIT_INVALID_PRICE)); // three digit only
        assertFalse(Price.isValidPrice(FOUR_DIGIT_INVALID_PRICE)); // four digit only
        assertFalse(Price.isValidPrice(FIVE_DIGIT_INVALID_PRICE));
        assertFalse(Price.isValidPrice(DECIMAL_INVALID_PRICE));
        assertFalse(Price.isValidPrice(WHITESPACE_INVALID_PRICE));
        assertFalse(Price.isValidPrice(THIRTEEN_DIGIT_INVALID_PRICE)); // thirteen digit price
        assertFalse(Price.isValidPrice(ONE_TRILLION_INVALID_PRICE)); // one trillion
        assertFalse(Price.isValidPrice(START_WITH_ZERO_INVALID_PRICE)); // starts with zero

        // valid prices
        assertTrue(Price.isValidPrice(VALID_PRICE));
        assertTrue(Price.isValidPrice(INTEGER_VALID_PRICE)); // long price
        assertTrue(Price.isValidPrice(TWELVE_DIGIT_VALID_PRICE)); // twelve digit price
    }

    @Test
    public void isInRangeBudget() {
        // null price
        assertTrue(new Price(VALID_PRICE).isInRangePrice(null));

        // smaller price
        assertFalse(new Price(INTEGER_VALID_PRICE).isInRangePrice(new Price(VALID_PRICE)));

        // same price
        assertTrue(new Price(VALID_PRICE).isInRangePrice(new Price(VALID_PRICE)));

        // bigger price
        assertTrue(new Price(VALID_PRICE).isInRangePrice(new Price(INTEGER_VALID_PRICE)));
    }

    @Test
    public void equals() {
        Price price = new Price(VALID_PRICE);

        // same values -> returns true
        assertTrue(price.equals(new Price(VALID_PRICE)));

        // same object -> returns true
        assertTrue(price.equals(price));

        // null -> returns false
        assertFalse(price.equals(null));

        // different types -> returns false
        assertFalse(price.equals(OTHER_TYPE_INVALID_PRICE));

        // different values -> returns false
        assertFalse(price.equals(new Price(INTEGER_VALID_PRICE)));
    }

    @Test
    public void convertToBudget_validPrice() {
        Price price = new Price(VALID_PRICE);
        Budget budget = new Budget(VALID_PRICE);
        assertEquals(price.convertToBudget(), budget);
    }

    @Test
    public void hashCode_validPrice() {
        Price price1 = new Price(VALID_PRICE);
        Price price2 = new Price(VALID_PRICE);
        assertNotSame(price1, price2);
        assertEquals(price1.hashCode(), price2.hashCode());
    }
}
