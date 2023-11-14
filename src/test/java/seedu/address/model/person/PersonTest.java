package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SM_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SM_LINKEDIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getSocialMediaLinks().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same email, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB)
                .withMajor(VALID_MAJOR_BOB).withYear(VALID_YEAR_BOB)
                .withDescription(VALID_DESCRIPTION_BOB)
                .withSocialMediaLinks(VALID_SM_LINKEDIN_BOB, VALID_SM_GITHUB_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different email, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // email differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).build();
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

        // different major -> returns false
        editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different year -> returns false
        editedAlice = new PersonBuilder(ALICE).withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different description -> returns false
        editedAlice = new PersonBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different social media links -> returns false
        editedAlice = new PersonBuilder(ALICE).withSocialMediaLinks(VALID_SM_LINKEDIN_BOB, VALID_SM_GITHUB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashCode_returnsExpectedHashCode() {
        Person aliceCopy = new PersonBuilder(ALICE).build();
        int expectedHashCode = ALICE.hashCode();
        int actualHashCode = aliceCopy.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", major=" + ALICE.getMajor()
                + ", year=" + ALICE.getYear() + ", email=" + ALICE.getEmail() + ", description="
                + ALICE.getDescription() + ", tutorials=" + ALICE.getTutorials()
                + ", socialMediaLinks=" + ALICE.getSocialMediaLinks()
                + ", nationality=" + ALICE.getNationality() + ", gender=" + ALICE.getGender() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void equals_sameTutorials_returnsTrue() {
        // Create two persons with the same tutorials
        Person aliceWithTutorials = new PersonBuilder(ALICE)
            .withTutorials("01", "02")
            .build();
        Person aliceCopyWithTutorials = new PersonBuilder(ALICE)
            .withTutorials("01", "02")
            .build();

        // They should be considered equal even if other fields are different
        assertTrue(aliceWithTutorials.equals(aliceCopyWithTutorials));
    }

}
