package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BenefitTest {

    private final Benefit benefit = new Benefit("5000.00", Reason.ANNUAL_BONUS);
    private final Benefit benefit2 = new Benefit("500.00", Reason.TRANSPORT_ALLOWANCE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Benefit(null, null));
        assertThrows(NullPointerException.class, () -> new Benefit(null, Reason.ANNUAL_BONUS));
        assertThrows(NullPointerException.class, () -> new Benefit("5000.00", null));
    }

    @Test
    public void constructor_invalidReason_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Benefit("1500.00", Reason.NO_PAY_LEAVE));
        assertThrows(IllegalArgumentException.class, () -> new Benefit("1500.00", Reason.ABSENCE));
        assertThrows(IllegalArgumentException.class, () -> new Benefit("1500.00", Reason.EMPLOYEE_CPF_DEDUCTION));
    }

    @Test
    public void constructor_invalidBenefit_throwsIllegalArgumentException() {
        String invalidBenefit = "5000";
        assertThrows(IllegalArgumentException.class, () -> new Benefit(invalidBenefit, Reason.ANNUAL_BONUS));
    }

    @Test
    public void isValidBenefit() {
        // null benefit
        assertThrows(NullPointerException.class, () -> Benefit.isValid(null));

        // valid benefit
        assertTrue(benefit.isValid());
        assertTrue(benefit2.isValid());

        // invalid benefit
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("", Reason.ANNUAL_BONUS).isValid()); // empty value
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit(" ", Reason.ANNUAL_BONUS).isValid()); // space only
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("*", Reason.ANNUAL_BONUS).isValid()); // only non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("8+1500.00", Reason.ANNUAL_BONUS).isValid()); // contain non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("5000", Reason.ANNUAL_BONUS).isValid()); // no decimal point
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("5000.0", Reason.ANNUAL_BONUS).isValid()); // one decimal point
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("5000.000", Reason.ANNUAL_BONUS).isValid()); // three decimal points
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("5000.00", Reason.NO_PAY_LEAVE).isValid()); // invalid reason
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("5000.00", Reason.ABSENCE).isValid()); // invalid reason
        assertThrows(IllegalArgumentException.class, () ->
            new Benefit("5000.00", Reason.EMPLOYEE_CPF_DEDUCTION).isValid()); // invalid reason
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(benefit.equals(new Benefit("5000.00", Reason.ANNUAL_BONUS)));
        assertTrue(benefit2.equals(new Benefit("500.00", Reason.TRANSPORT_ALLOWANCE)));

        // same object -> returns true
        assertTrue(benefit.equals(benefit));
        assertTrue(benefit2.equals(benefit2));

        // null -> returns false
        assertFalse(benefit.equals(null));

        // different types -> returns false
        assertFalse(benefit.equals(5.0f));

        // different values -> returns false
        assertFalse(benefit.equals(new Benefit("5000.01", Reason.ANNUAL_BONUS)));

        // different reasons -> returns false
        assertFalse(benefit.equals(new Benefit("5000.00", Reason.TRANSPORT_ALLOWANCE)));
    }
}
