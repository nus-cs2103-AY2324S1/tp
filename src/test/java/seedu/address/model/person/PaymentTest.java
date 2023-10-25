package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PaymentTest {

    private final Payment payment = new Payment("1500.00");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Payment(null));
    }

    @Test
    public void constructor_invalidPayment_throwsIllegalArgumentException() {
        String invalidPayment = "";
        assertThrows(IllegalArgumentException.class, () -> new Payment(invalidPayment));
    }

    @Test
    public void isValidPayment() {
        // null payment
        assertThrows(NullPointerException.class, () -> Payment.isValid(null));

        // valid payment
        assertTrue(Payment.isValid("0.00"));
        assertTrue(Payment.isValid("10.00"));
        assertTrue(Payment.isValid("100.00"));
        assertTrue(Payment.isValid("10000.00"));
        assertTrue(Payment.isValid("100000.00"));

        // invalid payment
        assertFalse(Payment.isValid("")); // empty value
        assertFalse(Payment.isValid(" ")); // space only
        assertFalse(Payment.isValid("*")); // only non-alphanumeric characters
        assertFalse(Payment.isValid("8+1500.00")); // contain non-alphanumeric characters
        assertFalse(Payment.isValid("1500")); // no decimal point
        assertFalse(Payment.isValid("1500.0")); // one decimal point
        assertFalse(Payment.isValid("1500.000")); // three decimal points
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(payment.equals(new Payment("1500.00")));

        // same object -> returns true
        assertTrue(payment.equals(payment));

        // null -> returns false
        assertFalse(payment.equals(null));

        // different types -> returns false
        assertFalse(payment.equals(5.0f));

        // different values -> returns false
        assertFalse(payment.equals(new Payment("1500.01")));
    }
}
