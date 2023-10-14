package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyGradedTestList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskListBook;
import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getGradedTestSet()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), getGradedTestSet()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), getGradedTestSet()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getGradedTestSet()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), getGradedTestSet()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), getGradedTestSet())
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskName("Do 2103T"), new TaskDescription("Homework assignment")),
            new Task(new TaskName("Do cs2101"), new TaskDescription("Practice script")),
            new Task(new TaskName("Do cs2100"), new TaskDescription("Remember mips"))
        };
    }

    public static GradedTest[] getSampleGradedTest() {
        return new GradedTest[] {
            new GradedTest(new ReadingAssessment("1"), new ReadingAssessment("2"),
                    new MidTerms("3"), new Finals("4"), new PracticalExam("5"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskListBook sampleTl = new TaskListBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }
        return sampleTl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyGradedTestList getSampleGradedTestList() {
        GradedTestListBook sampleTl = new GradedTestListBook();
        for (GradedTest sampleGradedTest : getSampleGradedTest()) {
            sampleTl.addGradedTest(sampleGradedTest);
        }
        return sampleTl;
    }

    /**
     * Returns a gradedTest set containing the list of strings given.
     */
    public static Set<GradedTest> getGradedTestSet(String... strings) {
        return Arrays.stream(strings)
                .map(GradedTest::new)
                .collect(Collectors.toSet());
    }

}
