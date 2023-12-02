package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Company;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Remark;
import seedu.address.model.company.Role;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Company[] getSampleCompanies() {
        return new Company[] {
            new Company(new Name("Google"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Role("Software Engineer"), new Deadline("10-11-2023"),
                    new ApplicationStatus("PA"), new RecruiterName("Francis Tan"),
                    new Priority("LOW"), new Remark("Java/Python")),
            new Company(new Name("Tiktok"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Role("Software Engineer"), new Deadline("20-12-2023"),
                    new ApplicationStatus("PA"), new RecruiterName("Alice Tan"),
                    new Priority("MEDIUM"), new Remark("No remarks")),
            new Company(new Name("DSTA"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Role("Software Engineer"), new Deadline("01-12-2023"),
                    new ApplicationStatus("PA"), new RecruiterName("Eve Lim"),
                    new Priority("HIGH"), new Remark("Java")),
            new Company(new Name("Microsoft"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Role("Data Analyst"), new Deadline("18-11-2023"),
                    new ApplicationStatus("PA"), new RecruiterName("Jovie Tan"),
                    new Priority("NONE"), new Remark("www.bing.com")),
            new Company(new Name("Meta"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Role("Data Analyst"), new Deadline("19-11-2023"),
                    new ApplicationStatus("PA"), new RecruiterName("Poppins Lam"),
                    new Priority("LOW"), new Remark("No remarks")),
            new Company(new Name("Amazon"), new Phone("92624417"), new Email("royb@example.com"),
                    new Role("Web Developer"), new Deadline("20-11-2023"),
                    new ApplicationStatus("PA"), new RecruiterName("Jarvis Koh"),
                    new Priority("NONE"), new Remark("JavaScript&CSS"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }
        return sampleAb;
    }
}
