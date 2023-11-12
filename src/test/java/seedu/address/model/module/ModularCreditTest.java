package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModularCreditTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModularCredit(null));
    }

    @Test
    public void constructor_invalidModularCredit_throwsIllegalArgumentException() {
        String invalidModularCreditEmpty = "";
        assertThrows(IllegalArgumentException.class, () -> new ModularCredit(invalidModularCreditEmpty));

        String invalidModularCreditLetters = "one";
        assertThrows(IllegalArgumentException.class, () -> new ModularCredit(invalidModularCreditLetters));
    }

    @Test
    public void isValidModularCredit_invalid() {
        // null modular credit
        assertThrows(NullPointerException.class, () -> ModularCredit.isValidModularCredit(null));

        // invalid modular credit
        assertFalse(ModularCredit.isValidModularCredit("")); // empty string
        assertFalse(ModularCredit.isValidModularCredit(" ")); // spaces only
        assertFalse(ModularCredit.isValidModularCredit("-1")); // negative
        assertFalse(ModularCredit.isValidModularCredit("1..2")); // double decimal
        assertFalse(ModularCredit.isValidModularCredit("one")); // letters
        assertFalse(ModularCredit.isValidModularCredit("1.")); // not a valid float format
    }

    @Test
    public void isValidModularCredit_valid() {
        // valid modular credit
        assertTrue(ModularCredit.isValidModularCredit(".5"));
        assertTrue(ModularCredit.isValidModularCredit("3.5"));
        assertTrue(ModularCredit.isValidModularCredit("2"));
        assertTrue(ModularCredit.isValidModularCredit("0"));
    }

    @Test
    public void getValue() {
        ModularCredit modularCredit = new ModularCredit("4");
        assertTrue(modularCredit.getValue() == 4);
    }

    @Test
    public void equals() {
        ModularCredit modularCredit = new ModularCredit("4");

        // same object -> returns true
        assertTrue(modularCredit.equals(modularCredit));

        // same values -> returns true
        ModularCredit modularCreditCopy = new ModularCredit("4");
        assertTrue(modularCredit.equals(modularCreditCopy));

        // different types -> returns false
        assertFalse(modularCredit.equals(1));

        // null -> returns false
        assertFalse(modularCredit.equals(null));

        // different modular credit -> returns false
        ModularCredit differentModularCredit = new ModularCredit("1.5");
        assertFalse(modularCredit.equals(differentModularCredit));
    }
}
