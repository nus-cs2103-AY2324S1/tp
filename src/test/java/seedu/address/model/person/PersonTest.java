package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalSessions.EMPTY_SESSION;
import static seedu.address.testutil.TypicalSessions.SESSION1A;

import org.junit.jupiter.api.Test;

import seedu.address.model.session.Session;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SessionBuilder;


public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void studentAttendSession() {
        Session emptySession = EMPTY_SESSION;
        Person alice = new PersonBuilder(ALICE).build();
        alice.attendSession(emptySession);

        assertTrue(emptySession.getStudents().contains(alice));
    }

    @Test
    public void studentMissSession() {
        Session sessionWithCarl = new SessionBuilder()
                .withSessionNumber("1").withStudents(SESSION1A.getStudents()).build();
        Person carl = new PersonBuilder(CARL).build();
        carl.missSession(sessionWithCarl);

        assertFalse(sessionWithCarl.getStudents().contains(carl));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TELEGRAM_HANDLE_BOB).withTags(VALID_TAG_HUSBAND).build();
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
    public void isSameName() {
        // person shares same name object
        Name aliceName = ALICE.getName();
        assertTrue(ALICE.isSameName(aliceName));

        // different person share same name
        Person personA = new PersonBuilder().withName("Alice").build();
        Person personB = new PersonBuilder().withName("Alice").build();
        assertTrue(personA.isSameName(personB.getName()));
    }

    @Test
    public void isSameName_sameName_returnsTrue() {
        Name name = new Name("Jeremy");
        Person person = new PersonBuilder().withName(name.toString()).withPhone("12345678").build();

        // Create another person with the same name
        Person anotherPerson = new PersonBuilder().withName(name.toString()).withPhone("98765432").build();

        assertTrue(person.isSameName(anotherPerson.getName()));
    }

    @Test
    public void isSameName_sameNameDifferentObject_returnsTrue() {
        Name name1 = new Name("James");
        Name name2 = new Name("James");
        Person person1 = new PersonBuilder().withName(name1.toString()).withPhone("99999999").build();
        Person person2 = new PersonBuilder().withName(name2.toString()).withPhone("12345678").build();

        assertTrue(person1.isSameName(person2.getName()));
    }

    @Test
    public void isSameName_differentName_returnsFalse() {
        Name name1 = new Name("Green Blue");
        Name name2 = new Name("Blue Green");
        Person person = new PersonBuilder().withName(name1.toString()).withPhone("12345678").build();
        // Create another person with a different name
        Person anotherPerson = new PersonBuilder().withName(name2.toString()).withPhone("12345678").build();

        assertFalse(person.isSameName(anotherPerson.getName()));
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
        editedAlice = new PersonBuilder(ALICE).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", telegramHandle=" + ALICE.getTelegramHandle()
                + ", tags=" + ALICE.getTags()
                + ", gradedTests=" + ALICE.getGradedTest() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
