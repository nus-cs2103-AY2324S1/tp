package seedu.staffsnap.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.staffsnap.model.AddressBook;
import seedu.staffsnap.model.ReadOnlyAddressBook;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Department;
import seedu.staffsnap.model.applicant.JobTitle;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Department("alexyeoh@example.com"),
                new JobTitle("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Department("berniceyu@example.com"),
                new JobTitle("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Department("charlotte@example.com"), new JobTitle("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Department("lidavid@example.com"),
                new JobTitle("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Department("irfan@example.com"),
                new JobTitle("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Department("royb@example.com"),
                new JobTitle("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleAb.addApplicant(sampleApplicant);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
