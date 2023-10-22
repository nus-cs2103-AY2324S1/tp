package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

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

        // invalid companies
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only

        // valid company numbers
        assertTrue(Company.isValidCompany("Google"));
        assertTrue(Company.isValidCompany("National University of Singapore"));
        assertTrue(Company.isValidCompany("G@@gle")); // not alphanumeric
        assertTrue(Company.isValidCompany("Shopee 2")); // company with numbers
    }

    @Test
    public void equals() {
        Company company = new Company("999");

        // same values -> returns true
        assertEquals(company, new Company("999"));

        // same object -> returns true
        assertEquals(company, company);

        // null -> returns false
        assertNotEquals(null, company);

        // different types -> returns false
        assertNotEquals(5.0f, company);

        // different values -> returns false
        assertNotEquals(company, new Company("995"));
    }
}
