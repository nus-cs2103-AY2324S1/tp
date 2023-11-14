package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidCompanyName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidCompanyName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidName(null));

        // invalid name
        assertFalse(CompanyName.isValidName("")); // empty string
        assertFalse(CompanyName.isValidName(" ")); // spaces only
        assertFalse(CompanyName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(CompanyName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(CompanyName.isValidName("Tech Giants")); // alphabets only
        assertTrue(CompanyName.isValidName("123 Tech")); // numbers and alphabets
        assertTrue(CompanyName.isValidName("Software Corp 2nd")); // alphanumeric characters
        assertTrue(CompanyName.isValidName("Big Data Systems")); // with capital letters
        assertTrue(CompanyName.isValidName("International Business Machines Corporation")); // long names
    }

    @Test
    public void equals() {
        CompanyName companyName = new CompanyName("Tech Giants Inc.");

        // same values -> returns true
        assertEquals(companyName, new CompanyName("Tech Giants Inc."));

        // same object -> returns true
        assertEquals(companyName, companyName);

        // null -> returns false
        assertNotEquals(null, companyName);

        // different types -> returns false
        assertFalse(companyName.equals(5.0f));

        // different values -> returns false
        assertNotEquals(companyName, new CompanyName("Other Tech Corp."));
    }
}

