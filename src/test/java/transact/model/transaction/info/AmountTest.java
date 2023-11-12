package transact.model.transaction.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class AmountTest {

    // Test Construction with Valid BigDecimal
    @Test
    public void testConstructionWithValidBigDecimal() {
        BigDecimal input = new BigDecimal("10.00");
        Amount amount = new Amount(input);
        assertEquals(input, amount.getValue());
    }

    // Test Construction with Invalid BigDecimal (Null or Negative)
    @Test
    public void testConstructionWithInvalidBigDecimal() {
        BigDecimal input = new BigDecimal("-1.00");
        assertThrows(IllegalArgumentException.class, () -> new Amount(input));
    }

    // Test Construction with Valid Double
    @Test
    public void testConstructionWithValidDouble() {
        double input = 10.00;
        Amount amount = new Amount(input);
        assertNotNull(amount);
    }

    // Test Construction with Invalid Double (Negative)
    @Test
    public void testConstructionWithInvalidDouble() {
        double input = -10.00;
        assertThrows(IllegalArgumentException.class, () -> new Amount(input));
    }

    // Test getValue Method
    @Test
    public void testGetValueMethod() {
        BigDecimal input = new BigDecimal("10.00");
        Amount amount = new Amount(input);
        assertEquals(true, input.equals(amount.getValue()));
    }

    // Test getFormattedValue Method
    @Test
    public void testGetFormattedValueMethod() {
        Amount amount = new Amount(new BigDecimal("10.000001"));
        assertEquals("10.00", amount.getFormattedValue());
    }

    // Test toString Method
    @Test
    public void testToStringMethod() {
        Amount amount = new Amount(new BigDecimal("10.000001"));
        assertEquals("10.00", amount.toString());
    }

    // Test equals Method
    @Test
    public void testEqualsMethod() {
        Amount amount1 = new Amount(new BigDecimal("10.00"));
        Amount amount2 = new Amount(new BigDecimal("10.00"));
        Amount amount3 = new Amount(new BigDecimal("20.00"));
        assertTrue(amount1.equals(amount2));
        assertFalse(amount1.equals(amount3));
    }

    // Test hashCode Method
    @Test
    public void testHashCodeMethod() {
        Amount amount1 = new Amount(new BigDecimal("10.00"));
        Amount amount2 = new Amount(new BigDecimal("10.00"));
        assertEquals(amount1.hashCode(), amount2.hashCode());
    }

    // Test isValidAmount Method with Valid String
    @Test
    public void testIsValidAmountWithValidString() {
        assertTrue(Amount.isValidAmount("10.00"));
    }

    // Test isValidAmount Method with Invalid String
    @Test
    public void testIsValidAmountWithInvalidString() {
        assertFalse(Amount.isValidAmount("-10.00"));
        assertFalse(Amount.isValidAmount("abc"));
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));
    }
}
