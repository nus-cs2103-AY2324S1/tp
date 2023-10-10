package seedu.application.model.util;

import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.job.Company;
import seedu.application.model.job.Job;
import seedu.application.model.job.Role;

/**
 * Contains utility methods for populating {@code ApplicationBook} with sample data.
 */
public class SampleDataUtil {
    public static Job[] getSampleJobs() {
        return new Job[] {
            new Job(new Role("Software Engineer"), new Company("Google")),
            new Job(new Role("Cleaner"), new Company("NUS")),
            new Job(new Role("Chef"), new Company("McDonalds"))
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
