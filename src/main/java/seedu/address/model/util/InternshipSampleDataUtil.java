package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InternshipBook;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;

/**
 * Contains utility methods for populating {@code InternshipBook} with sample data.
 */
public class InternshipSampleDataUtil {

    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new CompanyName("Google"), new Role("SWE Intern"),
                    new ApplicationStatus("Applied"), new Deadline("15/12/2022", "20/01/2023"),
                    new StartDate("20/01/2023"),
                    new Duration("3"), getRequirementSet("Java", "Python")),
            new Internship(new CompanyName("Facebook"), new Role("Research Intern"),
                    new ApplicationStatus("Yet to apply"), new Deadline("14/09/2022", "15/02/2023"),
                    new StartDate("15/02/2023"),
                    new Duration("4"), getRequirementSet("Machine Learning", "Python")),
            new Internship(new CompanyName("Microsoft"), new Role("DevOps Intern"),
                    new ApplicationStatus("Accepted"), new Deadline("30/12/2022", "01/03/2023"),
                    new StartDate("01/03/2023"),
                    new Duration("6"), getRequirementSet("Azure", "Shell Scripting")),
            new Internship(new CompanyName("Amazon"), new Role("Web Development Intern"),
                    new ApplicationStatus("Rejected"), new Deadline("15/12/2022", "01/04/2023"),
                    new StartDate("01/04/2023"),
                    new Duration("2"), getRequirementSet("React", "Node.js")),
            new Internship(new CompanyName("Netflix"), new Role("Data Science Intern"),
                    new ApplicationStatus("In progress"), new Deadline("15/06/2022", "10/03/2023"),
                    new StartDate("10/03/2023"),
                    new Duration("5"), getRequirementSet("Python", "Statistics")),
        };
    }

    public static ReadOnlyInternshipBook getSampleInternshipBook() {
        InternshipBook sampleIb = new InternshipBook();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleIb.addInternship(sampleInternship);
        }
        return sampleIb;
    }

    /**
     * Returns a requirement set containing the list of strings given.
     */
    public static Set<Requirement> getRequirementSet(String... strings) {
        return Arrays.stream(strings)
                .map(Requirement::new)
                .collect(Collectors.toSet());
    }
}
