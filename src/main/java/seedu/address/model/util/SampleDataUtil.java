package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Member[] getSampleMembers() {
        return new Member[]{
                new Member(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Telegram("alexyeoh"),
                        getTagSet("friends")),
                new Member(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Telegram("berniceyu"),
                        getTagSet("colleagues", "friends")),
                new Member(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Telegram("charlotteoliveiro"),
                        getTagSet("neighbours")),
                new Member(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Telegram("davidli"),
                        getTagSet("family")),
                new Member(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Telegram("irfanibrahim"),
                        getTagSet("classmates")),
                new Member(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Telegram("roybalakrishnan"),
                        getTagSet("colleagues"))
        };
    }

    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
                new Applicant(new Name("Alex Yeoh"), new Phone("87438807")),
                new Applicant(new Name("Bernice Yu"), new Phone("99272758")),
                new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283")),
                new Applicant(new Name("David Li"), new Phone("91031282")),
                new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021")),
                new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Member sampleMember : getSampleMembers()) {
            sampleAb.addMember(sampleMember);
        }

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
