package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompany_throwsIllegalArgumentException() {
        String invalidCompany = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidCompany() {
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid company
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only
        assertFalse(Company.isValidCompany("Apple/")); // contains '/' characters

        // valid company
        assertTrue(Company.isValidCompany("big company")); // alphabets only
        assertTrue(Company.isValidCompany("12345")); // numbers only
        assertTrue(Company.isValidCompany("big company the 2nd")); // alphanumeric characters
        assertTrue(Company.isValidCompany("Big Company")); // with capital letters
        assertTrue(Company.isValidCompany("This is a very Big Company the 2nd")); // long company name
    }

    @Test
    public void equals() {
        Company company = new Company("Valid Company");

        // same values -> returns true
        assertTrue(company.equals(new Company("Valid Company")));

        // same object -> returns true
        assertTrue(company.equals(company));

        // null -> returns false
        assertFalse(company.equals(null));

        // different types -> returns false
        assertFalse(company.equals(5.0f));

        // different values -> returns false
        assertFalse(company.equals(new Company("Other Valid Company")));
    }

    @Test
    public void compareTo() {
        Company company = new Company("Valid Company");
        Company company2 = new Company("Valid Company");
        Company company3 = new Company("Other Valid Company");

        // same values -> returns 0
        assertEquals(0, company.compareTo(company2));

        // different values -> returns 1
        assertTrue(company.compareTo(company3) > 0);
        assertTrue(company3.compareTo(company) < 0);
    }
}
