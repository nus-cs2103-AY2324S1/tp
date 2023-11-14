package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidCompanyName_throwsIllegalArgumentException() {
        String invalidCompanyName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidCompanyName));
    }

    @Test
    public void isValidCompanyName() {
        // null companyName -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> CompanyName.isValidCompanyName(null));

        // invalid companyName -> returns false
        assertFalse(CompanyName.isValidCompanyName("")); // empty string
        assertFalse(CompanyName.isValidCompanyName(" ")); // spaces only
        assertFalse(CompanyName.isValidCompanyName("^")); // only non-alphanumeric characters
        assertFalse(CompanyName.isValidCompanyName("dbs*")); // contains non-alphanumeric characters
        assertFalse(CompanyName.isValidCompanyName("123456789012345678901234567890123456789012345678901234567"
                + "89012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345"
                + "678901234567890123456789012345678901234567890a")); // 201 characters

        // valid companyName -> returns true
        assertTrue(CompanyName.isValidCompanyName("takashimaya")); // alphabets only
        assertTrue(CompanyName.isValidCompanyName("111")); // numbers only
        assertTrue(CompanyName.isValidCompanyName("123 shopping centre")); // alphanumeric characters
        assertTrue(CompanyName.isValidCompanyName("Capital Trading Centre")); // with capital letters
        assertTrue(CompanyName.isValidCompanyName("DBS Bank 2nd branch")); // long companyNames
        assertTrue(CompanyName.isValidCompanyName("123456789012345678901234567890123456789012345678901234567"
                + "89012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345"
                + "678901234567890123456789012345678901234567890")); // 200 characters
    }

    @Test
    public void equals() {
        CompanyName companyName = new CompanyName("Valid CompanyName");

        // same values -> returns true
        assertTrue(companyName.equals(new CompanyName("Valid CompanyName")));

        // same object -> returns true
        assertTrue(companyName.equals(companyName));

        // null -> returns false
        assertFalse(companyName.equals(null));

        // different types -> returns false
        assertFalse(companyName.equals(5.0f));

        // different values -> returns false
        assertFalse(companyName.equals(new CompanyName("Other Valid CompanyName")));
    }

    @Test
    public void compareTo_sameCompanyName_returnsZero() {
        CompanyName companyName1 = new CompanyName("Apple");
        CompanyName companyName2 = new CompanyName("Apple");

        assertEquals(0, companyName1.compareTo(companyName2));
    }

    @Test
    public void compareTo_companyName1EarlierThanCompanyName2_returnsNegative() {
        CompanyName companyName1 = new CompanyName("Apple");
        CompanyName companyName2 = new CompanyName("Microsoft");

        assertEquals(true, companyName1.compareTo(companyName2) < 0);
    }

    @Test
    public void compareTo_companyName1LaterThanCompanyName2_returnsPositive() {
        CompanyName companyName1 = new CompanyName("Microsoft");
        CompanyName companyName2 = new CompanyName("Apple");

        assertEquals(true, companyName1.compareTo(companyName2) > 0);
    }

    @Test
    public void compareTo_companyNamesDifferByOneCharacter_correctOrder() {
        CompanyName companyName1 = new CompanyName("Apples");
        CompanyName companyName2 = new CompanyName("Apple");

        assertEquals(true, companyName1.compareTo(companyName2) > 0);
    }
    @Test
    public void compareTo_companyNamesDifferByUppercase_correctOrder() {
        CompanyName companyName1 = new CompanyName("Apples");
        CompanyName companyName2 = new CompanyName("banana");

        assertEquals(true, companyName1.compareTo(companyName2) < 0);
    }
}
