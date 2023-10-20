package seedu.staffsnap.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.ReadOnlyApplicantBook;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.interview.Interview;

/**
 * Contains utility methods for populating {@code ApplicantBook} with sample data.
 */
public class SampleDataUtil {
    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Position("Software Engineer"),
                getInterviewList("screening")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Position("Testing Engineer"),
                getInterviewList("technical", "screening")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Position("Software Engineer"),
                getInterviewList("behavioral")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Position("Staff Engineer"),
                getInterviewList("technical")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Position("DevOps Engineer"),
                getInterviewList("technical", "behavioral")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Position("Software Engineer"),
                getInterviewList("screening", "technical", "behavioral")),
        };
    }

    public static ReadOnlyApplicantBook getSampleApplicantBook() {
        ApplicantBook sampleAb = new ApplicantBook();
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleAb.addApplicant(sampleApplicant);
        }
        return sampleAb;
    }

    /**
     * Returns a interview set containing the list of strings given.
     */
    public static List<Interview> getInterviewList(String... strings) {
        return Arrays.stream(strings)
                .map(Interview::new)
                .collect(Collectors.toList());
    }

}
