package seedu.staffsnap.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_HUSBAND;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalAddressBook;

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

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getApplicantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateApplicants_throwsDuplicateApplicantException() {
        // Two applicants with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE)
                .withPosition(VALID_POSITION_BOB).withInterviews(VALID_INTERVIEW_HUSBAND).build();
        List<Applicant> newApplicants = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newApplicants);

        assertThrows(DuplicateApplicantException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInAddressBook_returnsTrue() {
        addressBook.addApplicant(ALICE);
        assertTrue(addressBook.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addApplicant(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE)
                .withPosition(VALID_POSITION_BOB).withInterviews(VALID_INTERVIEW_HUSBAND).build();
        assertTrue(addressBook.hasApplicant(editedAlice));
    }

    @Test
    public void getApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getApplicantList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{applicants=" + addressBook.getApplicantList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose applicants list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();

        AddressBookStub(Collection<Applicant> applicants) {
            this.applicants.setAll(applicants);
        }

        @Override
        public ObservableList<Applicant> getApplicantList() {
            return applicants;
        }
    }

}
