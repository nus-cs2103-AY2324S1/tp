package seedu.address.model.university;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniversityAttributeTest {
    @Test
    public void isValidUniversityAttributeForSearch() {
        // invalid universityAttribute
        assertFalse(UniversityAttribute.isValidAttributeForSearch("units"));
        assertFalse(UniversityAttribute.isValidAttributeForSearch(""));
        assertFalse(UniversityAttribute.isValidAttributeForSearch(" "));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("a"));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("123"));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("PARTNER"));
        assertFalse(UniversityAttribute.isValidAttributeForSearch(null));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("UNIVERSITY"));


        // valid universityAttribute
        assertTrue(UniversityAttribute.isValidAttributeForSearch("university"));

        // Additional invalid cases
        assertFalse(UniversityAttribute.isValidAttributeForSearch(" university"));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("university "));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("univer sity"));
    }

    @Test
    public void isValidUniversityAttributeForSort() {
        // invalid universityAttribute
        assertFalse(UniversityAttribute.isValidAttributeForSort("units"));
        assertFalse(UniversityAttribute.isValidAttributeForSort(""));
        assertFalse(UniversityAttribute.isValidAttributeForSort(" "));
        assertFalse(UniversityAttribute.isValidAttributeForSort("a"));
        assertFalse(UniversityAttribute.isValidAttributeForSort("123"));
        assertFalse(UniversityAttribute.isValidAttributeForSort("PARTNER"));
        assertFalse(UniversityAttribute.isValidAttributeForSort(null));
        assertFalse(UniversityAttribute.isValidAttributeForSort("UNIVERSITY"));

        // valid universityAttribute
        assertTrue(UniversityAttribute.isValidAttributeForSort("university"));

        // Additional invalid cases
        assertFalse(UniversityAttribute.isValidAttributeForSearch(" university"));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("university "));
        assertFalse(UniversityAttribute.isValidAttributeForSearch("univer sity"));
    }
}
