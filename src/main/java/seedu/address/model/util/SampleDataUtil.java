package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.Location;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Gender;
import seedu.address.model.member.Member;
import seedu.address.model.member.Phone;
import seedu.address.model.shared.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Member[] getSampleMembers() {
        return new Member[] {
            new Member(new Name("Alex Yeoh"), new Gender("Male"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Member(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Member(new Name("Charlotte Oliveiro"), new Gender("Female"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Member(new Name("David Li"), new Gender("Male"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Member(new Name("Irfan Ibrahim"), new Gender("Male"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Member(new Name("Roy Balakrishnan"), new Gender("Male"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
                new Event(new Name("Aurora Borealis"),
                        new EventDate("2023-11-30"), new Location("Greenland")),
                new Event(new Name("Boxing Day"),
                        new EventDate("2023-12-26"), new Location("Ridge View Residential College")),
                new Event(new Name("Chinese New Year"),
                        new EventDate("2024-02-10"), new Location("Communal Hall")),
                new Event(new Name("Dog Day"),
                        new EventDate("2024-08-26"), new Location("UTown Pitstop")),
                new Event(new Name("Echo Day"),
                        new EventDate("2023-05-05"), new Location("Batu Cave")),
                new Event(new Name("Festival"),
                        new EventDate("2023-10-05"), new Location("Clementi 321")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Member sampleMember : getSampleMembers()) {
            sampleAb.createMember(sampleMember);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.createEvent(sampleEvent);
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
