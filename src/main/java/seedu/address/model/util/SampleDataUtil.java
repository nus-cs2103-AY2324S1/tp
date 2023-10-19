package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ScheduleList;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final String SAMPLE_SCHEDULE = "Mon 1200 1400 weekly";
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getSubjectSet("CHEMISTRY", "BIOLOGY"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getSubjectSet("English", "MATHEMATICS"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getSubjectSet("Chemistry", "PHYSICS"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getSubjectSet("Biology"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), getSubjectSet("English", "MATHEMATICS"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), getSubjectSet("Chemistry"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

    /**
     * Returns a subject set containing the list of strings given.
     */
    public static Set<Subject> getSubjectSet(String... strings) {
        return Arrays.stream(strings)
                .map(Subject::new)
                .collect(Collectors.toSet());
    }

    public static Lesson[] getSampleLessons() {
        LocalDateTime startLesson1 = LocalDateTime.of(2022, 10, 10, 14, 30);
        LocalDateTime endLesson1 = LocalDateTime.of(2022, 10, 10, 16, 30);
        LocalDateTime startLesson2 = LocalDateTime.of(2022, 10, 20, 14, 30);
        LocalDateTime endLesson2 = LocalDateTime.of(2022, 10, 18, 16, 30);
        LocalDateTime startLesson3 = LocalDateTime.of(2022, 11, 20, 10, 30);
        LocalDateTime endLesson3 = LocalDateTime.of(2022, 10, 18, 12, 30);
        Subject math = new Subject("MATHEMATICS");
        Subject bio = new Subject("BIOLOGY");
        return new Lesson[] {
            new Lesson(startLesson1, endLesson1, math, "Alex Yeoh"),
            new Lesson(startLesson2, endLesson2, math, "David Li"),
            new Lesson(startLesson3, endLesson3, bio, "Bernice Yu"),
        };
    }
    public static ReadOnlySchedule getSampleSchedule() {
        ScheduleList sampleSchedule = new ScheduleList();
        for (Lesson sampleLesson : getSampleLessons()) {
            sampleSchedule.addLesson(sampleLesson);
        }
        return sampleSchedule;
    }


}
