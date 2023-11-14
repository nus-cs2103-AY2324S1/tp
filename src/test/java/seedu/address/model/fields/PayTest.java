package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PayTest {
    private final String validPay1 = "10000.00";
    private final String validPay2 = "9999999999999999999.999";
    private final String validPay3 = "0.00";
    private final String validPay4 = "1200.504";

    private final String invalidPay1 = "-1200.00";
    private final String invalidPay2 = ".00";

    @Test
    public void isValidPayTest() {
        assertTrue(Pay.isValidPay(validPay1));
        assertTrue(Pay.isValidPay(validPay2));
        assertTrue(Pay.isValidPay(validPay3));

        assertFalse(Pay.isValidPay(invalidPay1));
        assertFalse(Pay.isValidPay(invalidPay2));
    }

    @Test
    public void equals() {
        Pay pay = new Pay(new BigDecimal(validPay1));

        // same values -> returns true
        assertEquals(pay, new Pay(new BigDecimal(validPay1)));

        // same object -> returns true
        assertEquals(pay, pay);

        // null -> returns false
        assertNotEquals(null, pay);

        // different types -> returns false
        assertFalse(pay.equals(5.0f));

        // different values -> returns false
        assertNotEquals(pay, new Pay(new BigDecimal(validPay2)));
    }

}
