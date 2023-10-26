package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeductionTest {

    private final Deduction deduction = new Deduction("1500.00", Reason.NO_PAY_LEAVE);
    private final Deduction deduction2 = new Deduction("1500.00", Reason.ABSENCE);
    private final Deduction deduction3 = new Deduction("1500.00", Reason.EMPLOYEE_CPF_DEDUCTION);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deduction(null, null));
        assertThrows(NullPointerException.class, () -> new Deduction(null, Reason.NO_PAY_LEAVE));
        assertThrows(NullPointerException.class, () -> new Deduction("1500.00", null));
    }

    @Test
    public void constructor_invalidReason_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Deduction("1500.00", Reason.ANNUAL_BONUS));
        assertThrows(IllegalArgumentException.class, () -> new Deduction("1500.00", Reason.TRANSPORT_ALLOWANCE));
    }

    @Test
    public void constructor_invalidDeduction_throwsIllegalArgumentException() {
        String invalidDeduction = "1500";
        assertThrows(IllegalArgumentException.class, () -> new Deduction(invalidDeduction, Reason.NO_PAY_LEAVE));
    }

    @Test
    public void isValidDeduction() {
        // null deduction
        assertThrows(NullPointerException.class, () -> Deduction.isValid(null));

        // valid deduction
        assertTrue(deduction.isValid());
        assertTrue(deduction2.isValid());
        assertTrue(deduction3.isValid());

        // invalid deduction
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("", Reason.ABSENCE).isValid()); // empty value
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction(" ", Reason.ABSENCE).isValid()); // space only
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("*", Reason.ABSENCE).isValid()); // only non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("8+1500.00", Reason.ABSENCE).isValid()); // contain non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("1500", Reason.ABSENCE).isValid()); // no decimal point
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("1500.0", Reason.ABSENCE).isValid()); // one decimal point
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("1500.000", Reason.ABSENCE).isValid()); // three decimal points
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("1500.00", Reason.ANNUAL_BONUS).isValid()); // invalid reason
        assertThrows(IllegalArgumentException.class, () ->
            new Deduction("1500.00", Reason.TRANSPORT_ALLOWANCE).isValid()); // invalid reason
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(deduction.equals(new Deduction("1500.00", Reason.NO_PAY_LEAVE)));
        assertTrue(deduction2.equals(new Deduction("1500.00", Reason.ABSENCE)));
        assertTrue(deduction3.equals(new Deduction("1500.00", Reason.EMPLOYEE_CPF_DEDUCTION)));

        // same object -> returns true
        assertTrue(deduction.equals(deduction));
        assertTrue(deduction2.equals(deduction2));
        assertTrue(deduction3.equals(deduction3));

        // null -> returns false
        assertFalse(deduction.equals(null));

        // different types -> returns false
        assertFalse(deduction.equals(5.0f));

        // different values -> returns false
        assertFalse(deduction.equals(new Deduction("1500.01", Reason.NO_PAY_LEAVE)));

        // different reasons -> returns false
        assertFalse(deduction.equals(new Deduction("1500.00", Reason.ABSENCE)));
    }
}
