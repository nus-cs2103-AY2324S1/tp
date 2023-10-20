package seedu.application.model.util;

import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.job.*;

/**
 * Contains utility methods for populating {@code ApplicationBook} with sample data.
 */
public class SampleDataUtil {


    public static Job[] getSampleJobs() {
        return new Job[]{
            new Job(new Role("Software Engineer"), new Company("Google"),
                    Status.DEFAULT_STATUS, Deadline.EMPTY_DEADLINE, JobType.EMPTY_JOB_TYPE),
            new Job(new Role("Cleaner"), new Company("NUS"),
                    Status.DEFAULT_STATUS, Deadline.EMPTY_DEADLINE, JobType.EMPTY_JOB_TYPE),
            new Job(new Role("Chef"), new Company("McDonalds"),
                    Status.DEFAULT_STATUS, Deadline.EMPTY_DEADLINE, JobType.EMPTY_JOB_TYPE),
        };
    }

    public static ReadOnlyApplicationBook getSampleApplicationBook() {
        ApplicationBook sampleAb = new ApplicationBook();
        for (Job sampleJob : getSampleJobs()) {
            sampleAb.addJob(sampleJob);
        }
        return sampleAb;
    }

}
