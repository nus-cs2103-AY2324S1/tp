package seedu.lovebook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.lovebook.testutil.TypicalPersons.ALICE;
import static seedu.lovebook.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.lovebook.testutil.PersonBuilder;

public class DateTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Date editedAlice = new PersonBuilder(ALICE).withAge(VALID_AGE_BOB).withGender(VALID_GENDER_BOB)
                .withHeight(VALID_HEIGHT_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Date editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Date aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different date -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Date editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different age -> returns false
        editedAlice = new PersonBuilder(ALICE).withAge(VALID_AGE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different gender -> returns false
        editedAlice = new PersonBuilder(ALICE).withGender(VALID_GENDER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different lovebook -> returns false
        editedAlice = new PersonBuilder(ALICE).withHeight(VALID_HEIGHT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Date.class.getCanonicalName() + "{name=" + ALICE.getName() + ", age=" + ALICE.getAge()
                + ", gender=" + ALICE.getGender() + ", height=" + ALICE.getHeight() + ", income=" + ALICE.getIncome()
                + "}";
        assertEquals(expected, ALICE.toString());
    }
}
