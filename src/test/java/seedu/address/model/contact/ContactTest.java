package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestData.Valid.Contact.ALICE;
import static seedu.address.testutil.TestData.Valid.EMAIL_BOB;
import static seedu.address.testutil.TestData.Valid.NAME_BOB;
import static seedu.address.testutil.TestData.Valid.NOTE_BOB;
import static seedu.address.testutil.TestData.Valid.PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.TestData;

public class ContactTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameContact(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameContact(null));

        // same name, all other attributes different -> returns true
        Contact editedAlice = new ContactBuilder(ALICE).withPhone(PHONE_BOB).withEmail(EMAIL_BOB)
                .withNote(NOTE_BOB).withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES).build();
        assertTrue(ALICE.isSameContact(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ContactBuilder(ALICE).withName(NAME_BOB).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Contact editedBob = new ContactBuilder(TestData.Valid.Contact.BOB)
                .withName(NAME_BOB.toLowerCase())
                .build();
        assertFalse(TestData.Valid.Contact.BOB.isSameContact(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = NAME_BOB + " ";
        editedBob = new ContactBuilder(TestData.Valid.Contact.BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(TestData.Valid.Contact.BOB.isSameContact(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Contact aliceCopy = new ContactBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different contact -> returns false
        assertFalse(ALICE.equals(TestData.Valid.Contact.BOB));

        // different name -> returns false
        Contact editedAlice = new ContactBuilder(ALICE).withName(NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ContactBuilder(ALICE).withPhone(PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ContactBuilder(ALICE).withEmail(EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ContactBuilder(ALICE).withNote(NOTE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ContactBuilder(ALICE)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Contact.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", note=" + ALICE.getNote() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
