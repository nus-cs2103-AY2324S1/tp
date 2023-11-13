package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{new Person(new Name("Alex Yeoh"),
            Optional.of(new Phone("87438807")),
            Optional.of(new Email("alexyeoh@example.com")),
            Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")),
            Optional.of(new Birthday("2001-12-20")),
            Optional.of(new Remark("")),
            getGroupSet("friends")),
            new Person(new Name("Bernice Yu"),
            Optional.of(new Phone("99272758")),
            Optional.of(new Email("berniceyu@example.com")),
            Optional.of(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            Optional.of(new Birthday("2001-12-21")),
            Optional.of(new Remark("")),
            getGroupSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"),
            Optional.of(new Phone("93210283")),
            Optional.of(new Email("charlotte@example.com")),
            Optional.of(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
            Optional.of(new Birthday("2001-12-22")),
            Optional.of(new Remark("")),
            getGroupSet("neighbours")),
            new Person(new Name("David Li"),
            Optional.of(new Phone("91031282")),
            Optional.of(new Email("lidavid@example.com")),
            Optional.of(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
            Optional.of(new Birthday("2001-12-23")),
            Optional.of(new Remark("")),
            getGroupSet("family")),
            new Person(new Name("Irfan Ibrahim"),
            Optional.of(new Phone("92492021")),
            Optional.of(new Email("irfan@example.com")),
            Optional.of(new Address("Blk 47 Tampines Street 20, #17-35")),
            Optional.of(new Birthday("2001-12-24")),
            Optional.of(new Remark("")),
            getGroupSet("classmates")),
            new Person(new Name("Roy Balakrishnan"),
            Optional.of(new Phone("92624417")),
            Optional.of(new Email("royb@example.com")),
            Optional.of(new Address("Blk 45 Aljunied Street 85, #11-31")),
            Optional.of(new Birthday("2001-12-25")),
            Optional.of(new Remark("")),
            getGroupSet("colleagues")),
            new Person(new Name("Ken"),
            Optional.of(new Phone("92624417")),
            Optional.of(new Email("ken@example.com")),
            Optional.of(new Address("Blk 124 Bukit Merah Rd, #08-31")),
            Optional.of(new Birthday("2001-03-23")),
            Optional.of(new Remark("")),
            getGroupSet("Team2")),
            new Person(new Name("Yuheng"),
            Optional.of(new Phone("92624417")),
            Optional.of(new Email("yuheng@example.com")),
            Optional.of(new Address("Blk 17 Lor 7 Toa Payoh, #08-31")),
            Optional.of(new Birthday("2001-08-23")),
            Optional.of(new Remark("")),
            getGroupSet("Team2"))
        };
    }

    /**
     * Returns an event list containing the list of events given.
     */
    public static Event[] getSampleEvents() {
        try {
            return new Event[]{new Meeting(new EventName("Group meeting"),
                    new EventDate("2023-10-10"),
                    Optional.of(EventTime.of("1000")),
                    Optional.of(EventTime.of("1200")),
                    getNameSet("Alex Yeoh", "Bernice Yu"),
                    getGroupSet("classmates")
            ), new Meeting(new EventName("Lunch with friends"),
                    new EventDate("2023-12-10"),
                    Optional.of(EventTime.NULL_EVENT_TIME),
                    Optional.of(EventTime.NULL_EVENT_TIME),
                    new HashSet<>(),
                    getGroupSet("friends")
            ), new Meeting(new EventName("Family dinner"),
                    new EventDate("2023-12-11"),
                    Optional.of(EventTime.of("1800")),
                    Optional.of(EventTime.NULL_EVENT_TIME),
                    getNameSet("David Li"),
                    new HashSet<>()
            )};
        } catch (ParseException e) {
            return new Event[]{};
        }
    }



    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

    /**
     * Returns a group set containing the list of strings given.
     */
    public static Set<Group> getGroupSet(String... strings) {
        if (strings.length == 0) {
            return new HashSet<>();
        } else if (strings.length == 1 && strings[0].isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }

    public static Set<Name> getNameSet(String... strings) {
        if (strings.length == 0) {
            return new HashSet<>();
        } else if (strings.length == 1 && strings[0].isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(strings)
                .map(Name::new)
                .collect(Collectors.toSet());
    }
}
