package seedu.address.model.util;

import static seedu.address.logic.parser.ParserUtil.FORMAT;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Status(""), new Remark(""),
                        getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Status(""), new Remark(""),
                        getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Status(""), new Remark(""),
                        getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Status(""), new Remark(""),
                        getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Status(""), new Remark(""),
                        getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Status(""), new Remark(""),
                        getTagSet("colleagues"))
        };
    }

    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Meeting(new Title("Test Meeting 1"), new Location("Room 1"),
                    LocalDateTime.parse("02.10.2023 1000", FORMAT),
                    LocalDateTime.parse("03.10.2023 1000", FORMAT),
                    getAttendeeSet("Alex Yeoh"), getTagSet("work")),
            new Meeting(new Title("Test Meeting 2"), new Location("Room 2"),
                    LocalDateTime.parse("02.10.2023 1000", FORMAT),
                    LocalDateTime.parse("02.10.2023 1000", FORMAT),
                    getAttendeeSet(), getTagSet()),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleAb.addMeeting(sampleMeeting);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::of)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an attendee set containing the list of strings given.
     */
    public static Set<Attendee> getAttendeeSet(String... strings) {
        return Arrays.stream(strings)
                .map(Attendee::new)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
