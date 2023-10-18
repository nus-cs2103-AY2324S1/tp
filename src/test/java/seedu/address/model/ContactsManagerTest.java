package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.ALICE;
import static seedu.address.testutil.TestData.VALID_NOTE_BOB;
import static seedu.address.testutil.TestData.getTypicalContactsManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.TestData;

public class ContactsManagerTest {

    private final ContactsManager contactsManager = new ContactsManager();
    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), contactsManager.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactsManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyContactsManager_replacesData() {
        ContactsManager newData = getTypicalContactsManager();
        contactsManager.resetData(newData);
        assertEquals(newData, contactsManager);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE)
                .withNote(VALID_NOTE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        ContactListStub newData = new ContactListStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> contactsManager.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactsManager.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInContactsManager_returnsFalse() {
        assertFalse(contactsManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInContactsManager_returnsTrue() {
        contactsManager.addContact(ALICE);
        assertTrue(contactsManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInContactsManager_returnsTrue() {
        contactsManager.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE)
                .withNote(VALID_NOTE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertTrue(contactsManager.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class,
            () -> contactsManager.getContactList().remove(0)
        );
    }

    @Test
    public void toStringMethod() {
        String expected = ContactsManager.class.getCanonicalName()
                + "{contacts=" + contactsManager.getContactList() + "}";
        assertEquals(expected, contactsManager.toString());
    }

    /**
     * A stub ContactList whose contacts list can violate interface constraints.
     */
    private static class ContactListStub implements ContactList {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

        ContactListStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }
    }

}
