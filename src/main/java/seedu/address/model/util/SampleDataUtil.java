package seedu.address.model.util;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.course.Course;
import seedu.address.model.course.Lesson;
import seedu.address.model.course.UniqueCourseList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Hour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 *
 * @formatter:off
 */
public class SampleDataUtil {
    /**
     * Populates the UniqueCourseList sample courses.
     *
     * @return
     */
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Telegram("@alexyeoh"),
                    getTagSet("friends"),
                    new FreeTime(LocalTime.parse("11:30"), LocalTime.parse("12:30")),
                    getCourseSet("CS2103T"), new Hour(8)),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Telegram("@berrrrrnice"),
                    getTagSet("colleagues", "friends"),
                    new FreeTime(LocalTime.parse("12:30"), LocalTime.parse("13:30")),
                    getCourseSet("CS2103T"), new Hour(2)),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Telegram("@heyimcharlotte"),
                    getTagSet("neighbours"),
                    null,
                    getCourseSet("CS2103T"), new Hour(8)),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Telegram("@davidli123"),
                    getTagSet("family"),
                    new FreeTime(LocalTime.parse("14:30"), LocalTime.parse("15:30")),
                    getCourseSet("CS2103T"), new Hour(10)),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Telegram("@irfannn"),
                    getTagSet("classmates"),
                    null,
                    getCourseSet("CS1231S"), new Hour(20)),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Telegram("@rollieroy"),
                    getTagSet("colleagues"),
                    new FreeTime(LocalTime.parse("11:30"), LocalTime.parse("12:30")),
                    getCourseSet("CS1231S"), new Hour(5))
        };
    }

    public static Course[] getSampleCourses() {
        return new Course[]{
            new Course("Discrete Structures", "CS1231S",
                    Set.of(
                            new Lesson("Lecture", "CS1231S", "MONDAY", "10:00", "12:00"),
                            new Lesson("Tutorial", "CS1231S", "TUESDAY", "10:00", "12:00"),
                            new Lesson("Laboratory", "CS1231S", "WEDNESDAY", "10:00", "12:00")
                    )
            ),
            new Course("Programming Methodology", "CS1101S",
                    Set.of(
                            new Lesson("Lecture", "CS1101S", "MONDAY", "10:00", "12:00"),
                            new Lesson("Tutorial", "CS1101S", "TUESDAY", "10:00", "12:00"),
                            new Lesson("Laboratory", "CS1101S", "WEDNESDAY", "10:00", "12:00")
                    )
            ),
            new Course("Data Structures and Algorithms", "CS2040S",
                    Set.of(
                            new Lesson("Lecture", "CS2040S", "MONDAY", "10:00", "12:00"),
                            new Lesson("Tutorial", "CS2040S", "TUESDAY", "10:00", "12:00"),
                            new Lesson("Laboratory", "CS2040S", "WEDNESDAY", "10:00", "12:00")
                    )
            ),
            new Course("Computer Organisation", "CS2100",
                    Set.of(
                            new Lesson("Lecture", "CS2100", "MONDAY", "10:00", "12:00"),
                            new Lesson("Tutorial", "CS2100", "TUESDAY", "10:00", "12:00"),
                            new Lesson("Laboratory", "CS2100", "WEDNESDAY", "10:00", "12:00")
                    )
            ),
            new Course("Software Engineering", "CS2103T",
                    Set.of(
                            new Lesson("Lecture", "CS2103T", "MONDAY", "10:00", "12:00"),
                            new Lesson("Tutorial", "CS2103T", "TUESDAY", "10:00", "12:00"),
                            new Lesson("Laboratory", "CS2103T", "WEDNESDAY", "10:00", "12:00")
                    )
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static List<Course> getSampleCoursesData() {
        return Arrays.asList(getSampleCourses());
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

    /**
     * Returns a lesson set containing the list of strings given.
     */
    public static Set<Lesson> getLessonSet(String[]... strings) {
        Set<Lesson> lessons = new HashSet<>();
        for (String[] lesson : strings) {
            lessons.add(UniqueCourseList.findLesson(lesson[0], lesson[1]));
        }
        return lessons;
    }
}
