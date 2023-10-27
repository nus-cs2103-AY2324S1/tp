package swe.context.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import swe.context.testutil.ContactBuilder;
import swe.context.testutil.TestData;

public class ContactTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(TestData.Valid.Contact.ALICE.isSameContact(TestData.Valid.Contact.ALICE));

        // null -> returns false
        assertFalse(TestData.Valid.Contact.ALICE.isSameContact(null));

        // same name, all other attributes different -> returns true
        Contact editedAlice =
                new ContactBuilder(TestData.Valid.Contact.ALICE)
                        .withPhone(TestData.Valid.PHONE_BOB)
                        .withEmail(TestData.Valid.EMAIL_BOB)
                        .withNote(TestData.Valid.NOTE_BOB)
                        .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                        .build();
        assertTrue(TestData.Valid.Contact.ALICE.isSameContact(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ContactBuilder(TestData.Valid.Contact.ALICE).withName(TestData.Valid.NAME_BOB).build();
        assertFalse(TestData.Valid.Contact.ALICE.isSameContact(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Contact editedBob = new ContactBuilder(TestData.Valid.Contact.BOB)
                .withName(TestData.Valid.NAME_BOB.toLowerCase())
                .build();
        assertFalse(TestData.Valid.Contact.BOB.isSameContact(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = TestData.Valid.NAME_BOB + " ";
        editedBob = new ContactBuilder(TestData.Valid.Contact.BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(TestData.Valid.Contact.BOB.isSameContact(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Contact aliceCopy = new ContactBuilder(TestData.Valid.Contact.ALICE).build();
        assertTrue(TestData.Valid.Contact.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TestData.Valid.Contact.ALICE.equals(TestData.Valid.Contact.ALICE));

        // null -> returns false
        assertFalse(TestData.Valid.Contact.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TestData.Valid.Contact.ALICE.equals(5));

        // different contact -> returns false
        assertFalse(TestData.Valid.Contact.ALICE.equals(TestData.Valid.Contact.BOB));

        // different name -> returns false
        Contact editedAlice =
                new ContactBuilder(TestData.Valid.Contact.ALICE).withName(TestData.Valid.NAME_BOB).build();
        assertFalse(TestData.Valid.Contact.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ContactBuilder(TestData.Valid.Contact.ALICE).withPhone(TestData.Valid.PHONE_BOB).build();
        assertFalse(TestData.Valid.Contact.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ContactBuilder(TestData.Valid.Contact.ALICE).withEmail(TestData.Valid.EMAIL_BOB).build();
        assertFalse(TestData.Valid.Contact.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ContactBuilder(TestData.Valid.Contact.ALICE).withNote(TestData.Valid.NOTE_BOB).build();
        assertFalse(TestData.Valid.Contact.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ContactBuilder(TestData.Valid.Contact.ALICE)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertFalse(TestData.Valid.Contact.ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected =
                Contact.class.getCanonicalName()
                        + "{name=" + TestData.Valid.Contact.ALICE.getName()
                        + ", phone=" + TestData.Valid.Contact.ALICE.getPhone()
                        + ", email=" + TestData.Valid.Contact.ALICE.getEmail()
                        + ", note=" + TestData.Valid.Contact.ALICE.getNote()
                        + ", tags=" + TestData.Valid.Contact.ALICE.getTags() + "}";
        assertEquals(expected, TestData.Valid.Contact.ALICE.toString());
    }
}
