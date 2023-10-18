package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.MonthDay;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;


public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
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
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
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
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different birthday -> returns false
        editedAlice = new PersonBuilder(ALICE).withBirthday(MonthDay.of(6, 9)).build();
        assertFalse(ALICE.equals(editedAlice));

        // different linkedin -> returns false
        editedAlice = new PersonBuilder(ALICE).withLinkedin("linkedin").build();
        assertFalse(ALICE.equals(editedAlice));

        // different secondaryEmail -> returns false
        editedAlice = new PersonBuilder(ALICE).withSecondaryEmail("alice@hotmail.com").build();
        assertFalse(ALICE.equals(editedAlice));

        // different telegram -> returns false
        editedAlice = new PersonBuilder(ALICE).withTelegram("@telegram").build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hasValid() {
        Person person = new PersonBuilder().build();
        assertFalse(person.hasValidTelegram());
        assertFalse(person.hasValidBirthday());
        assertFalse(person.hasValidLinkedin());
        assertFalse(person.hasValidSecondaryEmail());
        person = new PersonBuilder()
                .withBirthday(MonthDay.of(6, 9))
                .withLinkedin("linkedin")
                .withTelegram("@telegram")
                .withSecondaryEmail("email@email.com")
                .build();
        assertTrue(person.hasValidTelegram());
        assertTrue(person.hasValidBirthday());
        assertTrue(person.hasValidLinkedin());
        assertTrue(person.hasValidSecondaryEmail());
    }

    @Test
    public void hasSameEmailMethod() {
        Person person = new PersonBuilder().withEmail("email@email.com").withSecondaryEmail("email@email.com").build();
        assertTrue(person.hasSameEmail());
        person = new PersonBuilder().withEmail("email@email.com").withSecondaryEmail("email@gmail.com").build();
        assertFalse(person.hasSameEmail());
        person = new PersonBuilder().withEmail("email@email.com").build();
        assertFalse(person.hasSameEmail());
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void getNonEmergencyTags() {
        Person person = new PersonBuilder().withTags("RA", "SOS", "Friend", "Buddy").build();
        Set<Tag> nonEmergencyTags = person.getNonEmergencyTags();
        System.out.println(nonEmergencyTags);
        assertEquals(nonEmergencyTags.size(), 2);
        assertFalse(nonEmergencyTags.contains(new Tag("RA")));
        assertFalse(nonEmergencyTags.contains(new Tag("SOS")));
        assertTrue(nonEmergencyTags.contains(new Tag("Friend")));
        assertTrue(nonEmergencyTags.contains(new Tag("Buddy")));
    }

    @Test
    public void getEmergencyTags() {
        Person person = new PersonBuilder().withTags("RA", "SOS", "Friend", "Buddy").build();
        Set<Tag> emergencyTags = person.getEmergencyTags();
        assertEquals(emergencyTags.size(), 2);
        assertTrue(emergencyTags.contains(new Tag("RA")));
        assertTrue(emergencyTags.contains(new Tag("SOS")));
        assertFalse(emergencyTags.contains(new Tag("Friend")));
        assertFalse(emergencyTags.contains(new Tag("Buddy")));
    }
}
