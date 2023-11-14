package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {

        ArrayList<Note> sampleNotes = new ArrayList<Note>();
        sampleNotes.add(new Note("Questions to ask", "Ask about the bug at line 34"));

        ArrayList<Note> emptyNotes = new ArrayList<Note>();

        ArrayList<Event> sampleEvents1 = new ArrayList<Event>();
        sampleEvents1.add(new Event("Meeting with professor",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")),
                LocalDateTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")),
                "COM 1 Basement",
                "Discuss the project implementation with the professor"));

        ArrayList<Event> sampleEvents2 = new ArrayList<Event>();
        sampleEvents2.add(new Event("Chat with TikTok recruiter",
                LocalDateTime.now().plusHours(2).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")),
                LocalDateTime.now().plusHours(3).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")),
                "Online",
                "Need to prepare for questions"));

        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"), sampleNotes, sampleEvents1),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), emptyNotes, sampleEvents2),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours"), emptyNotes, new ArrayList<Event>()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family"), emptyNotes, new ArrayList<Event>()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates"), emptyNotes, new ArrayList<Event>()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"), emptyNotes, new ArrayList<Event>()),
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

}
