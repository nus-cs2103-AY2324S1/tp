package seedu.address.model.person.patients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TREATMENT_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.PATIENT_ALICE;
import static seedu.address.testutil.TypicalPatients.PATIENT_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

class PatientTest {

    @Test
    public void asObservableList_modifyPatientList_throwsUnsupportedOperationException() {
        Patient patient = new PatientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> patient.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(PATIENT_ALICE.isSamePatient(PATIENT_ALICE));

        // null -> returns false
        assertFalse(PATIENT_ALICE.isSamePatient(null));

        // same name, all other attributes different -> returns true
        Patient editedAlice = new PatientBuilder(PATIENT_ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(PATIENT_ALICE.isSamePatient(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(PATIENT_ALICE.isSamePatient(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Patient editedBob = new PatientBuilder(PATIENT_BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(PATIENT_BOB.isSamePatient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PatientBuilder(PATIENT_BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(PATIENT_BOB.isSamePatient(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(PATIENT_ALICE).build();
        //assertTrue(PATIENT_ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(PATIENT_ALICE.equals(PATIENT_ALICE));

        // null -> returns false
        assertFalse(PATIENT_ALICE.equals(null));

        // different type -> returns false
        assertFalse(PATIENT_ALICE.equals(5));

        // different Patient -> returns false
        assertFalse(PATIENT_ALICE.equals(PATIENT_BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(PATIENT_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different gender -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withGender(VALID_GENDER_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different birthdate -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withBirthdate(VALID_BIRTHDATE_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different remarks -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withRemark(VALID_REMARK_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different treatment -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withTreatmentName(VALID_TREATMENT_BOB).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PatientBuilder(PATIENT_ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(PATIENT_ALICE.equals(editedAlice));
    }

    @Test
    public void hashCodeTest() {
        // same object -> returns true
        assertEquals(PATIENT_ALICE.hashCode(), PATIENT_ALICE.hashCode());

        // equal objects -> returns true
        Patient aliceCopy = new PatientBuilder(PATIENT_ALICE).build();
        assertEquals(PATIENT_ALICE.hashCode(), aliceCopy.hashCode());

        // non-equal objects -> returns false
        assertNotEquals(PATIENT_ALICE.hashCode(), PATIENT_BOB.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Patient.class.getCanonicalName() + "{name=" + PATIENT_ALICE.getName()
                + ", phone=" + PATIENT_ALICE.getPhone()
                + ", birthday=" + PATIENT_ALICE.getBirthdate()
                + ", gender=" + PATIENT_ALICE.getGender()
                + ", remark=" + PATIENT_ALICE.getRemark()
                + ", treatment=" + PATIENT_ALICE.getTreatmentName()
                + ", address=" + PATIENT_ALICE.getAddress()
                + ", email=" + PATIENT_ALICE.getEmail()
                + ", id=" + PATIENT_ALICE.getId()
                + ", tags=" + PATIENT_ALICE.getTags() + "}";
        assertEquals(expected, PATIENT_ALICE.toString());
    }
}
