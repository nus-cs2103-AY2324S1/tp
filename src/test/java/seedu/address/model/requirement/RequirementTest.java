package seedu.address.model.requirement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.Role;

public class RequirementTest {

    @Test
    public void constructor_nullRequirement_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Requirement(null));
    }

    @Test
    public void constructor_invalidRequirement_throwsIllegalArgumentException() {
        String invalidRequirementName = ""; // An empty string
        assertThrows(IllegalArgumentException.class, () ->
                new Requirement(invalidRequirementName));
    }

    @Test
    public void isValidRequirement() {
        // invalid requirement -> returns false
        assertFalse(Requirement.isValidRequirementName("")); // Empty string
        assertFalse(Requirement.isValidRequirementName("12345678901234567890123456789012345678901234567890123456"
                + "890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456"
                + "678901234567890123456789012345678901234567890a")); // 201 characters

        // valid requirement -> returns true
        assertTrue(Requirement.isValidRequirementName("Maths Requirement")); // Alphabets
        assertTrue(Requirement.isValidRequirementName("C3")); // Alphanumeric
        assertTrue(Requirement.isValidRequirementName("c")); // Single character
        assertTrue(Requirement.isValidRequirementName("B++")); // Alphabets and special symbols
        assertTrue(Requirement.isValidRequirementName("!&^*%)_#^)_#^_+Q")); // Various special symbols
        assertTrue(Role.isValidRole("123456789012345678901234567890123456789012345678901234567"
                + "89012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345"
                + "678901234567890123456789012345678901234567890")); // 200 characters
    }

    @Test
    public void equals() {
        Requirement requirement = new Requirement("English Requirement");
        Requirement differentRequirement = new Requirement("Arts Requirement");

        // same values -> returns true
        assertTrue(requirement.equals(new Requirement("English Requirement")));

        // same object -> returns true
        assertTrue(requirement.equals(requirement));

        // null -> returns false
        assertFalse(requirement.equals(null));

        // different types -> returns false
        assertFalse(requirement.equals(5.0f));

        // different values -> returns false
        assertFalse(requirement.equals(differentRequirement));
    }

    @Test
    public void hashCode_sameRequirementName_sameHashCode() {
        Requirement requirement1 = new Requirement("History Requirement");
        Requirement requirement2 = new Requirement("History Requirement");
        assertEquals(requirement1.hashCode(), requirement2.hashCode());
    }

    @Test
    public void toString_validRequirementName_correctFormat() {
        Requirement requirement = new Requirement("Physics Requirement");
        assertEquals("[Physics Requirement]", requirement.toString());
    }
}
