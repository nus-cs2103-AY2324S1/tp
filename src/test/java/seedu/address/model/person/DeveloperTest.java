package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalDevelopers.ALICE;
import static seedu.address.testutil.TypicalDevelopers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.developer.Developer;
import seedu.address.testutil.DeveloperBuilder;

public class DeveloperTest {

    /*
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Developer developer = new DeveloperBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> developer.getTags().remove(0));
    }
    */

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameDeveloper(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Developer editedAlice = new DeveloperBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new DeveloperBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Developer editedBob = new DeveloperBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new DeveloperBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Developer aliceCopy = new DeveloperBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different developer -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Developer editedAlice = new DeveloperBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new DeveloperBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new DeveloperBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different address -> returns false
        editedAlice = new DeveloperBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ALICE, editedAlice);
    }

    @Test
    public void toStringMethod() {
        String expected = Developer.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone="
                + ALICE.getPhone() + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress()
                + ", projects=" + ALICE.getProjects() + "} Salary: " + ALICE.getSalary()
                + " Date Joined: " + ALICE.getDateJoined() + " Github ID: " + ALICE.getGithubId()
                + " Rating: " + ALICE.getRating();
        assertEquals(expected, ALICE.toString());
    }
}
