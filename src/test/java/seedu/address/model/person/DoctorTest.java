package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.ALICE;
import static seedu.address.testutil.TypicalDoctor.CHERYL;
import static seedu.address.testutil.TypicalDoctor.WAYNE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;



//@Disabled("Disabled since we will be moving Person class to abstract in the future")
public class DoctorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Doctor doctor = new DoctorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> doctor.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same nric, all other attributes different -> returns true
        Doctor editedCheryl = new DoctorBuilder(CHERYL).withPhone(VALID_PHONE_CHERYL).withEmail(VALID_EMAIL_CHERYL)
                .withAddress(VALID_ADDRESS_CHERYL).withGender(VALID_GENDER_FEMALE).withIc(VALID_NRIC_CHERYL).build();
        assertTrue(CHERYL.isSamePerson(editedCheryl));

        // different nric, all other attributes same -> returns false
        editedCheryl = new DoctorBuilder(CHERYL).withIc(VALID_NRIC_BOB).build();
        assertFalse(CHERYL.isSamePerson(editedCheryl));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Doctor aliceCopy = new DoctorBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(WAYNE));

        // different name -> returns false
        Person editedAlice = new DoctorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DoctorBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new DoctorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new DoctorBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new DoctorBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Doctor.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", remark=" + ALICE.getRemark()
                + ", gender=" + ALICE.getGender() + ", nric=" + ALICE.getIc() + ", patients=" + ALICE.getPatients() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
