package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.job.Company;
import seedu.address.model.job.Job;
import seedu.address.model.job.Remark;
import seedu.address.model.job.Role;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");
    public static Job[] getSampleJobs() {
        return new Job[] {
            new Job(new Role("Software Engineer"), new Company("Google"), EMPTY_REMARK),
            new Job(new Role("Cleaner"), new Company("NUS"), EMPTY_REMARK),
            new Job(new Role("Chef"), new Company("McDonalds"), EMPTY_REMARK)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Job sampleJob : getSampleJobs()) {
            sampleAb.addJob(sampleJob);
        }
        return sampleAb;
    }

}
