package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }
    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));
        // amount is not a number
        assertFalse(Amount.isValidAmount("This is words"));
        // amount is more than 2dp
        assertFalse(Amount.isValidAmount("100.001"));
        assertFalse(Amount.isValidAmount("100.000"));
        //amount is negative
        assertFalse(Amount.isValidAmount("-100"));
        assertFalse(Amount.isValidAmount("-100.00"));
        // valid amounts
        assertTrue(Amount.isValidAmount("100.01"));
        assertTrue(Amount.isValidAmount("100.0"));
        assertTrue(Amount.isValidAmount("100"));
    }
    @Test
    public void equals() {
        Amount amount = new Amount("80");

        // same object -> returns true
        assertTrue(amount.equals(amount));

        // same values -> returns true
        assertTrue(amount.equals(new Amount("80")));

        // null -> returns false
        assertFalse(amount.equals(null));

        // different types -> returns false
        assertFalse(amount.equals(5.0f));

        // different values -> returns false
        assertFalse(amount.equals(new Amount("100")));
    }
}
