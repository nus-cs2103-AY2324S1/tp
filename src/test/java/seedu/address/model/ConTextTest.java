package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.ALICE;
import static seedu.address.testutil.TestData.VALID_NOTE_BOB;
import static seedu.address.testutil.TestData.getTypicalConText;

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

public class ConTextTest {

    private final ConText conText = new ConText();
    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), conText.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> conText.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyConText_replacesData() {
        ConText newData = getTypicalConText();
        conText.resetData(newData);
        assertEquals(newData, conText);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE)
                .withNote(VALID_NOTE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        ConTextStub newData = new ConTextStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> conText.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> conText.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInConText_returnsFalse() {
        assertFalse(conText.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInConText_returnsTrue() {
        conText.addContact(ALICE);
        assertTrue(conText.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInConText_returnsTrue() {
        conText.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE)
                .withNote(VALID_NOTE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertTrue(conText.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> conText.getContactList().remove(0)
        );
    }

    @Test
    public void toStringMethod() {
        String expected = ConText.class.getCanonicalName() + "{contacts=" + conText.getContactList() + "}";
        assertEquals(expected, conText.toString());
    }

    /**
     * A stub ContactList whose contacts list can violate interface constraints.
     */
    private static class ConTextStub implements ContactList {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

        ConTextStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }
    }

}
