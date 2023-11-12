package transact.model.transaction.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    // Test Construction with Valid Description
    @Test
    public void testConstructionWithValidDescription() {
        String validDescription = "Valid description";
        Description description = new Description(validDescription);
        assertNotNull(description);
    }

    // Test Construction with Invalid Description (Null)
    @Test
    public void testConstructionWithInvalidDescription() {
        String nullDescription = null; // Null
        assertThrows(NullPointerException.class, () -> new Description(nullDescription));
    }

    // Test getValue Method
    @Test
    public void testGetValueMethod() {
        String validDescription = "Valid description";
        Description description = new Description(validDescription);
        assertEquals(validDescription, description.getValue());
    }

    // Test toString Method
    @Test
    public void testToStringMethod() {
        String validDescription = "Valid description";
        Description description = new Description(validDescription);
        assertEquals(validDescription, description.toString());
    }

    // Test equals Method
    @Test
    public void testEqualsMethod() {
        Description description1 = new Description("Description");
        Description description2 = new Description("Description");
        Description description3 = new Description("Different Description");
        assertEquals(description1, description2);
        assertNotEquals(description1, description3);
        assertNotEquals(description1, null);
    }

    // Test isValidDescription Method with Valid Description
    @Test
    public void testIsValidDescriptionWithValidString() {
        assertTrue(Description.isValidDescription("Valid Description")); // Generic
        assertTrue(Description.isValidDescription("-")); // Only one character
        assertTrue(Description.isValidDescription("Lorem ipsum dolor sit amet, "
                + "consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut "
                + "labore et dolore magna aliqua.")); // Long Description

    }

    // Test isValidDescription Method with Invalid Description
    @Test
    public void testIsValidDescriptionWithInvalidString() {
        assertFalse(Description.isValidDescription("")); // Empty String
        assertFalse(Description.isValidDescription(" ")); // String with only whitespace
    }
}

