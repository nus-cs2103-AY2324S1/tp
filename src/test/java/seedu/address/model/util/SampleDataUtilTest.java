package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getSampleAddressBook;
import static seedu.address.model.util.SampleDataUtil.getSampleEvents;
import static seedu.address.model.util.SampleDataUtil.getSampleMembers;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.Location;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Gender;
import seedu.address.model.member.Member;
import seedu.address.model.member.Phone;
import seedu.address.model.shared.Name;

class SampleDataUtilTest {

    @Test
    void getSampleMembers_returnsValidMembers() {
        Member[] sampleMembers = getSampleMembers();
        Member alex = new Member(new Name("Alex Yeoh"), new Gender("Male"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"));
        Member bernice = new Member(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"));
        Member charlotte = new Member(new Name("Charlotte Oliveiro"), new Gender("Female"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours"));
        Member david = new Member(new Name("David Li"), new Gender("Male"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family"));
        Member irfan = new Member(new Name("Irfan Ibrahim"), new Gender("Male"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates"));
        Member roy = new Member(new Name("Roy Balakrishnan"), new Gender("Male"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues"));
        assertTrue(Arrays.stream(sampleMembers).anyMatch(alex::equals));
        assertTrue(Arrays.stream(sampleMembers).anyMatch(bernice::equals));
        assertTrue(Arrays.stream(sampleMembers).anyMatch(charlotte::equals));
        assertTrue(Arrays.stream(sampleMembers).anyMatch(david::equals));
        assertTrue(Arrays.stream(sampleMembers).anyMatch(irfan::equals));
        assertTrue(Arrays.stream(sampleMembers).anyMatch(roy::equals));
    }

    @Test
    void getSampleEvents_returnsValidEvents() {
        Event[] sampleEvents = getSampleEvents();
        Event aurora = new Event(new Name("Aurora Borealis"),
                new EventDate("2023-11-30"), new Location("Greenland"));
        Event boxing = new Event(new Name("Boxing Day"),
                new EventDate("2023-12-26"), new Location("Ridge View Residential College"));
        Event cny = new Event(new Name("Chinese New Year"),
                new EventDate("2024-02-10"), new Location("Communal Hall"));
        Event dog = new Event(new Name("Dog Day"),
                new EventDate("2024-08-26"), new Location("UTown Pitstop"));
        Event echo = new Event(new Name("Echo Day"),
                new EventDate("2023-05-05"), new Location("Batu Cave"));
        Event festival = new Event(new Name("Festival"),
                new EventDate("2023-10-05"), new Location("Clementi 321"));
        assertTrue(Arrays.stream(sampleEvents).anyMatch(aurora::equals));
        assertTrue(Arrays.stream(sampleEvents).anyMatch(boxing::equals));
        assertTrue(Arrays.stream(sampleEvents).anyMatch(cny::equals));
        assertTrue(Arrays.stream(sampleEvents).anyMatch(dog::equals));
        assertTrue(Arrays.stream(sampleEvents).anyMatch(echo::equals));
        assertTrue(Arrays.stream(sampleEvents).anyMatch(festival::equals));

    }

    @Test
    void getSampleAddressBook_returnsValidMembersAndEvents() {
        Member alex = new Member(new Name("Alex Yeoh"), new Gender("Male"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"));
        Member bernice = new Member(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"));
        Member charlotte = new Member(new Name("Charlotte Oliveiro"), new Gender("Female"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"));
        Member david = new Member(new Name("David Li"), new Gender("Male"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"));
        Member irfan = new Member(new Name("Irfan Ibrahim"), new Gender("Male"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"));
        Member roy = new Member(new Name("Roy Balakrishnan"), new Gender("Male"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"));
        Event aurora = new Event(new Name("Aurora Borealis"),
                new EventDate("2023-11-30"), new Location("Greenland"));
        Event boxing = new Event(new Name("Boxing Day"),
                new EventDate("2023-12-26"), new Location("Ridge View Residential College"));
        Event cny = new Event(new Name("Chinese New Year"),
                new EventDate("2024-02-10"), new Location("Communal Hall"));
        Event dog = new Event(new Name("Dog Day"),
                new EventDate("2024-08-26"), new Location("UTown Pitstop"));
        Event echo = new Event(new Name("Echo Day"),
                new EventDate("2023-05-05"), new Location("Batu Cave"));
        Event festival = new Event(new Name("Festival"),
                new EventDate("2023-10-05"), new Location("Clementi 321"));
        AddressBook addressBook = (AddressBook) getSampleAddressBook();
        assertTrue(addressBook.hasMember(alex));
        assertTrue(addressBook.hasMember(bernice));
        assertTrue(addressBook.hasMember(charlotte));
        assertTrue(addressBook.hasMember(david));
        assertTrue(addressBook.hasMember(irfan));
        assertTrue(addressBook.hasMember(roy));
        assertTrue(addressBook.hasEvent(aurora));
        assertTrue(addressBook.hasEvent(boxing));
        assertTrue(addressBook.hasEvent(cny));
        assertTrue(addressBook.hasEvent(dog));
        assertTrue(addressBook.hasEvent(echo));
        assertTrue(addressBook.hasEvent(festival));
    }
}
