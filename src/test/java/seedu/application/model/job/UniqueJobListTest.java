package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.CLEANER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.model.job.exceptions.DuplicateJobException;
import seedu.application.model.job.exceptions.JobNotFoundException;
import seedu.application.testutil.JobBuilder;

public class UniqueJobListTest {

    private final UniqueJobList uniqueJobList = new UniqueJobList();

    @Test
    public void contains_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.contains(null));
    }

    @Test
    public void contains_jobNotInList_returnsFalse() {
        assertFalse(uniqueJobList.contains(CHEF));
    }

    @Test
    public void contains_jobInList_returnsTrue() {
        uniqueJobList.add(CHEF);
        assertTrue(uniqueJobList.contains(CHEF));
    }

    // To be implemented when non-identity fields are created
    @Test
    public void contains_jobWithSameIdentityFieldsInList_returnsTrue() {
        uniqueJobList.add(CHEF);
        Job editedAlice = new JobBuilder(CHEF).build();
        assertTrue(uniqueJobList.contains(editedAlice));
    }

    @Test
    public void add_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.add(null));
    }

    @Test
    public void add_duplicateJob_throwsDuplicateJobException() {
        uniqueJobList.add(CHEF);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.add(CHEF));
    }

    @Test
    public void setJob_nullTargetJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(null, CHEF));
    }

    @Test
    public void setJob_nullEditedJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(CHEF, null));
    }

    @Test
    public void setJob_targetJobNotInList_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.setJob(CHEF, CHEF));
    }

    @Test
    public void setJob_editedJobIsSameJob_success() {
        uniqueJobList.add(CHEF);
        uniqueJobList.setJob(CHEF, CHEF);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(CHEF);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    // To be implemented when non-identity fields are created
    @Test
    public void setJob_editedJobHasSameIdentity_success() {
        uniqueJobList.add(CHEF);
        Job editedAlice = new JobBuilder(CHEF)
                .build();
        uniqueJobList.setJob(CHEF, editedAlice);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(editedAlice);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasDifferentIdentity_success() {
        uniqueJobList.add(CHEF);
        uniqueJobList.setJob(CHEF, CLEANER);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(CLEANER);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasNonUniqueIdentity_throwsDuplicateJobException() {
        uniqueJobList.add(CHEF);
        uniqueJobList.add(CLEANER);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.setJob(CHEF, CLEANER));
    }

    @Test
    public void remove_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.remove(null));
    }

    @Test
    public void remove_jobDoesNotExist_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.remove(CHEF));
    }

    @Test
    public void remove_existingJob_removesJob() {
        uniqueJobList.add(CHEF);
        uniqueJobList.remove(CHEF);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullUniqueJobList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((UniqueJobList) null));
    }

    @Test
    public void setJobs_uniqueJobList_replacesOwnListWithProvidedUniqueJobList() {
        uniqueJobList.add(CHEF);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(CLEANER);
        uniqueJobList.setJobs(expectedUniqueJobList);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((List<Job>) null));
    }

    @Test
    public void setJobs_list_replacesOwnListWithProvidedList() {
        uniqueJobList.add(CHEF);
        List<Job> jobList = Collections.singletonList(CLEANER);
        uniqueJobList.setJobs(jobList);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(CLEANER);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_listWithDuplicateJobs_throwsDuplicateJobException() {
        List<Job> listWithDuplicateJobs = Arrays.asList(CHEF, CHEF);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.setJobs(listWithDuplicateJobs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueJobList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueJobList.asUnmodifiableObservableList().toString(), uniqueJobList.toString());
    }
}
