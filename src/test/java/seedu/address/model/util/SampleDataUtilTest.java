package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getGroupSet;
import static seedu.address.model.util.SampleDataUtil.getNameSet;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
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

public class SampleDataUtilTest {

    @Test
    public void getSamplePersonsTest() {
        Person samplePerson = new Person(new Name("Alex Yeoh"),
                Optional.of(new Phone("87438807")),
                Optional.of(new Email("alexyeoh@example.com")),
                Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")),
                Optional.of(new Birthday("2001-12-20")),
                Optional.of(new Remark("")),
                getGroupSet("friends"));

        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        assertEquals(samplePerson, samplePersons[0]);
    }

    @Test
    public void doesNotContainPersonTest() {
        Person invalidPerson = new Person(new Name("Bob"),
                Optional.of(new Phone("87438807")),
                Optional.of(new Email("alexyeoh@example.com")),
                Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")),
                Optional.of(new Birthday("2001-12-20")),
                Optional.of(new Remark("")),
                getGroupSet("friends"));

        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        assertFalse(Arrays.asList(samplePersons).contains(invalidPerson));
    }

    @Test
    public void getSampleEventsTest() throws ParseException {
        Event sampleEvent = new Meeting(new EventName("Group meeting"),
                new EventDate("2023-10-10"),
                Optional.of(EventTime.of("1000")),
                Optional.of(EventTime.of("1200")),
                getNameSet("Alex Yeoh", "Bernice Yu"),
                getGroupSet("classmates"));

        Event[] sampleEvents = SampleDataUtil.getSampleEvents();

        assertEquals(sampleEvent, sampleEvents[0]);
    }

    @Test
    public void doesNotContainEventTest() throws ParseException {
        Event invalidEvent = new Meeting(new EventName("Group meeting"),
                new EventDate("2023-10-10"),
                Optional.of(EventTime.of("1000")),
                Optional.of(EventTime.of("1200")),
                getNameSet("Alex Yeoh"),
                getGroupSet("classmates"));

        Event[] sampleEvents = SampleDataUtil.getSampleEvents();

        assertFalse(Arrays.asList(sampleEvents).contains(invalidEvent));
    }

    @Test
    public void getGroupSetTest() {
        assertEquals(getGroupSet("friends"), SampleDataUtil.getGroupSet("friends"));
    }

    @Test
    public void getGroupSetContainsTest() {
        Group sampleGroup = new Group("friends");

        Set<Group> groupSet = getGroupSet("friends", "family");

        assertTrue(groupSet.contains(sampleGroup));
    }

    @Test
    public void getGroupSetDoesNotContainTest() {
        Group sampleGroup = new Group("friends");

        Set<Group> groupSet = getGroupSet("family");

        assertFalse(groupSet.contains(sampleGroup));
    }

    @Test
    public void getNameSetTest() {
        assertEquals(getNameSet("Alex Yeoh"), SampleDataUtil.getNameSet("Alex Yeoh"));
    }

    @Test
    public void getNameSetContainsTest() {
        Name sampleName = new Name("Alex Yeoh");

        Set<Name> nameSet = getNameSet("Alex Yeoh", "Bernice Yu");

        assertTrue(nameSet.contains(sampleName));
    }

    @Test
    public void getNameSetDoesNotContaintest() {
        Name sampleName = new Name("Alex Yeoh");

        Set<Name> nameSet = getNameSet("Bernice Yu");

        assertFalse(nameSet.contains(sampleName));
    }

}
