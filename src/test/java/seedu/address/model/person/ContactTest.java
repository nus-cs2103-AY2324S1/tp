package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Contact(null));
    }

    @Test
    public void constructor_invalidContact_throwsIllegalArgumentException() {
        String invalidContact = "!@#$%";
        assertThrows(IllegalArgumentException.class, () -> new Contact(invalidContact));
    }

    @Test
    public void isValidContactName() {
        // invalid contact names
        assertFalse(Contact.isValidContactName("")); // empty string
        assertFalse(Contact.isValidContactName(" ")); // spaces only
        assertFalse(Contact.isValidContactName("!@#$%")); // special characters only

        // valid contact names
        assertTrue(Contact.isValidContactName("@john1234")); // alphanumeric characters and special character
        assertTrue(Contact.isValidContactName("12345")); // numbers only
        assertTrue(Contact.isValidContactName("john@example.com")); // contains special characters
        assertTrue(Contact.isValidContactName("johnjohn")); // letters only
    }

    @Test
    public void equals() {
        Contact contact1 = new Contact("John Smith");
        Contact contact2 = new Contact("John Smith");
        Contact contact3 = new Contact("John Smith");
        Contact contact4 = new Contact("john@example.com");
        Contact contact5 = new Contact("12345");

        // Same object -> return true
        assertTrue(contact1.equals(contact1));

        // If A equals B then B equals A -> return true
        assertEquals(contact1.equals(contact2), contact2.equals(contact1));

        // A equals B and B equals C then A equals C -> return true
        assertTrue(contact1.equals(contact2) && contact2.equals(contact3));
        assertTrue(contact1.equals(contact3));

        // null -> return false
        assertFalse(contact1.equals(null));

        // different data types -> return false
        assertFalse(contact1.equals(5.0f));

        // Contacts of the same value -> return true
        assertTrue(contact1.equals(contact2));

        // Different contact with different values -> return false
        assertFalse(contact1.equals(contact4));

        // Test additional valid contacts for equality
        assertTrue(contact4.equals(new Contact("john@example.com")));
        assertTrue(contact5.equals(new Contact("12345")));
    }

    @Test
    public void hashCodeMethod() {
        Contact contact1 = new Contact("John Smith");
        Contact contact2 = new Contact("John Smith");
        Contact contact3 = new Contact("Jane Doe");

        // Test that the hash codes of equivalent contacts are equal
        assertEquals(contact1.hashCode(), contact2.hashCode());

        // Test that the hash codes of different contacts are not equal
        assertFalse(contact1.hashCode() == contact3.hashCode());
    }

    @Test
    public void toStringMethod() {
        // Create a Contact with a valid contact name
        Contact contact1 = new Contact("aliceee@gmail.com");
        Contact contact2 = new Contact("aliceee@gmail.com, @aliceee");

        // Define the expected formatted string
        String expectedString1 = "[aliceee@gmail.com]";
        String expectedString2 = "[aliceee@gmail.com, @aliceee]";

        // Call the toString method and compare with the expected string
        assertEquals(expectedString1, contact1.toString());
        assertEquals(expectedString2, contact2.toString());


    }
}
