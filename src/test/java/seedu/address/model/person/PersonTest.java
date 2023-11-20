package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().buildClient();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).buildClient();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildClient();
        assertFalse(ALICE.isSamePerson(editedAlice));

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
        Person aliceCopy = new PersonBuilder(ALICE).buildClient();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildClient();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).buildClient();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).buildClient();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).buildClient();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).buildClient();
        assertFalse(ALICE.equals(editedAlice));

        // same values -> returns true
        Person bobCopy = new PersonBuilder(BOB).buildLead();
        assertTrue(BOB.equals(bobCopy));

        // same object -> returns true
        assertTrue(BOB.equals(BOB));

        // null -> returns false
        assertFalse(BOB.equals(null));

        // different type -> returns false
        assertFalse(BOB.equals(5));

        // different person -> returns false
        assertFalse(BOB.equals(ALICE));

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
        String expected = Client.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail()
                + ", type=" + ALICE.getType()
                + ", address=" + ALICE.getAddress()
                + ", meetingTime=" + ALICE.getMeetingTime()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void getMeetingTimeStringForUserInterface() {
        // non-empty meeting time
        String expected = "Meeting on: " + BENSON.getMeetingTime().get();
        assertEquals(expected, BENSON.getMeetingTimeStringForUserInterface());

        // empty meeting time
        Person person = new PersonBuilder(ALICE).withMeetingTime(null).buildClient();
        assertNull(person.getMeetingTimeStringForUserInterface());
    }
}
