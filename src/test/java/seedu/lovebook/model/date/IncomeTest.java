package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class IncomeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Income(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidAddress));
    }

    @Test
    public void isValidIncome() {
        // null lovebook
        assertThrows(NullPointerException.class, () -> Income.isValidIncome(null));

        // invalid addresses
        assertFalse(Income.isValidIncome("")); // empty string
        assertFalse(Income.isValidIncome(" ")); // spaces only
        assertFalse(Income.isValidIncome("ABCD"));
        assertFalse(Income.isValidIncome("-")); // one character
        assertFalse(Income.isValidIncome("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));

        // valid addresses
        assertTrue(Income.isValidIncome("123"));
        assertTrue(Income.isValidIncome("15000"));
    }

    @Test
    public void equals() {
        Income income = new Income("20");

        // same values -> returns true
        assertTrue(income.equals(new Income("20")));

        // same object -> returns true
        assertTrue(income.equals(income));

        // null -> returns false
        assertFalse(income.equals(null));

        // different types -> returns false
        assertFalse(income.equals(5.0f));

        // different values -> returns false
        assertFalse(income.equals(new Income("30")));
    }

    @Test
    void testToString() {
        Income income = new Income("20");

        assertTrue(income.toString().equals("20"));

        assertFalse(income.toString().equals("30"));
    }

    @Test
    void testHashCode() {
        Income income = new Income("20");

        assertTrue(income.hashCode() == new Income("20").hashCode());

        assertFalse(income.hashCode() == new Income("30").hashCode());
    }

    @Test
    void compareTo() {

        Income income = new Income("20");

        // same values -> returns true
        assertTrue(income.compareTo(new Income("20")) == 0);

        // 20 < 30
        assertTrue(income.compareTo(new Income("30")) < 0);

        // 20 > 10
        assertTrue(income.compareTo(new Income("10")) > 0);

    }

}
