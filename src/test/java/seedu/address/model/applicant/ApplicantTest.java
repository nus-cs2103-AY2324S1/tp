package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicantBuilder;

public class ApplicantTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Applicant applicant = new ApplicantBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> applicant.getTags().remove(0));
    }

    @Test
    public void isSameApplicant() {
        // same object -> returns true
        assertTrue(ALICE.isSameApplicant(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplicant(null));

        // same name, all other attributes different -> returns true
        Applicant editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameApplicant(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameApplicant(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Applicant editedBob = new ApplicantBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameApplicant(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ApplicantBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameApplicant(editedBob));
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

        // different address -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Applicant.class.getCanonicalName()
                + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress()
                + ", tags=" + ALICE.getTags() + ", hasInterview=" + ALICE.hasInterview() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
