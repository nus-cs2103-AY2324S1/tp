package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Year;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Major("Computer Science"), new Year("2"),
                new Email("alexyeoh@u.nus.edu"), new Description("Friendly person"),
                getTutorialSet("01", "20"),
                getSocialMediaLinkSet("https://example.com/alex"),
                new Nationality("local"), new Gender("M")),
            new Person(new Name("Bernice Yu"), new Major("Computer Science"), new Year("2"),
                new Email("berniceyu@u.nus.edu"), new Description("Colleague from work"),
                getTutorialSet("08", "19"),
                getSocialMediaLinkSet("https://example.com/bernice"),
                new Nationality("local"), new Gender("F")),
            new Person(new Name("Charlotte Oliveiro"), new Major("Computer Science"), new Year("2"),
                new Email("charlotte@u.nus.edu"), new Description("Neighbour"),
                getTutorialSet("05", "06", "10"),
                getSocialMediaLinkSet("https://example.com/charlotte"),
                new Nationality("local"), new Gender("F")),
            new Person(new Name("David Li"), new Major("Computer Science"), new Year("2"),
                new Email("lidavid@u.nus.edu"), new Description("Family member"),
                getTutorialSet("11"),
                getSocialMediaLinkSet("https://example.com/david"),
                new Nationality("foreigner"), new Gender("M")),
            new Person(new Name("Irfan Ibrahim"), new Major("Computer Science"), new Year("2"),
                new Email("irfan@u.nus.edu"), new Description("Classmate"),
                getTutorialSet("07", "18"),
                getSocialMediaLinkSet("https://example.com/irfan"),
                new Nationality("local"), new Gender("M")),
            new Person(new Name("Roy Balakrishnan"), new Major("Computer Science"), new Year("2"),
                new Email("royb@u.nus.edu"), new Description("Colleague from work"),
                getTutorialSet("11", "19", "22"),
                getSocialMediaLinkSet("https://example.com/roy"),
                new Nationality("foreigner"), new Gender("M"))
        };
    }

    public static Group[] getSampleGroups() {
        Group sampleGroup1 = new Group(1, new Tutorial("01"));
        sampleGroup1.addMember(getSamplePersons()[0]);

        Group sampleGroup2 = new Group(2, new Tutorial("11"));
        sampleGroup2.addMember(getSamplePersons()[3]);
        sampleGroup2.addMember(getSamplePersons()[5]);

        return new Group[]{ sampleGroup1, sampleGroup2 };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }
        return sampleAb;
    }

    public static Set<Tutorial> getTutorialSet(String... tutorials) {
        return Arrays.stream(tutorials)
            .map(Tutorial::new)
            .collect(Collectors.toSet());
    }

    public static Set<SocialMediaLink> getSocialMediaLinkSet(String... strings) {
        return Arrays.stream(strings)
            .map(SocialMediaLink::new)
            .collect(Collectors.toSet());
    }
}
