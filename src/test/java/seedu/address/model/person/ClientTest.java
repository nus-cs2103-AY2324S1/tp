package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalClients;

public class ClientTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().buildClient();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(TypicalClients.ALICE.isSamePerson(TypicalClients.ALICE));

        // null -> returns false
        assertFalse(TypicalClients.ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(TypicalClients.ALICE)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).buildClient();
        assertTrue(TypicalClients.ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalClients.ALICE).withName(VALID_NAME_BOB).buildClient();
        assertFalse(TypicalClients.ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).buildClient();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).buildClient();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(TypicalClients.ALICE).buildClient();
        assertTrue(TypicalClients.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalClients.ALICE.equals(TypicalClients.ALICE));

        // null -> returns false
        assertFalse(TypicalClients.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalClients.ALICE.equals(5));

        // different person -> returns false
        assertFalse(TypicalClients.ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(TypicalClients.ALICE).withName(VALID_NAME_BOB).buildClient();
        assertFalse(TypicalClients.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(TypicalClients.ALICE).withPhone(VALID_PHONE_BOB).buildClient();
        assertFalse(TypicalClients.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(TypicalClients.ALICE).withEmail(VALID_EMAIL_BOB).buildClient();
        assertFalse(TypicalClients.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(TypicalClients.ALICE).withAddress(VALID_ADDRESS_BOB).buildClient();
        assertFalse(TypicalClients.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalClients.ALICE).withTags(VALID_TAG_HUSBAND).buildClient();
        assertFalse(TypicalClients.ALICE.equals(editedAlice));

        // same values -> returns true
        Person bobCopy = new PersonBuilder(BOB).buildClient();
        assertTrue(BOB.equals(bobCopy));

        // same object -> returns true
        assertTrue(BOB.equals(BOB));

        // null -> returns false
        assertFalse(BOB.equals(null));

        // different type -> returns false
        assertFalse(BOB.equals(5));

        // different person -> returns false
        assertFalse(BOB.equals(TypicalClients.ALICE));

        // different name -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_AMY).buildClient();
        assertFalse(BOB.equals(editedBob));

        // different phone -> returns false
        editedBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).buildClient();
        assertFalse(BOB.equals(editedBob));

        // different email -> returns false
        editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).buildClient();
        assertFalse(BOB.equals(editedBob));

        // different address -> returns false
        editedBob = new PersonBuilder(BOB).withAddress(VALID_ADDRESS_AMY).buildClient();
        assertFalse(BOB.equals(editedBob));

        // different tags -> returns false
        editedBob = new PersonBuilder(BOB).withTags(VALID_TAG_HUSBAND).buildClient();
        assertFalse(BOB.equals(editedBob));
    }

    @Test
    public void toStringMethod() {
        String expected = Client.class.getCanonicalName() + "{name=" + TypicalClients.ALICE.getName()
                + ", phone=" + TypicalClients.ALICE.getPhone()
                + ", email=" + TypicalClients.ALICE.getEmail()
                + ", type=" + TypicalClients.ALICE.getType()
                + ", address=" + TypicalClients.ALICE.getAddress()
                + ", meetingTime=" + TypicalClients.ALICE.getMeetingTime()
                + ", tags=" + TypicalClients.ALICE.getTags() + "}";
        assertEquals(expected, TypicalClients.ALICE.toString());
    }
    @Test
    public void isClient() {
        assertTrue(ALICE.isClient());
        assertFalse(ALICE.isLead());
    }
}
