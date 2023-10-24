package seedu.address.model.util;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.course.Course;
import seedu.address.model.course.Lesson;
import seedu.address.model.course.UniqueCourseList;
import seedu.address.model.person.Email;
import seedu.address.model.person.FreeTime;
import seedu.address.model.person.Hour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;
/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 * @formatter:off
 */
public class SampleDataUtil {
    /**
     * Populates the UniqueCourseList sample courses.
     */
    public static void populateSampleCourses() {
        Set<Lesson> cs2103TLessons = new HashSet<>();
        cs2103TLessons.add(new Lesson("G17-L1", DayOfWeek.of(2),
                LocalTime.parse("8:00"), LocalTime.parse("10:00")));
        cs2103TLessons.add(new Lesson("G17-L2", DayOfWeek.of(5),
                LocalTime.parse("8:00"), LocalTime.parse("10:00")));

        Set<Lesson> cs1231SLessons = new HashSet<>();
        cs1231SLessons.add(new Lesson("T17-L1", DayOfWeek.of(2),
                LocalTime.parse("8:00"), LocalTime.parse("10:00")));
        cs1231SLessons.add(new Lesson("T17-L2", DayOfWeek.of(5),
                LocalTime.parse("8:00"), LocalTime.parse("10:00")));
        UniqueCourseList.add(new Course("CS2103T", "Software Engineering", cs2103TLessons));
        UniqueCourseList.add(new Course("CS1231S", "Discrete Structures", cs1231SLessons));
    }
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Telegram("@alexyeoh"),
                        getTagSet("friends"),
                        new FreeTime(LocalTime.parse("11:30"), LocalTime.parse("12:30")),
                        getCourseSet("CS2103T"), new Hour("8")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Telegram("@berrrrrnice"),
                        getTagSet("colleagues", "friends"),
                        new FreeTime(LocalTime.parse("12:30"), LocalTime.parse("13:30")),
                        getCourseSet("CS2103T"), new Hour("2")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Telegram("@heyimcharlotte"),
                        getTagSet("neighbours"),
                        null,
                        getCourseSet("CS2103T"), new Hour("8")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Telegram("@davidli123"),
                        getTagSet("family"),
                        new FreeTime(LocalTime.parse("14:30"), LocalTime.parse("15:30")),
                        getCourseSet("CS2103T"), new Hour("10")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Telegram("@irfannn"),
                        getTagSet("classmates"),
                        null,
                        getCourseSet("CS1231S"), new Hour("20")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Telegram("@rollieroy"),
                        getTagSet("colleagues"),
                        new FreeTime(LocalTime.parse("11:30"), LocalTime.parse("12:30")),
                        getCourseSet("CS1231S"), new Hour("5"))
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
     * Returns a course set containing the list of strings given.
     */
    public static Set<Course> getCourseSet(String... strings) {
        return Arrays.stream(strings)
                .map(UniqueCourseList::findByCourseCode)
                .collect(Collectors.toSet());
    }
}
