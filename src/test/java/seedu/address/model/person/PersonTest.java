package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

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

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB).withClassNumber(VALID_CLASS_NUMBER_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same student number, all other attributes different -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withName(VALID_NAME_BOB).withClassNumber(VALID_CLASS_NUMBER_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different student number, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withStudentNumber(VALID_STUDENT_NUMBER_AMY).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // student number differs in case, all other attributes same -> returns True
        Person editedBob = new PersonBuilder(BOB).withStudentNumber(VALID_STUDENT_NUMBER_BOB.toLowerCase())
                .build();
        assertTrue(BOB.isSamePerson(editedBob));

        // student number has trailing spaces, all other attributes same -> returns True
        String studentNumberWithTrailingSpaces = VALID_STUDENT_NUMBER_BOB + " ";
        editedBob = new PersonBuilder(BOB).withStudentNumber(studentNumberWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));
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

        // different student number -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withStudentNumber(VALID_STUDENT_NUMBER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", student number=" + ALICE.getStudentNumber()
                + ", class number=" + ALICE.getClassNumber() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
