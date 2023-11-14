package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Note;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("catering", "RilassisCatering")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("soundSystems", "EragonSounds")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("flowers", "CharlottesBoutique")),
            new Person(new Name("Xiangs Catering"), new Phone("62840300"), new Email("sales@xiangscatering.com.sg"),
                new Address("171 Kampong Ampat 02-02 KA Foodlink, 368330"),
                getTagSet("378perMeal", "catering")),
            new Person(new Name("EATZ"), new Phone("67890328"), new Email("sales@eatzcatering.com"),
                new Address("1550 Bedok North Ave 4, #04-17, 489950"),
                getTagSet("1000perMeal", "catering")),
            new Person(new Name("Angels Restaurant"), new Phone("62431802"), new Email("orders@rasa2.sg"),
                new Address("3017 Bedok North Street 5, Singapore 486121"),
                getTagSet("540perMeal", "catering"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("Find caterer"), new Note("for 221 students in day 1 of orientation"), getTagSet(
                    "caterer", "orientation")),
            new Task(new Title("Create budget"), new Note("for finale night"), getTagSet("finance", "orientation"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

}
