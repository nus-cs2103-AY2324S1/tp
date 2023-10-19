package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTeamBook;
import seedu.address.model.TeamBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.team.Team;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTagSet("colleagues"))
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

    // Additions for TeamBook
    public static Team[] getSampleTeams() {
        Team teamAlpha = new Team(new IdentityCode("1"), "Team Alpha");
        teamAlpha.addDeveloper(new IdentityCode("1"));
        teamAlpha.addDeveloper(new IdentityCode("2"));

        Team teamBeta = new Team(new IdentityCode("2"), "Team Beta");
        teamBeta.addDeveloper(new IdentityCode("3"));
        teamBeta.addDeveloper(new IdentityCode("4"));

        Team teamGamma = new Team(new IdentityCode("3"), "Team Gamma");
        teamGamma.addDeveloper(new IdentityCode("5"));
        teamGamma.addDeveloper(new IdentityCode("6"));

        Team teamDelta = new Team(new IdentityCode("4"), "Team Delta");
        teamDelta.addDeveloper(new IdentityCode("7"));
        teamDelta.addDeveloper(new IdentityCode("8"));

        return new Team[] {teamAlpha, teamBeta, teamGamma, teamDelta};
    }

    public static ReadOnlyTeamBook getSampleTeamBook() {
        TeamBook sampleTb = new TeamBook();
        for (Team sampleTeam : getSampleTeams()) {
            sampleTb.addTeam(sampleTeam);
        }
        return sampleTb;
    }
}
