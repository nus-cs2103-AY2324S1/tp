package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyDescription(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyDescription(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> CompanyDescription.isValidCompanyDescription(null));

        // invalid descriptions
        assertFalse(CompanyDescription.isValidCompanyDescription("")); // empty string
        assertFalse(CompanyDescription.isValidCompanyDescription(" ")); // spaces only

        // valid descriptions
        assertTrue(CompanyDescription.isValidCompanyDescription("This is a valid description."));
        assertTrue(CompanyDescription.isValidCompanyDescription("Short")); // short description
        assertTrue(CompanyDescription.isValidCompanyDescription("0Longer descriptions should also be"
                + "perfectly valid to handle."));
    }

    @Test
    public void equals() {
        CompanyDescription companyDescription = new CompanyDescription("Sample companyDescription");

        // same values -> returns true
        assertEquals(companyDescription, new CompanyDescription("Sample companyDescription"));

        // same object -> returns true
        assertEquals(companyDescription, companyDescription);

        // null -> returns false
        assertNotEquals(null, companyDescription);

        // different types -> returns false
        assertFalse(companyDescription.equals(5.0f));

        // different values -> returns false
        assertNotEquals(companyDescription, new CompanyDescription("Different companyDescription"));
    }
}
