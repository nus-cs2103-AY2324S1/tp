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
}
