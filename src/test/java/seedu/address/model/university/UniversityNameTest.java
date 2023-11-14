package seedu.address.model.university;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_UNIVERSITY_NAME;

import org.junit.jupiter.api.Test;

public class UniversityNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UniversityName(null));
    }

    @Test
    public void constructor_invalidUniversityName_throwsIllegalArgumentException() {
        String invalidUniversityName = "";
        assertThrows(IllegalArgumentException.class, () -> new UniversityName(invalidUniversityName));
    }

    @Test
    public void isValidUniversityName() {
        // null universityName
        assertThrows(NullPointerException.class, () -> UniversityName.isValidUniversityName(null));

        // invalid universityName
        assertFalse(UniversityName.isValidUniversityName("")); // empty string
        assertFalse(UniversityName.isValidUniversityName(" ")); // spaces only
        assertFalse(UniversityName.isValidUniversityName(" How to Cook Potatoes")); // first character space

        // valid universityName`
        assertTrue(UniversityName.isValidUniversityName("How to Eat Potatoes")); // alphabets only
        assertTrue(UniversityName.isValidUniversityName("Decoding with Alyssa :D")); // alphanumeric, with symbols
        assertTrue(UniversityName.isValidUniversityName(TYPICAL_UNIVERSITY_NAME)); // alphanumeric characters
    }

    @Test
    public void equals() {
        UniversityName universityName = new UniversityName(TYPICAL_UNIVERSITY_NAME);

        // same values -> returns true
        assertTrue(universityName.equals(new UniversityName(TYPICAL_UNIVERSITY_NAME)));

        // same object -> returns true
        assertTrue(universityName.equals(universityName));

        // null -> returns false
        assertFalse(universityName.equals(null));

        // different types -> returns false
        assertFalse(universityName.equals(5.0f));

        // different values -> returns false
        assertFalse(universityName.equals(new UniversityName(EDGE_CASE_VALID_UNIVERSITY_NAME)));
    }
}
