package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.application.model.job.Job;
import seedu.application.model.job.exceptions.DuplicateJobException;
import seedu.application.testutil.JobBuilder;

public class ApplicationBookTest {

    private final ApplicationBook applicationBook = new ApplicationBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), applicationBook.getJobList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> applicationBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyApplicationBook_replacesData() {
        ApplicationBook newData = getTypicalApplicationBook();
        applicationBook.resetData(newData);
        assertEquals(newData, applicationBook);
    }

    // To be implemented when non-identity fields are created
    @Test
    public void resetData_withDuplicateJobs_throwsDuplicateJobException() {
        // Two jobs with the same identity fields
        Job editedChef = new JobBuilder(CHEF).build();
        List<Job> newJobs = Arrays.asList(CHEF, editedChef);
        ApplicationBookStub newData = new ApplicationBookStub(newJobs);

        assertThrows(DuplicateJobException.class, () -> applicationBook.resetData(newData));
    }

    @Test
    public void hasJob_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> applicationBook.hasJob(null));
    }

    @Test
    public void hasJob_jobNotInApplicationBook_returnsFalse() {
        assertFalse(applicationBook.hasJob(CHEF));
    }

    @Test
    public void hasJob_jobInApplicationBook_returnsTrue() {
        applicationBook.addJob(CHEF);
        assertTrue(applicationBook.hasJob(CHEF));
    }

    // To be implemented when non-identity fields are created
    @Test
    public void hasJob_jobWithSameIdentityFieldsInApplicationBook_returnsTrue() {
        applicationBook.addJob(CHEF);
        Job editedChef = new JobBuilder(CHEF).build();
        assertTrue(applicationBook.hasJob(editedChef));
    }

    @Test
    public void getJobList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> applicationBook.getJobList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ApplicationBook.class.getCanonicalName() + "{jobs=" + applicationBook.getJobList() + "}";
        assertEquals(expected, applicationBook.toString());
    }

    /**
     * A stub ReadOnlyApplicationBook whose jobs list can violate interface constraints.
     */
    private static class ApplicationBookStub implements ReadOnlyApplicationBook {
        private final ObservableList<Job> jobs = FXCollections.observableArrayList();

        ApplicationBookStub(Collection<Job> jobs) {
            this.jobs.setAll(jobs);
        }

        @Override
        public ObservableList<Job> getJobList() {
            return jobs;
        }
    }

}
