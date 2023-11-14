package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.JobFestGo;
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.address.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Contains utility methods for populating {@code JobFestGo} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns an array of sample Contacts.
     * @return an array of sample Contacts
     */
    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("vendors")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("caterers", "clients")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("customers")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("suppliers")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("vendors")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("customers"))
        };
    }

    /**
     * Returns an array of sample tags.
     * @return an array of sample tags
     */
    public static Tag[] getSampleTags() {
        return new Tag[] {
            new Tag("vendors"),
            new Tag("customers"),
            new Tag("caterers"),
            new Tag("clients"),
            new Tag("suppliers")
        };
    }

    /**
     * Returns an array of sample tasks.
     * @return an array of sample tasks
     */
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskDescription("Order Food"), new Date("2024-01-13"), getSampleEvents()[0], false),
            new Task(new TaskDescription("Call vendors"), new Date("2024-01-12"), getSampleEvents()[1], false)
        };
    }

    /**
     * Returns an array of sample events.
     * @return an array of sample events
     */
    public static Event[] getSampleEvents() {
        Contact[] sampleContacts = getSampleContacts();

        Set<Contact> sampleContactSet1 = new HashSet<>();
        sampleContactSet1.add(sampleContacts[0]);
        sampleContactSet1.add(sampleContacts[1]);

        Set<Contact> sampleContactSet2 = new HashSet<>();
        sampleContactSet2.add(sampleContacts[2]);
        sampleContactSet2.add(sampleContacts[3]);
        sampleContactSet2.add(sampleContacts[4]);

        Set<Contact> sampleContactSet3 = new HashSet<>();

        Set<Task> sampleTaskSet1 = new HashSet<>();
        Set<Task> sampleTaskSet2 = new HashSet<>();

        Event sampleEvent1 = new Event(new Name("NUS Career Fair 2023"), new Date("2024-01-23"),
                new Address("311, Clementi Ave 2, #02-25"), sampleContactSet1, sampleTaskSet1);
        Event sampleEvent2 = new Event(new Name("JobFest 2023"), new Date("2024-01-12"),
                new Address("3 Temasek Blvd, Singapore 038983"), sampleContactSet2, sampleTaskSet2);
        Event sampleEvent3 = new Event(new Name("NTU Job In Fair 2023"), new Date("2024-02-10"),
                new Address("50 Nanyang Ave, #32 Block N4 #02a, Singapore 639798"),
                sampleContactSet3, sampleTaskSet1);

        return new Event[] {

            sampleEvent1, sampleEvent2, sampleEvent3

        };
    }

    /**
     * Returns an {@code JobFestGo} with sample data.
     * @return sample readOnlyJobFestGo
     */
    public static ReadOnlyJobFestGo getSampleJobFestGo() {
        JobFestGo sampleAb = new JobFestGo();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        for (Tag sampleTag : getSampleTags()) {
            sampleAb.addTag(sampleTag);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
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
    public static Set<Contact> getContactSet(Contact... contacts) {
        return Arrays.stream(contacts).collect(Collectors.toSet());
    }

    public static Set<Task> getTaskSet(Task ... tasks) {
        return Arrays.stream(tasks).collect(Collectors.toSet());
    }
}
