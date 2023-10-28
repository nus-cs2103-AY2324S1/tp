package swe.context.model.alternate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import swe.context.testutil.TestData;

public class AlternateContactTest {
    @Test
    public void isValidName_alphanumeric_true() {
        assertTrue(AlternateContact.isValid(TestData.Valid.AlternateContact.ALPHANUMERIC));
    }

    @Test
    public void isValidName_alphanumericUnderscore_true() {
        assertTrue(AlternateContact.isValid(TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE));
    }

    @Test
    public void isValidName_missingSymbol_false() {
        assertFalse(AlternateContact.isValid(TestData.Invalid.AlternateContact.MISSING_SYMBOL));
    }

    @Test
    public void isValidName_whitespace_false() {
        assertFalse(AlternateContact.isValid(TestData.Invalid.AlternateContact.WHITESPACE));
    }
}
