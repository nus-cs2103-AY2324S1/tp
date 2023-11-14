package connectify.model.person;

import static connectify.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static connectify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static connectify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static connectify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static connectify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalPersons.ALICE;
import static connectify.testutil.TypicalPersons.BOB;
import static connectify.testutil.TypicalPersons.GEORGE;
import static connectify.testutil.TypicalPersons.HOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.testutil.PersonBuilder;
import connectify.testutil.TypicalCompanies;

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

        // same everything except company -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withParentCompany(TypicalCompanies.COMPANY_3).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same everything but company is null -> returns true
        editedAlice = new PersonBuilder(ALICE).withParentCompany(null).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
        assertTrue(editedAlice.isSamePerson(ALICE));

        // both companies are null, everything else is the same -> returns true
        editedAlice = new PersonBuilder(ALICE).withParentCompany(null).build();
        Person editedAlice2 = new PersonBuilder(ALICE).withParentCompany(null).build();
        assertTrue(editedAlice.isSamePerson(editedAlice2));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
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

        // different company -> returns false
        aliceCopy = new PersonBuilder(ALICE).withParentCompany(TypicalCompanies.COMPANY_3).build();
        assertFalse(ALICE.equals(aliceCopy));

        // one company null, everything else the same -> returns false
        aliceCopy = new PersonBuilder(ALICE).withParentCompany(null).build();
        assertFalse(ALICE.equals(aliceCopy));
        assertFalse(aliceCopy.equals(ALICE));

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
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", note=" + ALICE.getNote()
                + ", priority=" + ALICE.getPriority() + ", company="
                + ALICE.getParentCompany().getName()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void compareNameWith() {
        assertEquals(ALICE.compareNameWith(BOB), -1);
        assertEquals(ALICE.compareNameWith(ALICE), 0);
        assertEquals(HOON.compareNameWith(GEORGE), 1);
    }

    @Test
    public void rank() {
        assertEquals(ALICE.rank(), 1);
        assertEquals(HOON.rank(), 7);
    }
}
