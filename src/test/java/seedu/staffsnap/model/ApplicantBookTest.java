package seedu.staffsnap.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_TYPE_BEHAVIORAL;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.exceptions.DuplicateApplicantException;
import seedu.staffsnap.testutil.ApplicantBuilder;

public class ApplicantBookTest {

    private final ApplicantBook applicantBook = new ApplicantBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), applicantBook.getApplicantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> applicantBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyApplicantBook_replacesData() {
        ApplicantBook newData = getTypicalApplicantBook();
        applicantBook.resetData(newData);
        assertEquals(newData, applicantBook);
    }

    @Test
    public void resetData_withDuplicateApplicants_throwsDuplicateApplicantException() {
        // Two applicants with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE)
                .withPosition(VALID_POSITION_BOB).withInterviews(VALID_TYPE_BEHAVIORAL).build();
        List<Applicant> newApplicants = Arrays.asList(ALICE, editedAlice);
        ApplicantBookStub newData = new ApplicantBookStub(newApplicants);

        assertThrows(DuplicateApplicantException.class, () -> applicantBook.resetData(newData));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> applicantBook.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInApplicantBook_returnsFalse() {
        assertFalse(applicantBook.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInApplicantBook_returnsTrue() {
        applicantBook.addApplicant(ALICE);
        assertTrue(applicantBook.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInApplicantBook_returnsTrue() {
        applicantBook.addApplicant(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE)
                .withPosition(VALID_POSITION_BOB).withInterviews(VALID_TYPE_BEHAVIORAL).build();
        assertTrue(applicantBook.hasApplicant(editedAlice));
    }

    @Test
    public void getApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> applicantBook.getApplicantList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ApplicantBook.class.getCanonicalName()
                + "{applicants=" + applicantBook.getApplicantList() + "}";
        assertEquals(expected, applicantBook.toString());
    }

    /**
     * A stub ReadOnlyApplicantBook whose applicants list can violate interface constraints.
     */
    private static class ApplicantBookStub implements ReadOnlyApplicantBook {
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();

        ApplicantBookStub(Collection<Applicant> applicants) {
            this.applicants.setAll(applicants);
        }

        @Override
        public ObservableList<Applicant> getApplicantList() {
            return applicants;
        }
    }

}
