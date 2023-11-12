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
    public void isValidName_noWhitespace_false() {
        assertFalse(AlternateContact.isValid(TestData.Invalid.AlternateContact.NO_WHITESPACE));
    }

    @Test
    public void isValidName_whitespaceInName_false() {
        assertFalse(AlternateContact.isValid(TestData.Invalid.AlternateContact.WHITESPACE_IN_NAME));
    }

    @Test
    public void equals() {
        AlternateContact alphanumeric = new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC);

        // same values -> return true
        AlternateContact alphanumericCopy = new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC);
        assertTrue(alphanumeric.equals(alphanumericCopy));

        // same object -> return true
        assertTrue(alphanumeric.equals(alphanumeric));

        // different type -> return false
        assertFalse(alphanumeric.equals(1));

        // null -> return false
        assertFalse(alphanumeric.equals(null));

        // different alternate contact -> return false
        AlternateContact different = new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE);
        assertFalse(alphanumeric.equals(different));
    }
}
