package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class IndustryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Industry(null));
    }

    @Test
    public void constructor_invalidIndustry_throwsIllegalArgumentException() {
        String invalidIndustry = "";
        assertThrows(IllegalArgumentException.class, () -> new Industry(invalidIndustry));
    }

    @Test
    void isValidIndustry() {
        // null industry
        assertThrows(NullPointerException.class, () -> Industry.isValidIndustry(null));

        // invalid industries
        assertFalse(Industry.isValidIndustry("")); // empty string
        assertFalse(Industry.isValidIndustry(" ")); // spaces only
        assertFalse(Industry.isValidIndustry("$Finance")); // starts with non-alphanumeric character

        // valid industries
        assertTrue(Industry.isValidIndustry("Farming"));
        assertTrue(Industry.isValidIndustry("Health & Wellness")); // non-alphanumeric characters
        assertTrue(Industry.isValidIndustry("Bitcoin 2")); // with numbers
    }

    @Test
    void testEqualsAndHashcode() {
        Industry industry = new Industry("Finance");

        // same values -> returns true
        assertTrue(industry.equals(new Industry("Finance")));
        assertEquals(industry.hashCode(), new Industry("Finance").hashCode());

        // same object -> returns true
        assertTrue(industry.equals(industry));

        // null -> returns false
        assertFalse(industry.equals(null));

        // different types -> returns false
        assertFalse(industry.equals(new Company("Google")));

        // different values -> returns false
        assertFalse(industry.equals(new Industry("Engineering")));
        assertNotEquals(industry.hashCode(), (new Industry("Engineering").hashCode()));
    }

}
