package seedu.cc.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_ASTHMA;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalPatients.ALICE;
import static seedu.cc.testutil.TypicalPatients.BOB;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.PatientBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PatientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAge(VALID_AGE_BOB).withTags(VALID_TAG_ASTHMA).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PatientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PatientBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PatientBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different person -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Person editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new PatientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different age -> returns false
        editedAlice = new PatientBuilder(ALICE).withAge(VALID_AGE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different tags -> returns false
        editedAlice = new PatientBuilder(ALICE).withTags(VALID_TAG_ASTHMA).build();
        assertNotEquals(ALICE, editedAlice);
    }

    @Test
    public void toStringMethod() {
        String expected = Patient.class.getCanonicalName() + "{name=" + ALICE.getName() + ", nric=" + ALICE.getNric()
                + ", phone=" + ALICE.getPhone() + ", email=" + ALICE.getEmail() + ", age=" + ALICE.getAge() + ", tags="
                + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    void constructor_allFieldsNonNull_constructsSuccessfully() {
        // Assume Name, Phone, and Email are classes with a single String argument constructor
        Name testName = new Name("John Doe");
        Phone testPhone = new Phone("12345678");
        Email testEmail = new Email("john@example.com");
        Age testAge = new Age("20");

        // Creating a Person using constructor
        Person testPerson = new Person(testName, testPhone, testEmail, testAge, new HashSet<>());

        // Check that fields are correctly initialized
        assertEquals(testName, testPerson.getName());
        assertEquals(testPhone, testPerson.getPhone());
        assertEquals(testEmail, testPerson.getEmail());

        // Check that default/alternative values are set
        assertEquals(0, testPerson.getTags().size());
    }

    @Test
    void constructor_nullName_throwsNullPointerException() {
        // Assume Phone and Email are classes with a single String argument constructor
        Phone testPhone = new Phone("12345678");
        Email testEmail = new Email("john@example.com");

        // Assert that passing a null Name triggers a NullPointerException
        assertThrows(NullPointerException.class, () -> new Person(null, testPhone, testEmail));
    }
}
