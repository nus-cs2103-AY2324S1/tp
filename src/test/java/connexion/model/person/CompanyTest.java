package connexion.model.person;

import static connexion.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        // EP: null value
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid companies
        // EP: Empty strings
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only

        // Valid companies
        // EP: alphanumeric characters
        assertTrue(Company.isValidCompany("OCBC")); //alphabet only
        assertTrue(Company.isValidCompany("OCBC Pte Ltd")); //long company (alphabet only)
        assertTrue(Company.isValidCompany("123456")); //numbers only
        assertTrue(Company.isValidCompany("OCBC123")); //alphabet and numbers

        // EP: Non-alphanumeric characters
        assertTrue(Company.isValidCompany("-")); // one character
        assertTrue(Company.isValidCompany("Jane's Street")); //non-alphanumeric within company name
        assertTrue(Company.isValidCompany("Edward Hopper House & Study Center")); // long company
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
    void getDetailString_equals_input() {
        Company company = new Company("Valid Company");
        assertEquals(company.getDetailString(), "Company: Valid Company");
        assertNotEquals(company.getDetailString(), "Nonsense"); //to show it's actually matching the string
    }

    @Test
    void getValue_equals_input() {
        Company company = new Company("Valid Company");
        assertEquals(company.getValue(), "Valid Company");
        assertNotEquals(company.getValue(), "Nonsense"); //to show it's actually matching the string
    }

    @Test
    void getListString_equals_input() {
        Company company = new Company("Valid Company");
        assertEquals(company.getListString(), "Valid Company");
        assertNotEquals(company.getListString(), "Nonsense"); //to show it's actually matching the string
    }
}
