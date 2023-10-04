package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.CHEF;
import static seedu.address.testutil.TypicalJobs.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.job.Job;
import seedu.address.model.job.exceptions.DuplicateJobException;
import seedu.address.testutil.JobBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getJobList());
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

    // To be implemented when non-identity fields are created
    @Test
    public void resetData_withDuplicateJobs_throwsDuplicateJobException() {
        // Two jobs with the same identity fields
        Job editedChef = new JobBuilder(CHEF).build();
        List<Job> newJobs = Arrays.asList(CHEF, editedChef);
        AddressBookStub newData = new AddressBookStub(newJobs);

        assertThrows(DuplicateJobException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasJob_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasJob(null));
    }

    @Test
    public void hasJob_jobNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasJob(CHEF));
    }

    @Test
    public void hasJob_jobInAddressBook_returnsTrue() {
        addressBook.addJob(CHEF);
        assertTrue(addressBook.hasJob(CHEF));
    }

    // To be implemented when non-identity fields are created
    @Test
    public void hasJob_jobWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addJob(CHEF);
        Job editedChef = new JobBuilder(CHEF).build();
        assertTrue(addressBook.hasJob(editedChef));
    }

    @Test
    public void getJobList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getJobList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{jobs=" + addressBook.getJobList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose jobs list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Job> jobs = FXCollections.observableArrayList();

        AddressBookStub(Collection<Job> jobs) {
            this.jobs.setAll(jobs);
        }

        @Override
        public ObservableList<Job> getJobList() {
            return jobs;
        }
    }

}
