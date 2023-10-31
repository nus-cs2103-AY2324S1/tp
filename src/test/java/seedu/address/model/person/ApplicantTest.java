package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalApplicants.ALICE_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.BOB_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicantBuilder;

public class ApplicantTest {

    @Test
    public void isSameApplicant() {
        // same object -> returns true
        assertTrue(ALICE_APPLICANT.isSamePerson(ALICE_APPLICANT));

        // null -> returns false
        assertFalse(ALICE_APPLICANT.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withPhone(VALID_PHONE_BOB)
                .build();
        assertTrue(ALICE_APPLICANT.isSamePerson(editedAlice));

        // same phone, all other attributes different -> returns true
        editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB)
                .build();
        assertTrue(ALICE_APPLICANT.isSamePerson(editedAlice));

        // different name and phone -> returns false
        editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE_APPLICANT.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Applicant aliceCopy = new ApplicantBuilder(ALICE_APPLICANT).build();
        assertTrue(ALICE_APPLICANT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_APPLICANT.equals(ALICE_APPLICANT));

        // null -> returns false
        assertFalse(ALICE_APPLICANT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_APPLICANT.equals(5));

        // different applicant -> returns false
        assertFalse(ALICE_APPLICANT.equals(BOB_APPLICANT));

        // different name -> returns false
        Applicant editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_APPLICANT.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE_APPLICANT.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Applicant.class.getCanonicalName()
                + "{name=" + ALICE_APPLICANT.getName()
                + ", phone=" + ALICE_APPLICANT.getPhone() + "}";
        assertEquals(expected, ALICE_APPLICANT.toString());
    }

    @Test
    public void detailsToCopyMethod() {
        String expected = "Name: " + ALICE_APPLICANT.getName() + "\n"
                + "Phone: " + ALICE_APPLICANT.getPhone();
        assertEquals(expected, ALICE_APPLICANT.detailsToCopy());
    }
}
