package swe.context.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import swe.context.model.contact.exceptions.ContactNotFoundException;
import swe.context.model.contact.exceptions.DuplicateContactException;
import swe.context.testutil.ContactBuilder;
import swe.context.testutil.TestData;

public class UniqueContactListTest {
    private final UniqueContactList uniqueContactList = new UniqueContactList();

    @Test
    public void contains_contactNotInList_returnsFalse() {
        assertFalse(uniqueContactList.contains(TestData.Valid.Contact.ALICE));
    }

    @Test
    public void contains_contactInList_returnsTrue() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        assertTrue(uniqueContactList.contains(TestData.Valid.Contact.ALICE));
    }

    @Test
    public void contains_contactWithSameIdentityFieldsInList_returnsTrue() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        Contact editedAlice = new ContactBuilder(TestData.Valid.Contact.ALICE)
                .withNote(TestData.Valid.NOTE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertTrue(uniqueContactList.contains(editedAlice));
    }

    @Test
    public void add_duplicateContact_throwsDuplicateContactException() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.add(TestData.Valid.Contact.ALICE));
    }

    @Test
    public void setContact_targetContactNotInList_throwsContactNotFoundException() {
        assertThrows(ContactNotFoundException.class,
                () -> uniqueContactList.setContact(TestData.Valid.Contact.ALICE, TestData.Valid.Contact.ALICE));
    }

    @Test
    public void setContact_editedContactIsSameContact_success() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        uniqueContactList.setContact(TestData.Valid.Contact.ALICE, TestData.Valid.Contact.ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(TestData.Valid.Contact.ALICE);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasSameIdentity_success() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        Contact editedAlice = new ContactBuilder(TestData.Valid.Contact.ALICE)
                .withNote(TestData.Valid.NOTE_BOB)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        uniqueContactList.setContact(TestData.Valid.Contact.ALICE, editedAlice);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(editedAlice);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasDifferentIdentity_success() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        uniqueContactList.setContact(TestData.Valid.Contact.ALICE, TestData.Valid.Contact.BOB);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(TestData.Valid.Contact.BOB);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasNonUniqueIdentity_throwsDuplicateContactException() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        uniqueContactList.add(TestData.Valid.Contact.BOB);
        assertThrows(
            DuplicateContactException.class,
            () -> uniqueContactList.setContact(TestData.Valid.Contact.ALICE, TestData.Valid.Contact.BOB)
        );
    }

    @Test
    public void remove_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.remove(null));
    }

    @Test
    public void remove_contactDoesNotExist_throwsContactNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.remove(TestData.Valid.Contact.ALICE));
    }

    @Test
    public void remove_existingContact_removesContact() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        uniqueContactList.remove(TestData.Valid.Contact.ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_list_overwritesList() {
        uniqueContactList.add(TestData.Valid.Contact.ALICE);
        List<Contact> contactList = Collections.singletonList(TestData.Valid.Contact.BOB);
        uniqueContactList.setContacts(contactList);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(TestData.Valid.Contact.BOB);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_listWithDuplicates_throwsException() {
        List<Contact> listWithDuplicateContacts =
                Arrays.asList(TestData.Valid.Contact.ALICE, TestData.Valid.Contact.ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContacts(listWithDuplicateContacts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueContactList.asUnmodifiableObservableList().remove(0));
    }
}
