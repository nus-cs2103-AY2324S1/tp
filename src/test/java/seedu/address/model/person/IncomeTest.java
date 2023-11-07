package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IncomeTest {

    // EP: valid income (integers)
    @Test
    public void isValidIncome_validIncome_returnsTrue() {
        assertTrue(Income.isValidIncome("60000"));
        assertTrue(Income.isValidIncome("100"));
        assertTrue(Income.isValidIncome("0"));
        assertTrue(Income.isValidIncome("1"));
        assertTrue(Income.isValidIncome("2147483647"));
    }

    // EP: null input
    @Test
    public void isValidIncome_nullInput_returnsFalse() {
        assertFalse(Income.isValidIncome(null));
    }

    // EP: empty string
    @Test
    public void isValidIncome_emptyInput_returnsFalse() {
        assertFalse(Income.isValidIncome(("")));
        assertFalse(Income.isValidIncome((" ")));
        assertFalse(Income.isValidIncome(("       ")));
    }

    // EP: invalid inputs (non-integers)
    @Test
    public void isValidIncome_invalidInput_returnsFalse() {
        assertFalse(Income.isValidIncome(("100 USD")));
        assertFalse(Income.isValidIncome(("123.50")));
        assertFalse(Income.isValidIncome(("$60000")));
    }

    // EP: negative integers
    @Test
    public void isValidIncome_negativeInput_returnsFalse() {
        assertFalse(Income.isValidIncome("-1"));
        assertFalse(Income.isValidIncome("-8923"));
    }

    // EP: integers larger than MAX_INT
    @Test
    public void isValidIncome_largeInput_returnsFalse() {
        assertFalse(Income.isValidIncome("2147483648"));
        assertFalse(Income.isValidIncome("100000000000000"));
    }
}
