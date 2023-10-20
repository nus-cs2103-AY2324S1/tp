package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.TestData;



public class ContactsTest {
    @Test
    public void constructor_nothing_isEmpty() {
        assertEquals(Collections.emptyList(), new Contacts().getUnmodifiableList());
    }

    @Test
    public void contains() {
        Contacts contacts = new Contacts();

        // Does not contain
        assertFalse(contacts.contains(TestData.ALICE));

        // Contains identical object
        contacts.add(TestData.ALICE);
        assertTrue(contacts.contains(TestData.ALICE));

        // Contains similar Contact deemed the same
        Contact edited = new ContactBuilder(TestData.ALICE)
                .withNote(TestData.VALID_NOTE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertTrue(contacts.contains(edited));
    }

    @Test
    public void getUnmodifiableList_modifyList_throwsException() {
        assertThrows(
            UnsupportedOperationException.class,
            () -> TestData.getTypicalContacts().getUnmodifiableList().remove(0)
        );
    }
}
