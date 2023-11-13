package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHIFTDAYS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_AMY;
import static seedu.address.testutil.TypicalDoctors.ALICE;
import static seedu.address.testutil.TypicalDoctors.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.TypicalPersons;

public class DoctorTest {

    @Test
    public void asObservableList_modifyList_success() {
        Doctor doctor = new DoctorBuilder().build();
        doctor.getAffiliations().add(new Affiliation(VALID_AFFILIATION_AMY));
        assertTrue(doctor.getAffiliations().contains(new Affiliation(VALID_AFFILIATION_AMY)));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Doctor editedAlice = new DoctorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAffiliations(VALID_AFFILIATION_AMY)
                .withAffiliationHistory(VALID_AFFILIATION_AMY).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new DoctorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Doctor editedBob = new DoctorBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new DoctorBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Doctor aliceCopy = new DoctorBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));
        assertTrue(BOB.equals(BOB));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different doctor -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Doctor editedAlice = new DoctorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DoctorBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new DoctorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different affiliations -> returns false
        editedAlice = new DoctorBuilder(ALICE).withAffiliations(VALID_AFFILIATION_AMY)
                .withAffiliationHistory(VALID_AFFILIATION_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different shift days -> return false
        editedAlice = new DoctorBuilder(ALICE).withShiftDays(VALID_SHIFTDAYS_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different specialisations -> return false
        editedAlice = new DoctorBuilder(ALICE).withSpecialisations(VALID_SPECIALISATION_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        //different class instances -> return false
        assertFalse(TypicalPersons.ALICE.hashCode() == aliceCopy.hashCode());
    }

    @Test
    public void toStringMethod() {
        System.out.println(ALICE.toString());
        String expected = Doctor.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", role=" + ALICE.getRole() + ", affiliations="
                + ALICE.getAffiliations() + ", affiliationHistory=" + ALICE.getAffiliationHistory()
                + ", shiftDays=" + ALICE.getShiftDays()
                + ", specialisations=" + ALICE.getSpecialisations() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
