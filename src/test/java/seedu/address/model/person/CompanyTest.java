package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompany_throwsIllegalArgumentException() {
        String invalidCompany = " ";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidCompany() {
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid company
        assertFalse(Company.isValidCompany(" ")); // spaces only

        // valid company
        assertTrue(Company.isValidCompany("ABC Corporation")); // alphabets and spaces
        assertTrue(Company.isValidCompany("XYZ, Inc.")); // special characters and alphabets
        assertTrue(Company.isValidCompany("123 Tech Company")); // numbers and alphabets
        assertTrue(Company.isValidCompany("Long Company Name with Many Words 12345")); // long company name
    }

    @Test
    public void equals() {
        Company company = new Company("ABC Corporation");

        // same values -> returns true
        assertTrue(company.equals(new Company("ABC Corporation")));

        // same object -> returns true
        assertTrue(company.equals(company));

        // null -> returns false
        assertFalse(company.equals(null));

        // different types -> returns false
        assertFalse(company.equals(5.0f));

        // different values -> returns false
        assertFalse(company.equals(new Company("XYZ, Inc.")));
    }
}
