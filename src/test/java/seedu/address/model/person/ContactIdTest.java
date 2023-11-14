package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContactIdTest {

    @Test
    public void equals() {
        ContactID id = ContactID.fromString("1");

        // same values -> returns true
        assertTrue(id.equals(ContactID.fromString("1")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // same values but using fromInt method -> returns true
        assertTrue(id.equals(ContactID.fromInt(1)));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(ContactID.fromString("2")));
    }
}
