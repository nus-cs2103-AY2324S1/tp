package seedu.staffsnap.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_HUSBAND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.AMY;
import static seedu.staffsnap.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.testutil.ApplicantBuilder;

public class ApplicantTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Applicant applicant = new ApplicantBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> applicant.getInterviews().remove(0));
    }

    @Test
    public void isSameApplicant() {
        // same object -> returns true
        assertTrue(AMY.isSameApplicant(AMY));

        // null -> returns false
        assertFalse(AMY.isSameApplicant(null));

        // same phone number, all other attributes different -> returns true
        Applicant editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(BOB.isSameApplicant(editedAlice));

        // same email, all other attributes different -> returns true
        editedAlice = new ApplicantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(BOB.isSameApplicant(editedAlice));

        // same email and same phone number, all other attributes different -> returns true
        editedAlice = new ApplicantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withPhone(VALID_PHONE_BOB).build();
        assertTrue(BOB.isSameApplicant(editedAlice));

        // different phone number and email, all other attributes same -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSameApplicant(editedAlice));

        // email differs in case, all other attributes same -> returns true
        Applicant editedBob = new ApplicantBuilder(BOB).withEmail(VALID_EMAIL_BOB.toUpperCase()).build();
        assertTrue(BOB.isSameApplicant(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ApplicantBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameApplicant(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Applicant aliceCopy = new ApplicantBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different applicant -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Applicant editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different position -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withPosition(VALID_POSITION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different interviews -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withInterviews(VALID_INTERVIEW_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Applicant.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", phone=" + ALICE.getPhone() + ", email=" + ALICE.getEmail()
                + ", position=" + ALICE.getPosition() + ", interviews=" + ALICE.getInterviews() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
